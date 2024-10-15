package com.e3i3.moduerp.salesstock.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.e3i3.moduerp.employee.model.service.EmployeeProductionService;
import com.e3i3.moduerp.item.model.dto.ItemDTO;
import com.e3i3.moduerp.item.model.service.ItemSalesStockService;
import com.e3i3.moduerp.salesstock.model.dto.SalesStockInDTO;
import com.e3i3.moduerp.salesstock.service.SalesStockInService;

@Controller
@RequestMapping("/")
public class SalesStockInController {

	@Autowired
	private SalesStockInService salesStockInService;

	@Autowired
	private ItemSalesStockService itemSalesStockService;
	@Autowired
	private EmployeeProductionService employeeProductionService;

	// Sales Stock In GET method
	@RequestMapping(value = "/salesStockIn.do", method = RequestMethod.GET)
	public String forwardSalesIn(@RequestParam(value = "page", defaultValue = "1") int page, Model model,
			HttpSession session) {
		String bizNumber = (String) session.getAttribute("biz_number");
		List<ItemDTO> itemList = itemSalesStockService.getItemsByBizNumber(bizNumber);

		// Add 9 hours to CREATED_AT
		for (ItemDTO item : itemList) {
			Timestamp createdAt = item.getCreatedAt();
			Timestamp adjustedTimestamp = Timestamp
					.from(Instant.ofEpochMilli(createdAt.getTime() + 9 * 60 * 60 * 1000));
			item.setCreatedAt(adjustedTimestamp);
		}

		int itemsPerPage = 10;
		int totalItems = itemList.size();
		int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);
		int startIndex = (page - 1) * itemsPerPage;
		int endIndex = Math.min(startIndex + itemsPerPage, totalItems);
		List<ItemDTO> paginatedList = itemList.subList(startIndex, endIndex);

		model.addAttribute("itemList", paginatedList);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", page);

		return "salesStock/salesStockIn";
	}

	@RequestMapping(value = "/salesStockInFilter.do", method = RequestMethod.GET)
	public String forwardSalesInFilter(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "filterOption", required = false) String option,
			@RequestParam(value = "filterText", required = false) String filterText,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate, Model model, HttpSession session) {

		String bizNumber = (String) session.getAttribute("biz_number");
		List<ItemDTO> itemList;

		// 필터링 로직 
		if (option != null && filterText != null) {
			if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
				System.out.println("날짜있는거 실행");
				itemList = itemSalesStockService.getItemByFilterDate(bizNumber, option, filterText, startDate, endDate);
			} else if ((option == null || filterText == null || filterText.isEmpty()) && startDate != null
					&& !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
				// 날짜만 있는 경우 처리
				System.out.println("날짜만 있는 경우 실행");
				itemList = itemSalesStockService.getItemByFilterOnlyDate(bizNumber, startDate, endDate);
			} else if (startDate != null && !startDate.isEmpty() && (endDate == null || endDate.isEmpty())) {
				// 시작 날짜만 있는 경우 처리
				System.out.println("시작 날짜만 있는 경우 실행");
				itemList = itemSalesStockService.getItemByFilterStartDate(bizNumber, startDate);
			} else if ((startDate == null || startDate.isEmpty()) && endDate != null && !endDate.isEmpty()) {
				// 종료 날짜만 있는 경우 처리
				System.out.println("종료 날짜만 있는 경우 실행");
				itemList = itemSalesStockService.getItemByFilterEndDate(bizNumber, endDate);
			} else {
				System.out.println("날짜없는거 실행");
				itemList = itemSalesStockService.getItemsByFilter(bizNumber, option, filterText);
			}
		} else if ((option == null || filterText == null || filterText.isEmpty()) && startDate != null
				&& !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
			// 필터 옵션과 텍스트 없이 날짜만 있는 경우
			System.out.println("날짜만 있는 경우 실행");
			itemList = itemSalesStockService.getItemByFilterOnlyDate(bizNumber, startDate, endDate);
		} else if (startDate != null && !startDate.isEmpty() && (endDate == null || endDate.isEmpty())) {
			// 시작 날짜만 있는 경우 처리
			System.out.println("시작 날짜만 있는 경우 실행");
			itemList = itemSalesStockService.getItemByFilterStartDate(bizNumber, startDate);
		} else if ((startDate == null || startDate.isEmpty()) && endDate != null && !endDate.isEmpty()) {
			// 종료 날짜만 있는 경우 처리
			System.out.println("종료 날짜만 있는 경우 실행");
			itemList = itemSalesStockService.getItemByFilterEndDate(bizNumber, endDate);
		} else {
			itemList = itemSalesStockService.getItemsByBizNumber(bizNumber);
		}

		// 페이지네이션 처리
		int itemsPerPage = 10;
		int totalItems = itemList.size();
		int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);
		int startIndex = (page - 1) * itemsPerPage;
		int endIndex = Math.min(startIndex + itemsPerPage, totalItems);

		// 서브리스트 생성
		List<ItemDTO> paginatedList = itemList.subList(startIndex, endIndex);

		// 모델에 추가
		model.addAttribute("itemList", paginatedList);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", page);
		model.addAttribute("option", option);
		model.addAttribute("filterText", filterText);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);

		return "salesStock/salesStockInFilter"; // JSP 파일 경로 반환
	}

	@PostMapping("/salesStockInCreate.do")
	public String createSalesStockIn(@RequestParam("sStockInDate") String stockInDateStr,
			@RequestParam("stockPlace") String stockPlace, @RequestParam("stockIn") int stockIn,
			@RequestParam("itemName") String itemName, @RequestParam("itemDesc") String itemDesc,
			@RequestParam("inPrice") double inPrice, @RequestParam("materialType") List<String> materialType,
			@RequestParam("director") String iDirector, HttpSession session) {

		String bizNumber = (String) session.getAttribute("biz_number");
		String userUuid = (String) session.getAttribute("uuid");

		LocalDate localDate = LocalDate.parse(stockInDateStr);
		LocalDateTime localDateTime = localDate.atStartOfDay();
		Timestamp stockInDate = Timestamp.valueOf(localDateTime);

		ZonedDateTime nowKST = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
		Timestamp currentTimestampKST = Timestamp.valueOf(nowKST.toLocalDateTime());

		String itemCode = bizNumber + "S" + currentTimestampKST.getTime();

		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setItemCode(itemCode);
		itemDTO.setItemName(itemName);
		itemDTO.setItemDesc(itemDesc);
		itemDTO.setCreatedAt(stockInDate);
		itemDTO.setStockPlace(stockPlace);
		itemDTO.setInPrice(inPrice);
		itemDTO.setBizNumber(bizNumber);
		itemDTO.setItemList(materialType);
		itemDTO.setStockIn(stockIn);
		itemDTO.setStock(stockIn);
		itemDTO.setiDirector(iDirector);

		itemSalesStockService.insertItem(itemDTO);

		String sStockInId = "S" + bizNumber + currentTimestampKST.getTime();

		SalesStockInDTO salesStockInDTO = new SalesStockInDTO();
		salesStockInDTO.setsStockInId(sStockInId);
		salesStockInDTO.setItemCode(itemCode);
		salesStockInDTO.setsStockInDate(stockInDate);
		salesStockInDTO.setsStockInPlace(stockPlace);
		salesStockInDTO.setsStockInQty(stockIn);
		salesStockInDTO.setUuid(userUuid);

		salesStockInService.insertSalesStockIn(salesStockInDTO);

		return "redirect:/salesStockIn.do";
	}

	@GetMapping("/getSalesInDetails.do")
	public String getSalesInDetails(@RequestParam("itemCode") String itemCode, Model model) {
		ItemDTO itemDetails = itemSalesStockService.getItemDetails(itemCode);
		SalesStockInDTO salesStockInDetails = salesStockInService.getSalesStockInDetails(itemCode);

		Timestamp createdAt = itemDetails.getCreatedAt();
		Timestamp adjustedCreatedAt = Timestamp.from(Instant.ofEpochMilli(createdAt.getTime() + 9 * 60 * 60 * 1000));
		itemDetails.setCreatedAt(adjustedCreatedAt);

		Timestamp updatedAt = itemDetails.getUpdatedAt();
		if (updatedAt != null) {
			Timestamp adjustedUpdatedAt = Timestamp
					.from(Instant.ofEpochMilli(updatedAt.getTime() + 9 * 60 * 60 * 1000));
			itemDetails.setUpdatedAt(adjustedUpdatedAt);
		} else {
			itemDetails.setUpdatedAt(null);
		}

		model.addAttribute("itemDetails", itemDetails);
		model.addAttribute("salesStockInDetails", salesStockInDetails);

		return "salesStock/salesStockInDetail";
	}

	@GetMapping("/salesStockInDetailUpdate.do")
	public String showUpdateForm(@RequestParam("itemCode") String itemCode, Model model, HttpSession session) {
		ItemDTO itemDetails = itemSalesStockService.getItemDetails(itemCode);

		Timestamp createdAt = itemDetails.getCreatedAt();
		Timestamp adjustedCreatedAt = Timestamp.from(Instant.ofEpochMilli(createdAt.getTime() + 9 * 60 * 60 * 1000));
		itemDetails.setCreatedAt(adjustedCreatedAt);

		SalesStockInDTO salesStockInDetails = salesStockInService.getSalesStockInDetails(itemCode);

		Timestamp updatedAt = itemDetails.getUpdatedAt();
		if (updatedAt != null) {
			Timestamp adjustedUpdatedAt = Timestamp
					.from(Instant.ofEpochMilli(updatedAt.getTime() + 9 * 60 * 60 * 1000));
			itemDetails.setUpdatedAt(adjustedUpdatedAt);
		} else {
			itemDetails.setUpdatedAt(null);
		}

		String bizNumber = (String) session.getAttribute("biz_number");
		List<String> itemNames = itemSalesStockService.getSalesItemNamesByBizNumber(bizNumber);

		model.addAttribute("itemDetails", itemDetails);
		model.addAttribute("salesStockInDetails", salesStockInDetails);
		model.addAttribute("itemNames", itemNames);

		return "salesStock/salesStockInDetailUpdate";
	}

	@PostMapping("/updateSalesStockIn.do")
	public String updateSalesStockIn(@RequestParam("itemCode") String itemCode,
			@RequestParam("itemName") String itemName, @RequestParam("itemDesc") String itemDesc,
			@RequestParam("stockIn") int stockIn, @RequestParam("inPrice") double inPrice,
			@RequestParam("stockPlace") String stockPlace, @RequestParam("materialTypes") List<String> materialTypes) {

		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

		ItemDTO currentItem = itemSalesStockService.getItemDetails(itemCode);

		Integer stockOut = currentItem.getStockOut() != null ? currentItem.getStockOut() : 0;

		int stock = stockIn - stockOut;

		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setItemCode(itemCode);
		itemDTO.setItemName(itemName);
		itemDTO.setItemDesc(itemDesc);
		itemDTO.setStockIn(stockIn);
		itemDTO.setStock(stock);
		itemDTO.setInPrice(inPrice);
		itemDTO.setStockPlace(stockPlace);
		itemDTO.setItemList(materialTypes);
		itemDTO.setUpdatedAt(currentTimestamp);

		itemSalesStockService.updateItem(itemDTO);

		SalesStockInDTO salesStockInDTO = new SalesStockInDTO();
		salesStockInDTO.setItemCode(itemCode);
		salesStockInDTO.setsStockInQty(stockIn);
		salesStockInDTO.setsStockInPlace(stockPlace);

		salesStockInService.updateSalesStockIn(salesStockInDTO);

		return "redirect:/salesStockIn.do";
	}

	@PostMapping("/deleteSalesStockIn.do")
	public String deleteSalesStockIn(@RequestParam("itemCode") String itemCode, HttpSession session) {
		salesStockInService.deleteSalesStockInByItemCode(itemCode);
		itemSalesStockService.deleteItemByCode(itemCode);

		return "redirect:/salesStockIn.do";
	}

}
