package com.e3i3.moduerp.productionstock.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public String forwardProductionIn(Model model) {
		List<ProductionStockInDTO> stockList = productionStockInService.getAllProductionStockIn();
		model.addAttribute("stockList", stockList);
		return "productionStock/productionStockIn"; // JSP 파일 경로 반환
	}

	@PostMapping("/productionStockInCreate.do")
	public String createProductionStockIn(@RequestParam("pStockInDate") String stockInDateStr,
			@RequestParam("stockPlace") String stockPlace, @RequestParam("stockInQty") int stockInQty,
			@RequestParam("itemName") String itemName, @RequestParam("itemDesc") String itemDesc,
			@RequestParam("inPrice") double inPrice, @RequestParam("materialType") List<String> materialType,
			HttpSession session) {

		// 세션에서 biz_number와 uuid를 가져옴
		String bizNumber = (String) session.getAttribute("biz_number");
		String userUuid = (String) session.getAttribute("uuid");

		// ITEM_CODE 생성: biz_number + "P" + 현재 타임스탬프
		String itemCode = bizNumber + "P" + new Timestamp(System.currentTimeMillis()).getTime();

		// stockInDateStr를 LocalDate로 변환한 후 LocalDateTime으로 변환 (자정 시간 추가)
		LocalDate localDate = LocalDate.parse(stockInDateStr);
		LocalDateTime localDateTime = localDate.atStartOfDay(); // 자정 시간 추가
		Timestamp stockInDate = Timestamp.valueOf(localDateTime);

		// ITEM 테이블에 저장할 데이터 설정
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setItemCode(itemCode);
		itemDTO.setItemName(itemName);
		itemDTO.setItemDesc(itemDesc);

		// TOTAL_STOCK_QTY 계산 및 설정
		// 처음 입력될 경우 TOTAL_STOCK_QTY는 0으로 시작
		int totalStockQty = 0;

		// 가격과 수량을 기반으로 TOTAL_STOCK_QTY 계산
		totalStockQty += stockInQty; // 현재 입고 수량을 TOTAL_STOCK_QTY에 추가
		itemDTO.setTotalStockQty(totalStockQty); // TOTAL_STOCK_QTY 설정

		itemDTO.setCreatedAt(stockInDate); // 변환된 Timestamp 사용
		itemDTO.setStockPlace(stockPlace);
		itemDTO.setInPrice(inPrice);
		itemDTO.setBizNumber(bizNumber);
		itemDTO.setItemList(materialType);

		// ITEM 테이블에 데이터 저장
		itemProductionstockService.insertItem(itemDTO);

		// PRODUCTION_STOCK_IN 테이블에 저장할 데이터 설정
		ProductionStockInDTO productionStockInDTO = new ProductionStockInDTO();
		productionStockInDTO.setPStockInId(itemCode); // ITEM_CODE 사용
		productionStockInDTO.setItemCode(itemCode);
		productionStockInDTO.setPStockInDate(stockInDate); // 변환된 Timestamp 사용
		productionStockInDTO.setPStockPlace(stockPlace);
		productionStockInDTO.setPStockInQty(stockInQty);
		productionStockInDTO.setUUID(userUuid);

		// PRODUCTION_STOCK_IN 테이블에 데이터 저장
		productionStockInService.insertProductionStockIn(productionStockInDTO);

		return "redirect:/productionStockIn.do"; // 등록 후 목록 페이지로 리다이렉트
	}

}
