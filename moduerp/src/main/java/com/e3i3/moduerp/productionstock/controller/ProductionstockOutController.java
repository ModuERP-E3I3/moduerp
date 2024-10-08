package com.e3i3.moduerp.productionstock.controller;

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

import com.e3i3.moduerp.employee.model.service.EmployeeProductionService;
import com.e3i3.moduerp.item.model.dto.ItemDTO;
import com.e3i3.moduerp.item.model.service.ItemProductionstockService;

import com.e3i3.moduerp.productionstock.model.dto.ProductionStockOutDTO;
import com.e3i3.moduerp.productionstock.service.ProductionStockOutService;

@Controller
@RequestMapping("/")
public class ProductionstockOutController {

	@Autowired
	private ItemProductionstockService itemProductionstockService;
	@Autowired
	private ProductionStockOutService productionStockOutService;

	@Autowired
	private EmployeeProductionService employeeProductionService;

	@RequestMapping(value = "/productionStockOut.do", method = RequestMethod.GET)
	public String showProductionStockOut(@RequestParam(value = "page", defaultValue = "1") int page, Model model,
			HttpSession session) {
		String bizNumber = (String) session.getAttribute("biz_number");
		List<ItemDTO> itemList = itemProductionstockService.getItemsByBizNumberOutDate(bizNumber);

		// CREATED_AT에 9시간 추가하는 로직
		for (ItemDTO item : itemList) {
			// CREATED_AT 필드에서 Timestamp 값을 가져옴
			Timestamp createdAt = item.getCreatedAt();

			// 9시간 추가
			Timestamp adjustedTimestamp = Timestamp
					.from(Instant.ofEpochMilli(createdAt.getTime() + 9 * 60 * 60 * 1000));

			// Timestamp를 ItemDTO에 설정
			item.setCreatedAt(adjustedTimestamp); // 조정된 Timestamp 설정
		}

		// 페이지당 항목 수
		int itemsPerPage = 10;

		// 총 항목 수
		int totalItems = itemList.size();

		// 총 페이지 수
		int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);

		// 시작 인덱스 계산
		int startIndex = (page - 1) * itemsPerPage;
		int endIndex = Math.min(startIndex + itemsPerPage, totalItems);

		// 서브리스트 생성
		List<ItemDTO> paginatedList = itemList.subList(startIndex, endIndex);

		// 모델에 추가
		model.addAttribute("itemList", paginatedList);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", page);

		return "productionStock/productionStockOut"; // JSP 파일 경로 반환
	}

	// 등록 페이지로 이동
	@RequestMapping(value = "/productionStockOutCreate.do", method = RequestMethod.GET)
	public String showCreateForm(HttpSession session, Model model) {

		String bizNumber = (String) session.getAttribute("biz_number");
		String uuid = (String) session.getAttribute("uuid");

		String directorName = employeeProductionService.getEmployeeNameByUuid(uuid);

		// item_code가 biz_number + "P"로 시작하는 데이터 가져오기
		List<ItemDTO> items = itemProductionstockService.getItemsByBizNumberStartingWith(bizNumber);
		model.addAttribute("itemList", items); // item_name을 포함한 리스트
		model.addAttribute("directorName", directorName);

		return "productionStock/productionStockOutCreate"; // JSP 파일 경로 반환
	}

	@PostMapping("/productionStockOutCreate.do")
	public String createProductionStockOut(@RequestParam("itemName") String itemName,
			@RequestParam("createdOutAt") String createdOutAt, @RequestParam("stockOutPlace") String stockOutPlace,
			@RequestParam("stockOut") int stockOut, @RequestParam("outPrice") double outPrice,
			@RequestParam("itemCode") String itemCode, @RequestParam("oDirector") String oDirector,
			HttpSession session) {
		// 세션에서 biz_number와 uuid를 가져옴
		String bizNumber = (String) session.getAttribute("biz_number");
		String userUuid = (String) session.getAttribute("uuid");

		// ITEM 테이블 업데이트 로직
		itemProductionstockService.updateItemStockOut(itemCode, createdOutAt, stockOutPlace, stockOut, outPrice,
				oDirector);

		// createdOutAt을 LocalDate로 변환한 후 현재 시간을 추가
		LocalDate localDate = LocalDate.parse(createdOutAt);
		LocalDateTime localDateTime = localDate.atTime(LocalTime.now()); // 현재 시간을 추가

		// LocalDateTime을 Timestamp로 변환
		Timestamp stockOutDate = Timestamp.valueOf(localDateTime);

		// PRODUCTION_STOCK_OUT 테이블에 저장할 데이터 설정
		ProductionStockOutDTO productionStockOutDTO = new ProductionStockOutDTO();

		// P_STOCK_OUT_ID 생성: "OUT" + biz_number + 현재 타임스탬프
		String pStockOutId = bizNumber + System.currentTimeMillis() + "P"; // 타임스탬프 사용
		productionStockOutDTO.setpStockOutId(pStockOutId); // 생성된 P_STOCK_OUT_ID 사용
		productionStockOutDTO.setItemCode(itemCode);
		productionStockOutDTO.setpStockOutDate(stockOutDate); // 변환된 Timestamp 사용
		productionStockOutDTO.setpStockOutPlace(stockOutPlace);
		productionStockOutDTO.setpStockOutQty(stockOut);
		productionStockOutDTO.setUuid(userUuid);
		productionStockOutDTO.setpStockOutPrice(outPrice);
		productionStockOutDTO.setoDirector(oDirector);

		// PRODUCTION_STOCK_OUT 테이블에 데이터 저장
		productionStockOutService.insertProductionStockOut(productionStockOutDTO);

		return "redirect:/productionStockOut.do"; // 등록 후 목록 페이지로 리다이렉트
	}

	@GetMapping("/getProductionOutDetails.do")
	public String getProductionInDetails(@RequestParam("itemCode") String itemCode, Model model) {
		// ITEM 테이블에서 데이터 가져오기
		ItemDTO itemDetails = itemProductionstockService.getItemDetails(itemCode);

		// PRODUCTION_STOCK_OUT 테이블에서 데이터 가져오기
		List<ProductionStockOutDTO> productionStockOutDetails = productionStockOutService
				.getProductionStockOutDetails(itemCode);

		// 디버그 로그 출력
		System.out.println("ProductionStockOutDetails: " + productionStockOutDetails);

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
		model.addAttribute("productionStockOutDetails", productionStockOutDetails);

		return "productionStock/productionStockOutDetail"; // JSP 파일 경로
	}

	@GetMapping("/getProductionOutDetailsSub.do")
	public String getProductionInDetailsSub(@RequestParam("pStockOutId") String pStockOutId,
			@RequestParam("itemCode") String itemCode, Model model) {

		// PRODUCTION_STOCK_OUT 테이블에서 데이터 가져오기
		ProductionStockOutDTO productionStockOutDetails = productionStockOutService
				.getProductionStockOutDetailssSub(pStockOutId);

		// ITEM 테이블에서 itemCode로 데이터 가져오기
		ItemDTO itemDetailsSub = itemProductionstockService.getItemDetails(itemCode);

		// 모델에 추가
		model.addAttribute("productionStockOutDetailsSub", productionStockOutDetails);
		model.addAttribute("itemDetailsSub", itemDetailsSub);

		return "productionStock/productionStockOutDetailSub"; // JSP 파일 경로
	}

	@GetMapping("/productionStockOutDetailSubUpdate.do")
	public String showProductionStockOutUpdateForm(@RequestParam("itemCode") String itemCode,
			@RequestParam("pStockId") String pStockOutId, Model model) {
		// ITEM 테이블에서 itemCode로 데이터 가져오기
		ItemDTO itemDetails = itemProductionstockService.getItemDetails(itemCode);

		// PRODUCTION_STOCK_OUT 테이블에서 pStockOutId로 데이터 가져오기
		ProductionStockOutDTO productionStockOutDetails = productionStockOutService
				.getProductionStockOutDetailssSub(pStockOutId);

		// 모델에 추가하여 JSP로 전달
		model.addAttribute("itemDetails", itemDetails);
		model.addAttribute("productionStockOutDetails", productionStockOutDetails);

		// 수정 페이지로 이동
		return "productionStock/productionStockOutDetailSubUpdate"; // 수정 JSP 파일 경로
	}

	@PostMapping("/updateProductionStockSubOut.do")
	public String updateProductionStockSubOut(@RequestParam("itemCode") String itemCode,
			@RequestParam("pStockOutId") String pStockOutId, @RequestParam("stockIn") int stockOutQty,
			@RequestParam("inPrice") double pStockOutPrice, @RequestParam("stockPlace") String pStockOutPlace,
			HttpSession session) {
		// 세션에서 UUID 가져오기
		String userUuid = (String) session.getAttribute("uuid");

		// 현재 시간을 사용하여 출고 수정 날짜 설정
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

		// DTO 설정
		ProductionStockOutDTO productionStockOutDTO = new ProductionStockOutDTO();
		productionStockOutDTO.setpStockOutId(pStockOutId);
		productionStockOutDTO.setpStockOutQty(stockOutQty);
		productionStockOutDTO.setpStockOutPrice(pStockOutPrice);
		productionStockOutDTO.setpStockOutPlace(pStockOutPlace);
		productionStockOutDTO.setUuid(userUuid); // 수정자 정보
		productionStockOutDTO.setpStockOutUpdate(currentTimestamp); // 수정 시간

		// 데이터베이스 업데이트 로직 호출
		productionStockOutService.updateProductionStockOut(productionStockOutDTO);

		// 1. PRODUCTION_STOCK_OUT 테이블에서 동일한 itemCode에 대한 P_STOCK_OUT_QTY 합계 구하기
		int totalStockOut = productionStockOutService.getTotalStockOutByItemCode(itemCode);

		// 2. ITEM 테이블의 STOCK_OUT 컬럼 업데이트
		itemProductionstockService.updateItemStockOutTotal(itemCode, totalStockOut);

		// 3. ITEM 테이블에서 STOCK_IN - STOCK_OUT 계산 후 STOCK 업데이트
		int stockIn = itemProductionstockService.getStockInByItemCode(itemCode);
		int updatedStock = stockIn - totalStockOut;
		itemProductionstockService.updateItemStock(itemCode, updatedStock);

		// 수정 완료 후 출고 목록 페이지로 리다이렉트
		return "redirect:/productionStockOut.do";
	}

	@PostMapping("/deleteProductionStockOut.do")
	public String deleteProductionStockOut(@RequestParam("pStockOutId") String pStockOutId,
			@RequestParam("itemCode") String itemCode) {
		// 1. PRODUCTION_STOCK_OUT 테이블에서 pStockOutId에 맞는 데이터 삭제
		productionStockOutService.deleteProductionStockOut(pStockOutId);

		// 2. PRODUCTION_STOCK_OUT 테이블에서 동일한 itemCode에 대한 P_STOCK_OUT_QTY 합계 구하기
		int totalStockOut = productionStockOutService.getTotalStockOutByItemCode(itemCode);

		// 3. ITEM 테이블의 STOCK_OUT 컬럼 업데이트
		itemProductionstockService.updateItemStockOutTotal(itemCode, totalStockOut);

		// 4. ITEM 테이블에서 STOCK_IN - STOCK_OUT 계산 후 STOCK 업데이트
		int stockIn = itemProductionstockService.getStockInByItemCode(itemCode);
		int updatedStock = stockIn - totalStockOut;
		itemProductionstockService.updateItemStock(itemCode, updatedStock);

		// 5. PRODUCTION_STOCK_OUT 테이블에서 가장 최근의 P_STOCK_OUT_DATE, P_STOCK_OUT_PRICE,
		// P_STOCK_OUT_PLACE 가져오기
		ProductionStockOutDTO recentStockOut = productionStockOutService.getMostRecentStockOutDetails(itemCode);

		if (recentStockOut != null) {
			// 최근 출고 내역이 있을 경우
			// Date를 Timestamp로 변환 (필요한 경우)
			Timestamp latestOutDate = new Timestamp(recentStockOut.getpStockOutDate().getTime());

			// 6. ITEM 테이블의 CREATED_OUT_AT, OUT_PRICE, STOCK_OUT_PLACE 업데이트
			itemProductionstockService.updateItemWithLatestStockOut(itemCode, latestOutDate,
					recentStockOut.getpStockOutPrice(), recentStockOut.getpStockOutPlace());
		} else {
			// 7. PRODUCTION_STOCK_OUT 테이블에 데이터가 없으면 ITEM 테이블의 STOCK_OUT, OUT_PRICE,
			// STOCK_OUT_PLACE, CREATED_OUT_AT 컬럼을 초기화
			itemProductionstockService.resetItemStockOut(itemCode);
		}

		// 8. 수정이 완료된 후 출고 목록 페이지로 리다이렉트
		return "redirect:/productionStockOut.do";
	}

}
