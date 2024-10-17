package com.e3i3.moduerp.buystock.controller;

import java.sql.Date;
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

import com.e3i3.moduerp.employee.model.service.EmployeeBuyService;
import com.e3i3.moduerp.item.model.dto.ItemDTO;
import com.e3i3.moduerp.item.model.service.ItemBuyStockService;

import com.e3i3.moduerp.buystock.model.dto.BuyStockOutDTO;
import com.e3i3.moduerp.buystock.model.service.BuyStockOutService;

@Controller
@RequestMapping("/")
public class BuyStockOutController {

	@Autowired
	private ItemBuyStockService itemBuystockService;
	@Autowired
	private BuyStockOutService buyStockOutService;

	@Autowired
	private EmployeeBuyService employeeBuyService;

	@RequestMapping(value = "/buyStockOut.do", method = RequestMethod.GET)
	public String showBuyStockOut(@RequestParam(value = "page", defaultValue = "1") int page, Model model,
			HttpSession session) {
		String bizNumber = (String) session.getAttribute("biz_number");
		List<ItemDTO> itemList = itemBuystockService.getItemsByBizNumberOutDate(bizNumber);

		// CREATED_AT에 9시간 추가하는 로직
		for (ItemDTO item : itemList) {
			// CREATED_AT 필드에서 Timestamp 값을 가져옴
			Timestamp createdAt = item.getCreatedAt();

			// 9시간 추가
			Timestamp adjustedTimestamp = Timestamp
					.from(Instant.ofEpochMilli(createdAt.getTime() + 9 * 60 * 60 * 1000));

			// Timestamp를 ItemDTO에 설정
			item.setCreatedAt(adjustedTimestamp);
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

		return "buyStock/buyStockOut"; // JSP 파일 경로 반환
	}
	
	@RequestMapping(value = "/buyStockOutFilter.do", method = RequestMethod.GET)
	public String forwardBuyInFilter(@RequestParam(value = "page", defaultValue = "1") int page,
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
				itemList = itemBuystockService.getItemByFilterDate(bizNumber, option, filterText, startDate, endDate);
			} else if ((option == null || filterText == null || filterText.isEmpty()) && startDate != null
					&& !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
				// 날짜만 있는 경우 처리
				System.out.println("날짜만 있는 경우 실행");
				itemList = itemBuystockService.getItemByFilterOnlyDate(bizNumber, startDate, endDate);
			} else if (startDate != null && !startDate.isEmpty() && (endDate == null || endDate.isEmpty())) {
				// 시작 날짜만 있는 경우 처리
				System.out.println("시작 날짜만 있는 경우 실행");
				itemList = itemBuystockService.getItemByFilterStartDate(bizNumber, startDate);
			} else if ((startDate == null || startDate.isEmpty()) && endDate != null && !endDate.isEmpty()) {
				// 종료 날짜만 있는 경우 처리
				System.out.println("종료 날짜만 있는 경우 실행");
				itemList = itemBuystockService.getItemByFilterEndDate(bizNumber, endDate);
			} else {
				System.out.println("날짜없는거 실행");
				itemList = itemBuystockService.getItemsByFilter(bizNumber, option, filterText);
			}
		} else if ((option == null || filterText == null || filterText.isEmpty()) && startDate != null
				&& !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
			// 필터 옵션과 텍스트 없이 날짜만 있는 경우
			System.out.println("날짜만 있는 경우 실행");
			itemList = itemBuystockService.getItemByFilterOnlyDate(bizNumber, startDate, endDate);
		} else if (startDate != null && !startDate.isEmpty() && (endDate == null || endDate.isEmpty())) {
			// 시작 날짜만 있는 경우 처리
			System.out.println("시작 날짜만 있는 경우 실행");
			itemList = itemBuystockService.getItemByFilterStartDate(bizNumber, startDate);
		} else if ((startDate == null || startDate.isEmpty()) && endDate != null && !endDate.isEmpty()) {
			// 종료 날짜만 있는 경우 처리
			System.out.println("종료 날짜만 있는 경우 실행");
			itemList = itemBuystockService.getItemByFilterEndDate(bizNumber, endDate);
		} else {
			itemList = itemBuystockService.getItemsByBizNumber(bizNumber);
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

		return "buyStock/buyStockOutFilter"; // JSP 파일 경로 반환
	}

	// 등록 페이지로 이동(buyStockOutCreate.do)
	@RequestMapping(value = "/buyStockOutCreate.do", method = RequestMethod.GET)
	public String showCreateForm(HttpSession session, Model model) {

		String bizNumber = (String) session.getAttribute("biz_number");
		String uuid = (String) session.getAttribute("uuid");

		String directorName = employeeBuyService.getEmployeeNameByUuid(uuid);

		// item_code가 biz_number + "B"로 시작하는 데이터 가져오기
		List<ItemDTO> items = itemBuystockService.getItemsByBizNumberStartingWith(bizNumber);
		model.addAttribute("itemList", items); // item_name을 포함한 리스트
		model.addAttribute("directorName", directorName);

		return "buyStock/buyStockOutCreate"; // JSP 파일 경로 반환
	}

	@PostMapping("/buyStockOutCreate.do")
	public String createBuyStockOut(@RequestParam("itemName") String itemName,
	        @RequestParam("createdOutAt") String createdOutAt, @RequestParam("stockOutPlace") String stockOutPlace,
	        @RequestParam("stockOut") int stockOut, @RequestParam("outPrice") double outPrice,
	        @RequestParam("itemCode") String itemCode, @RequestParam("oDirector") String oDirector,
	        HttpSession session) {
	    // 세션에서 biz_number와 uuid를 가져옴

	    String bizNumber = (String) session.getAttribute("biz_number");
	    String userUuid = (String) session.getAttribute("uuid");
		

	    // ITEM 테이블 업데이트 로직
	    itemBuystockService.updateItemStockOut(itemCode, createdOutAt, stockOutPlace, stockOut, outPrice,
	            oDirector);

	    // createdOutAt을 LocalDate로 변환한 후 현재 시간을 추가
	    LocalDate parsedDate = LocalDate.parse(createdOutAt);
	    LocalTime currentTime = LocalTime.now();
	    LocalDateTime combinedDateTime = LocalDateTime.of(parsedDate, currentTime);
	    // LocalDateTime을 Timestamp로 변환
	    Timestamp stockOutDate = Timestamp.valueOf(combinedDateTime);

	    // BUY_STOCK_OUT 테이블에 저장할 데이터 설정
	    BuyStockOutDTO buyStockOutDTO = new BuyStockOutDTO();

	    // B_STOCK_OUT_ID 생성: "OUT" + biz_number + 현재 타임스탬프
	    String bStockOutId = bizNumber + System.currentTimeMillis() + "B"; // 타임스탬프 사용
	    buyStockOutDTO.setbStockOutId(bStockOutId); // 생성된 B_STOCK_OUT_ID 사용
	    buyStockOutDTO.setItemCode(itemCode);
	    buyStockOutDTO.setbStockOutDate(stockOutDate); // 변환된 Date 사용
	    buyStockOutDTO.setbStockOutPlace(stockOutPlace);
	    buyStockOutDTO.setbStockOutQty(stockOut);
	    buyStockOutDTO.setUuid(userUuid);
	    buyStockOutDTO.setbStockOutPrice(outPrice);
	    buyStockOutDTO.setoDirector(oDirector);

	    // BUY_STOCK_OUT 테이블에 데이터 저장
	    buyStockOutService.insertBuyStockOut(buyStockOutDTO);

	    return "redirect:/buyStockOut.do"; // 등록 후 목록 페이지로 리다이렉트
	}


	
	@GetMapping("/getBuyOutDetails.do")
	public String getBuyInDetails(@RequestParam("itemCode") String itemCode, Model model) {
		// ITEM 테이블에서 데이터 가져오기
		ItemDTO itemDetails = itemBuystockService.getItemDetails(itemCode);
		// BUY_STOCK_OUT 테이블에서 데이터 가져오기
		List<BuyStockOutDTO> buyStockOutDetails = buyStockOutService
				.getBuyStockOutDetails(itemCode);

		// 디버그 로그 출력 안뜨는지 확인 필요
		System.out.println("BuyStockOutDetails: " + buyStockOutDetails);
		
		// CREATED_AT에 9시간 추가하는 로직
		Timestamp createdAt = itemDetails.getCreatedAt();
		Timestamp adjustedCreatedAt = Timestamp.from(Instant.ofEpochMilli(createdAt.getTime() + 9 * 60 * 60 * 1000)); // 9시간
									
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
		model.addAttribute("buyStockOutDetails", buyStockOutDetails);

		return "buyStock/buyStockOutDetail"; // JSP 파일 경로
	}
	
	//
	@GetMapping("/getBuyOutDetailsSub.do")
	public String getBuyInDetailsSub(@RequestParam("bStockOutId") String bStockOutId,
			@RequestParam("itemCode") String itemCode, Model model) {

		BuyStockOutDTO buyStockOutDetails = buyStockOutService
				.getBuyStockOutDetailssSub(bStockOutId);

		// ITEM 테이블에서 itemCode로 데이터 가져오기
		ItemDTO itemDetailsSub = itemBuystockService.getItemDetails(itemCode);

		// 모델에 추가
		model.addAttribute("buyStockOutDetailsSub", buyStockOutDetails);
		model.addAttribute("itemDetailsSub", itemDetailsSub);

		return "buyStock/buyStockOutDetailSub"; // JSP 파일 경로
	}
	
	@GetMapping("/buyStockOutDetailSubUpdate.do")
	public String showBuyStockOutUpdateForm(@RequestParam("itemCode") String itemCode,
			@RequestParam("bStockId") String bStockOutId, Model model) {
		// ITEM 테이블에서 itemCode로 데이터 가져오기
		ItemDTO itemDetails = itemBuystockService.getItemDetails(itemCode);

		//  테이블에서 bStockOutId로 데이터 가져오기
		BuyStockOutDTO buyStockOutDetails = 
				buyStockOutService.getBuyStockOutDetailssSub(bStockOutId);

		// 모델에 추가하여 JSP로 전달
		model.addAttribute("itemDetails", itemDetails);
		model.addAttribute("buyStockOutDetails", buyStockOutDetails);

		// 수정 페이지로 이동
		return "buyStock/buyStockOutDetailSubUpdate"; // 수정 JSP 파일 경로
	}

	
	@PostMapping("/updateBuyStockSubOut.do")
	public String updateBuyStockSubOut(@RequestParam("itemCode") String itemCode,
			@RequestParam("bStockOutId") String bStockOutId, @RequestParam("stockIn") int stockOutQty,
			@RequestParam("inPrice") double bStockOutPrice, @RequestParam("stockPlace") String bStockOutPlace,
			HttpSession session) {
		// 세션에서 UUID 가져오기
		String userUuid = (String) session.getAttribute("uuid");

		// 현재 시간을 사용하여 출고 수정 날짜 설정
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

		// DTO 설정
		BuyStockOutDTO buyStockOutDTO = new BuyStockOutDTO();
		buyStockOutDTO.setbStockOutId(bStockOutId);
		buyStockOutDTO.setbStockOutQty(stockOutQty);
		buyStockOutDTO.setbStockOutPrice(bStockOutPrice);
		buyStockOutDTO.setbStockOutPlace(bStockOutPlace);
		buyStockOutDTO.setUuid(userUuid); // 수정자 정보
		buyStockOutDTO.setbStockOutUpdate(currentTimestamp); // 수정 시간

		// 데이터베이스 업데이트 로직 호출
		buyStockOutService.updateBuyStockOut(buyStockOutDTO);

		// 1. BUY_STOCK_OUT 테이블에서 동일한 itemCode에 대한 B_STOCK_OUT_QTY 합계 구하기
		int totalStockOut = buyStockOutService.getTotalStockOutByItemCode(itemCode);

		// 2. ITEM 테이블의 STOCK_OUT 컬럼 업데이트
		itemBuystockService.updateItemStockOutTotal(itemCode, totalStockOut);

		// 3. ITEM 테이블에서 STOCK_IN - STOCK_OUT 계산 후 STOCK 업데이트
		int stockIn = itemBuystockService.getStockInByItemCode(itemCode);
		int updatedStock = stockIn - totalStockOut;
		itemBuystockService.updateItemStock(itemCode, updatedStock);

		// 수정 완료 후 출고 목록 페이지로 리다이렉트
		return "redirect:/buyStockOut.do";
	}
	
	@PostMapping("/deleteBuyStockOut.do")
	public String deleteBuyStockOut(@RequestParam("bStockOutId") String bStockOutId,
			@RequestParam("itemCode") String itemCode) {
		// 1. BUY 테이블에서 StockOutId에 맞는 데이터 삭제
		buyStockOutService.deleteBuyStockOut(bStockOutId);

		// 2. BUY 테이블에서 동일한 itemCode에 대한 P_STOCK_OUT_QTY 합계 구하기
		int totalStockOut = buyStockOutService.getTotalStockOutByItemCode(itemCode);

		// 3. ITEM 테이블의 STOCK_OUT 컬럼 업데이트
		itemBuystockService.updateItemStockOutTotal(itemCode, totalStockOut);

		// 4. ITEM 테이블에서 STOCK_IN - STOCK_OUT 계산 후 STOCK 업데이트
		int stockIn = itemBuystockService.getStockInByItemCode(itemCode);
		int updatedStock = stockIn - totalStockOut;
		itemBuystockService.updateItemStock(itemCode, updatedStock);

		// 5.  테이블에서 가장 최근의 P_STOCK_OUT_DATE, P_STOCK_OUT_PRICE,
		// STOCK_OUT_PLACE 가져오기
		BuyStockOutDTO recentStockOut = buyStockOutService.getMostRecentStockOutDetails(itemCode);

		if (recentStockOut != null) {
			// 최근 출고 내역이 있을 경우
			// Date를 Timestamp로 변환 (필요한 경우)
			Timestamp latestOutDate = new Timestamp(recentStockOut.getbStockOutDate().getTime());

			// 6. ITEM 테이블의 CREATED_OUT_AT, OUT_PRICE, STOCK_OUT_PLACE 업데이트
			itemBuystockService.updateItemWithLatestStockOut(itemCode, latestOutDate,
					recentStockOut.getbStockOutPrice(), recentStockOut.getbStockOutPlace());
		} else {
			// 7.  테이블에 데이터가 없으면 ITEM 테이블의 STOCK_OUT, OUT_PRICE,
			// STOCK_OUT_PLACE, CREATED_OUT_AT 컬럼을 초기화
			itemBuystockService.resetItemStockOut(itemCode);
		}

		// 8. 수정이 완료된 후 출고 목록 페이지로 리다이렉트
		return "redirect:/buyStockOut.do";
	}
	
	
	
		
// 기존 리스트 불러오는 구문
//	@Autowired
//    private BuyStockOutService buyStockOutService;
//
//	@RequestMapping("/buyStockOut.do")
//	public String showBuyStockOut(Model model) {
//		List<BuyStockOutDTO> stockOutList = buyStockOutService.getAllBuyStockOuts();
//		model.addAttribute("stockOutList", stockOutList);
//		return "buyStock/buyStockOut"; 
//	}

}
