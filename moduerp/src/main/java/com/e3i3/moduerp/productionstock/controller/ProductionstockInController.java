package com.e3i3.moduerp.productionstock.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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
	public String forwardProductionIn(Model model, HttpSession session) {
	    String bizNumber = (String) session.getAttribute("biz_number");
	    List<ItemDTO> itemList = itemProductionstockService.getItemsByBizNumber(bizNumber);

	 // CREATED_AT에 7시간 추가하는 로직 추가
	    for (ItemDTO item : itemList) {
	        // CREATED_AT 필드에서 Timestamp 값을 가져옴
	        Timestamp createdAt = item.getCreatedAt();
	        
	        // 7시간 추가
	        Timestamp adjustedTimestamp = Timestamp.from(Instant.ofEpochMilli(createdAt.getTime() + 9 * 60 * 60 * 1000));
	        
	        // Timestamp를 ItemDTO에 설정 (새로운 필드 추가 필요)
	        item.setCreatedAt(adjustedTimestamp); // 조정된 Timestamp 설정
	    }

	    model.addAttribute("itemList", itemList);
	    return "productionStock/productionStockIn"; // JSP 파일 경로 반환
	}

	@PostMapping("/productionStockInCreate.do")
	public String createProductionStockIn(@RequestParam("pStockInDate") String stockInDateStr,
			@RequestParam("stockPlace") String stockPlace, @RequestParam("stockIn") int stockIn,
			@RequestParam("itemName") String itemName, @RequestParam("itemDesc") 		String itemDesc,
			@RequestParam("inPrice") double inPrice, @RequestParam("materialType") List<String> materialType,
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
		Timestamp currentTimestampKST  = Timestamp.valueOf(nowKST.toLocalDateTime());
		// 콘솔에 출력
		System.out.println("한국 시간대의 현재 타임스탬프: " + currentTimestampKST);
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
		itemDTO.setItemList(materialType);
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
}
