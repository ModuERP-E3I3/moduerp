package com.e3i3.moduerp.productionstock.controller;

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

import com.e3i3.moduerp.item.model.dto.ItemDTO;
import com.e3i3.moduerp.item.model.service.ItemProductionstockService;
import com.e3i3.moduerp.productionstock.model.dto.ProductionStockInDTO;
import com.e3i3.moduerp.productionstock.service.ProductionStockInService;

@Controller
@RequestMapping("/")
public class ProductionstockInController {

	@Autowired
	private ProductionStockInService productionStockInService;

	@Autowired
	private ItemProductionstockService itemProductionstockService;

	// 기존 GET 메서드
	@RequestMapping(value = "/productionStockIn.do", method = RequestMethod.GET)
	public String forwardProductionIn(Model model, HttpSession session) {
		String bizNumber = (String) session.getAttribute("biz_number");
		List<ItemDTO> itemList = itemProductionstockService.getItemsByBizNumber(bizNumber);

		// CREATED_AT에 9시간 추가하는 로직 추가
		for (ItemDTO item : itemList) {
			// CREATED_AT 필드에서 Timestamp 값을 가져옴
			Timestamp createdAt = item.getCreatedAt();

			// 7시간 추가
			Timestamp adjustedTimestamp = Timestamp
					.from(Instant.ofEpochMilli(createdAt.getTime() + 9 * 60 * 60 * 1000));

			// Timestamp를 ItemDTO에 설정 (새로운 필드 추가 필요)
			item.setCreatedAt(adjustedTimestamp); // 조정된 Timestamp 설정
		}

		model.addAttribute("itemList", itemList);
		return "productionStock/productionStockIn"; // JSP 파일 경로 반환
	}

	@PostMapping("/productionStockInCreate.do")
	public String createProductionStockIn(@RequestParam("pStockInDate") String stockInDateStr,
			@RequestParam("stockPlace") String stockPlace, @RequestParam("stockIn") int stockIn,
			@RequestParam("itemName") String itemName, @RequestParam("itemDesc") String itemDesc,
			@RequestParam("inPrice") double inPrice, @RequestParam("materialType") List<String> materialType, // 수정된 부분
			HttpSession session) {

		// 세션에서 biz_number와 uuid를 가져옴
		String bizNumber = (String) session.getAttribute("biz_number");
		String userUuid = (String) session.getAttribute("uuid");
		// stockInDateStr를 LocalDate로 변환한 후 LocalDateTime으로 변환 (자정 시간 추가)
		LocalDate localDate = LocalDate.parse(stockInDateStr);
		LocalDateTime localDateTime = localDate.atStartOfDay(); // 자정 시간 추가
		Timestamp stockInDate = Timestamp.valueOf(localDateTime);

		// 한국 시간대의 현재 타임스탬프를 사용
		ZonedDateTime nowKST = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
		Timestamp currentTimestampKST = Timestamp.valueOf(nowKST.toLocalDateTime());

		// ITEM_CODE 생성: biz_number + "P" + 현재 타임스탬프
		String itemCode = bizNumber + "P" + currentTimestampKST.getTime();

		// ITEM 테이블에 저장할 데이터 설정
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setItemCode(itemCode);
		itemDTO.setItemName(itemName);
		itemDTO.setItemDesc(itemDesc);
		itemDTO.setCreatedAt(stockInDate); // 변환된 Timestamp 사용
		itemDTO.setStockPlace(stockPlace);
		itemDTO.setInPrice(inPrice);
		itemDTO.setBizNumber(bizNumber);
		itemDTO.setItemList(materialType); // List 형태로 설정
		itemDTO.setStockIn(stockIn);

		// ITEM 테이블에 데이터 저장
		itemProductionstockService.insertItem(itemDTO);

		// P_STOCK_IN_ID 생성: "P" + biz_number + 타임스탬프
		String pStockInId = "P" + bizNumber + currentTimestampKST.getTime();

		// PRODUCTION_STOCK_IN 테이블에 저장할 데이터 설정
		ProductionStockInDTO productionStockInDTO = new ProductionStockInDTO();
		productionStockInDTO.setpStockInId(pStockInId); // 생성된 P_STOCK_IN_ID 사용
		productionStockInDTO.setItemCode(itemCode);
		productionStockInDTO.setpStockInDate(stockInDate); // 변환된 Timestamp 사용
		productionStockInDTO.setpStockPlace(stockPlace);
		productionStockInDTO.setpStockInQty(stockIn);
		productionStockInDTO.setUuid(userUuid);

		// PRODUCTION_STOCK_IN 테이블에 데이터 저장
		productionStockInService.insertProductionStockIn(productionStockInDTO);

		return "redirect:/productionStockIn.do"; // 등록 후 목록 페이지로 리다이렉트
	}

	@GetMapping("/getProductionInDetails.do")
	public String getProductionInDetails(@RequestParam("itemCode") String itemCode, Model model) {
		// ITEM 테이블에서 데이터 가져오기
		ItemDTO itemDetails = itemProductionstockService.getItemDetails(itemCode);
		// PRODUCTION_STOCK_IN 테이블에서 데이터 가져오기
		ProductionStockInDTO productionStockInDetails = productionStockInService.getProductionStockInDetails(itemCode);

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
		model.addAttribute("productionStockInDetails", productionStockInDetails);

		return "productionStock/productionStockInDetail"; // JSP 파일 경로
	}

	@GetMapping("/productionStockInDetailUpdate.do")
	public String showUpdateForm(@RequestParam("itemCode") String itemCode, Model model, HttpSession session) {
		// ITEM 테이블에서 해당 itemCode의 데이터 가져오기
		ItemDTO itemDetails = itemProductionstockService.getItemDetails(itemCode);

		// CREATED_AT에 9시간 추가하는 로직
		Timestamp createdAt = itemDetails.getCreatedAt();
		Timestamp adjustedCreatedAt = Timestamp.from(Instant.ofEpochMilli(createdAt.getTime() + 9 * 60 * 60 * 1000)); // 9시간
																														// 추가
		itemDetails.setCreatedAt(adjustedCreatedAt); // 조정된 Timestamp 설정

		// PRODUCTION_STOCK_IN 테이블에서 데이터 가져오기
		ProductionStockInDTO productionStockInDetails = productionStockInService.getProductionStockInDetails(itemCode);

		// UPDATED_AT에 9시간 추가하는 로직
		Timestamp updatedAt = itemDetails.getUpdatedAt();
		if (updatedAt != null) {
			Timestamp adjustedUpdatedAt = Timestamp
					.from(Instant.ofEpochMilli(updatedAt.getTime() + 9 * 60 * 60 * 1000)); // 9시간 추가
			itemDetails.setUpdatedAt(adjustedUpdatedAt); // 조정된 Timestamp 설정
		} else {
			itemDetails.setUpdatedAt(null); // 명시적으로 null로 설정 (선택 사항)
		}

		String bizNumber = (String) session.getAttribute("biz_number");
		// biz_number로 item_name 목록을 가져옴
		List<String> itemNames = itemProductionstockService.getItemNamesByBizNumber(bizNumber);

		// 모델에 추가
		model.addAttribute("itemDetails", itemDetails);
		model.addAttribute("productionStockInDetails", productionStockInDetails);
		model.addAttribute("itemNames", itemNames);

		// 수정 페이지로 이동
		return "productionStock/productionStockInDetailUpdate"; // 수정할 JSP 파일 경로
	}

	@PostMapping("/updateProductionStockIn.do")
	public String updateProductionStockIn(@RequestParam("itemCode") String itemCode,
			@RequestParam("itemName") String itemName, @RequestParam("itemDesc") String itemDesc,
			@RequestParam("stockIn") int stockIn, @RequestParam("inPrice") double inPrice,
			@RequestParam("stockPlace") String stockPlace, @RequestParam("materialTypes") List<String> materialTypes) {
		// 현재 타임스탬프 생성
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
		// ITEM 테이블에 업데이트할 데이터 설정
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setItemCode(itemCode);
		itemDTO.setItemName(itemName);
		itemDTO.setItemDesc(itemDesc);
		itemDTO.setStockIn(stockIn);
		itemDTO.setInPrice(inPrice);
		itemDTO.setStockPlace(stockPlace);
		itemDTO.setItemList(materialTypes); // List 형태로 설정
		itemDTO.setUpdatedAt(currentTimestamp); // 타임스탬프 설정

		// ITEM 테이블 업데이트
		itemProductionstockService.updateItem(itemDTO);

		// PRODUCTION_STOCK_IN 테이블에 업데이트할 데이터 설정
		ProductionStockInDTO productionStockInDTO = new ProductionStockInDTO();
		productionStockInDTO.setItemCode(itemCode);
		productionStockInDTO.setpStockInQty(stockIn);
		productionStockInDTO.setpStockPlace(stockPlace);

		// PRODUCTION_STOCK_IN 테이블 업데이트
		productionStockInService.updateProductionStockIn(productionStockInDTO);

		return "redirect:/productionStockIn.do"; // 업데이트 후 목록 페이지로 리다이렉트
	}

}
