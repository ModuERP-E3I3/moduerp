package com.e3i3.moduerp.salesstock.controller;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.e3i3.moduerp.employee.model.service.EmployeeSalesService;
import com.e3i3.moduerp.item.model.dto.ItemDTO;
import com.e3i3.moduerp.item.model.service.ItemSalesStockService;
import com.e3i3.moduerp.salesstock.model.dto.SalesStockOutDTO;
import com.e3i3.moduerp.salesstock.service.SalesStockOutService;

@Controller
@RequestMapping("/")
public class SalesStockOutController {

	@Autowired
	private ItemSalesStockService itemSalesStockService;

	@Autowired
	private SalesStockOutService salesStockOutService;

	@Autowired
	private EmployeeSalesService employeeSalesService;

	@RequestMapping(value = "/salesStockOut.do", method = RequestMethod.GET)
	public String showSalesStockOut(@RequestParam(value = "page", defaultValue = "1") int page, Model model,
			HttpSession session) {
		String bizNumber = (String) session.getAttribute("biz_number");
		List<ItemDTO> itemList = itemSalesStockService.getItemsByBizNumberOutDate(bizNumber);

		// CREATED_AT에 9시간 추가하는 로직
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

		return "salesStock/salesStockOut"; // JSP 파일 경로 반환
	}
	
	@RequestMapping(value = "/salesStockOutFilter.do", method = RequestMethod.GET)   //  filter !!!!!
	public String forwardSalesInFilter(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "filterOption", required = false) String option,
			@RequestParam(value = "filterText", required = false) String filterText,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate, Model model, HttpSession session) {
		String bizNumber = (String) session.getAttribute("biz_number");
		List<ItemDTO> itemList;

		// 필터링 로직 추가
		if (option != null && filterText != null) {
			if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
				System.out.println("날짜있는거 실행");
				itemList = itemSalesStockService.getItemOutByFilterDate(bizNumber, option, filterText, startDate,
						endDate);
			} else if (startDate == null || startDate.isEmpty()) {
				System.out.println("날짜없는거 실행");
				itemList = itemSalesStockService.getItemOutByFilter(bizNumber, option, filterText);
			} else {
				System.out.println("실행 못함");
				itemList = itemSalesStockService.getItemsByBizNumberOutDate(bizNumber);
			}
		} else {
			itemList = itemSalesStockService.getItemsByBizNumberOutDate(bizNumber);
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

		return "salesStock/salesStockOutFilter"; // JSP 파일 경로 반환
	}

	// 등록 페이지로 이동
	@RequestMapping(value = "/salesStockOutCreate.do", method = RequestMethod.GET)
	public String showCreateForm(HttpSession session, Model model) {
		String bizNumber = (String) session.getAttribute("biz_number");
		String uuid = (String) session.getAttribute("uuid");
		String directorName = employeeSalesService.getEmployeeNameByUuid(uuid);
		List<ItemDTO> items = itemSalesStockService.getItemsByBizNumberStartingWith(bizNumber);

		model.addAttribute("itemList", items);
		model.addAttribute("directorName", directorName);

		return "salesStock/salesStockOutCreate"; // JSP 파일 경로 반환
	}
	
	
	@PostMapping("/salesStockOutCreate.do")
	public String createSalesStockOut(@RequestParam("itemName") String itemName,
			@RequestParam("createdOutAt") String createdOutAt, @RequestParam("stockOutPlace") String stockOutPlace,
			@RequestParam("stockOut") int stockOut, @RequestParam("outPrice") double outPrice,
			@RequestParam("itemCode") String itemCode, @RequestParam("oDirector") String oDirector,
			@RequestParam("paymentStatus") String paymentStatus, // paymentStatus 추가
			HttpSession session) {

		// 세션에서 biz_number와 uuid를 가져옴
		String bizNumber = (String) session.getAttribute("biz_number");
		String userUuid = (String) session.getAttribute("uuid");

		// ITEM 테이블 업데이트 로직
		itemSalesStockService.updateItemStockOut(itemCode, createdOutAt, stockOutPlace, stockOut, outPrice, oDirector);

		// createdOutAt을 LocalDate로 변환한 후 현재 시간을 추가
		LocalDate localDate = LocalDate.parse(createdOutAt);
		LocalDateTime localDateTime = localDate.atTime(LocalTime.now());

		// LocalDateTime을 Timestamp로 변환
		Timestamp stockOutDate = Timestamp.valueOf(localDateTime);

		// SALES_STOCK_OUT 테이블에 저장할 데이터 설정
		SalesStockOutDTO salesStockOutDTO = new SalesStockOutDTO();

		// S_STOCK_OUT_ID 생성: "OUT" + biz_number + 현재 타임스탬프
		String sStockOutId = bizNumber + System.currentTimeMillis() + "S"; // 타임스탬프 사용
		salesStockOutDTO.setsStockOutId(sStockOutId);
		salesStockOutDTO.setItemCode(itemCode);
		salesStockOutDTO.setsStockOutDate(stockOutDate);
		salesStockOutDTO.setsStockOutPlace(stockOutPlace);
		salesStockOutDTO.setsStockOutQty(stockOut);
		salesStockOutDTO.setUuid(userUuid);
		salesStockOutDTO.setsStockOutPrice(outPrice);
		salesStockOutDTO.setoDirector(oDirector);
		salesStockOutDTO.setPaymentStatus(paymentStatus); // 추가된 paymentStatus 설정

		// SALES_STOCK_OUT 테이블에 데이터 저장
		salesStockOutService.insertSalesStockOut(salesStockOutDTO);

		return "redirect:/salesStockOut.do"; // 등록 후 목록 페이지로 리다이렉트
	}

	@GetMapping("/getSalesOutDetails.do")
	public String getSalesOutDetails(@RequestParam("itemCode") String itemCode, Model model) {
		// ITEM 테이블에서 데이터 가져오기
		ItemDTO itemDetails = itemSalesStockService.getItemDetails(itemCode);

		// SALES_STOCK_OUT 테이블에서 데이터 가져오기
		List<SalesStockOutDTO> salesStockOutDetails = salesStockOutService.getSalesStockOutDetails(itemCode);

		// CREATED_AT에 9시간 추가하는 로직
		Timestamp createdAt = itemDetails.getCreatedAt();
		Timestamp adjustedCreatedAt = Timestamp.from(Instant.ofEpochMilli(createdAt.getTime() + 9 * 60 * 60 * 1000)); // 9시간
																														// 추가
		itemDetails.setCreatedAt(adjustedCreatedAt); // 조정된 Timestamp 설정

		// UPDATED_AT에 9시간 추가하는 로직
		Timestamp updatedAt = itemDetails.getUpdatedAt();
		if (updatedAt != null) {
			Timestamp adjustedUpdatedAt = Timestamp
					.from(Instant.ofEpochMilli(updatedAt.getTime() + 9 * 60 * 60 * 1000)); // 9시간 추가
			itemDetails.setUpdatedAt(adjustedUpdatedAt); // 조정된 Timestamp 설정
		} else {
			// updatedAt이 null일 경우, 아무 작업도 하지 않고 null로 유지합니다.
			itemDetails.setUpdatedAt(null); // 명시적으로 null로 설정 (선택 사항)
		}

		// 모델에 추가
		model.addAttribute("itemDetails", itemDetails);
		model.addAttribute("salesStockOutDetails", salesStockOutDetails);

		return "salesStock/salesStockOutDetail"; // JSP 파일 경로
	}

	@GetMapping("/getSalesOutDetailsSub.do")
	public String getSalesOutDetailsSub(@RequestParam("sStockOutId") String sStockOutId,
			@RequestParam("itemCode") String itemCode, Model model) {

		// SALES_STOCK_OUT 테이블에서 데이터 가져오기
		SalesStockOutDTO salesStockOutDetails = salesStockOutService.getSalesStockOutDetailsSub(sStockOutId);

		// ITEM 테이블에서 itemCode로 데이터 가져오기
		ItemDTO itemDetailsSub = itemSalesStockService.getItemDetails(itemCode);

		// 모델에 추가
		model.addAttribute("salesStockOutDetailsSub", salesStockOutDetails);
		model.addAttribute("itemDetailsSub", itemDetailsSub);

		return "salesStock/salesStockOutDetailSub"; // JSP 파일 경로
	}

	@GetMapping("/salesStockOutDetailSubUpdate.do")
	public String showSalesStockOutUpdateForm(@RequestParam("itemCode") String itemCode,
			@RequestParam("sStockId") String sStockOutId, Model model) {
		// ITEM 테이블에서 itemCode로 데이터 가져오기
		ItemDTO itemDetails = itemSalesStockService.getItemDetails(itemCode);

		// SALES_STOCK_OUT 테이블에서 sStockOutId로 데이터 가져오기
		SalesStockOutDTO salesStockOutDetails = salesStockOutService.getSalesStockOutDetailsSub(sStockOutId);

		// 모델에 추가하여 JSP로 전달
		model.addAttribute("itemDetails", itemDetails);
		model.addAttribute("salesStockOutDetails", salesStockOutDetails);

		// 수정 페이지로 이동
		return "salesStock/salesStockOutDetailSubUpdate";
	}

	@PostMapping("/updateSalesStockSubOut.do")
	public String updateSalesStockSubOut(@RequestParam("itemCode") String itemCode,
			@RequestParam("sStockOutId") String sStockOutId, @RequestParam("stockOut") int stockOutQty,
			@RequestParam("outPrice") double sStockOutPrice, @RequestParam("stockPlace") String sStockOutPlace,
			@RequestParam("paymentStatus") String paymentStatus, // paymentStatus 추가
			HttpSession session) {
		// 세션에서 UUID 가져오기
		String userUuid = (String) session.getAttribute("uuid");

		// 현재 시간을 사용하여 출고 수정 날짜 설정
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

		// DTO 설정
		SalesStockOutDTO salesStockOutDTO = new SalesStockOutDTO();
		salesStockOutDTO.setsStockOutId(sStockOutId);
		salesStockOutDTO.setsStockOutQty(stockOutQty);
		salesStockOutDTO.setsStockOutPrice(sStockOutPrice);
		salesStockOutDTO.setsStockOutPlace(sStockOutPlace);
		salesStockOutDTO.setUuid(userUuid); // 수정자 정보
		salesStockOutDTO.setsStockOutUpdate(currentTimestamp); // 수정 시간
		salesStockOutDTO.setPaymentStatus(paymentStatus); // paymentStatus 추가

		// 데이터베이스 업데이트 로직 호출
		salesStockOutService.updateSalesStockOut(salesStockOutDTO);

		// 1. SALES_STOCK_OUT 테이블에서 동일한 itemCode에 대한 S_STOCK_OUT_QTY 합계 구하기
		int totalStockOut = salesStockOutService.getTotalStockOutByItemCode(itemCode);

		// 2. ITEM 테이블의 STOCK_OUT 컬럼 업데이트
		itemSalesStockService.updateItemStockOutTotal(itemCode, totalStockOut);

		// 3. ITEM 테이블에서 STOCK_IN - STOCK_OUT 계산 후 STOCK 업데이트
		int stockIn = itemSalesStockService.getStockInByItemCode(itemCode);
		int updatedStock = stockIn - totalStockOut;
		itemSalesStockService.updateItemStock(itemCode, updatedStock);

		// 수정 완료 후 출고 목록 페이지로 리다이렉트
		return "redirect:/salesStockOut.do";
	}

	@PostMapping("/deleteSalesStockOut.do")
	public String deleteSalesStockOut(@RequestParam("sStockOutId") String sStockOutId,
			@RequestParam("itemCode") String itemCode) {
		// 1. SALES_STOCK_OUT 테이블에서 sStockOutId에 맞는 데이터 삭제
		salesStockOutService.deleteSalesStockOut(sStockOutId);

		// 2. SALES_STOCK_OUT 테이블에서 동일한 itemCode에 대한 S_STOCK_OUT_QTY 합계 구하기
		int totalStockOut = salesStockOutService.getTotalStockOutByItemCode(itemCode);

		// 3. ITEM 테이블의 STOCK_OUT 컬럼 업데이트
		itemSalesStockService.updateItemStockOutTotal(itemCode, totalStockOut);

		// 4. ITEM 테이블에서 STOCK_IN - STOCK_OUT 계산 후 STOCK 업데이트
		int stockIn = itemSalesStockService.getStockInByItemCode(itemCode);
		int updatedStock = stockIn - totalStockOut;
		itemSalesStockService.updateItemStock(itemCode, updatedStock);

		// 5. SALES_STOCK_OUT 테이블에서 가장 최근의 S_STOCK_OUT_DATE, S_STOCK_OUT_PRICE,
		// S_STOCK_OUT_PLACE 가져오기
		SalesStockOutDTO recentStockOut = salesStockOutService.getMostRecentStockOutDetails(itemCode);

		if (recentStockOut != null) {
			// 최근 출고 내역이 있을 경우
			// Date를 Timestamp로 변환 (필요한 경우)
			Timestamp latestOutDate = new Timestamp(recentStockOut.getsStockOutDate().getTime());

			// 6. ITEM 테이블의 CREATED_OUT_AT, OUT_PRICE, STOCK_OUT_PLACE 업데이트
			itemSalesStockService.updateItemWithLatestStockOut(itemCode, latestOutDate,
					recentStockOut.getsStockOutPrice(), recentStockOut.getsStockOutPlace());
		} else {
			// 7. SALES_STOCK_OUT 테이블에 데이터가 없으면 ITEM 테이블의 STOCK_OUT, OUT_PRICE,
			// STOCK_OUT_PLACE, CREATED_OUT_AT 컬럼을 초기화
			itemSalesStockService.resetItemStockOut(itemCode);
		}

		// 8. 수정이 완료된 후 출고 목록 페이지로 리다이렉트
		return "redirect:/salesStockOut.do";
	}

}
