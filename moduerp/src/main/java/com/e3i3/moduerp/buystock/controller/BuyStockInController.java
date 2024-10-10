package com.e3i3.moduerp.buystock.controller;

import java.sql.Timestamp;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.e3i3.moduerp.item.model.dto.ItemDTO;
import com.e3i3.moduerp.item.model.service.ItemBuyStockService;
import com.e3i3.moduerp.buystock.model.dto.BuyStockInDTO;
import com.e3i3.moduerp.buystock.model.service.BuyStockInService;

@Controller
@RequestMapping("/")
public class BuyStockInController {
	

		@Autowired
		private BuyStockInService BuyStockInService;
		
		@Autowired
		private ItemBuyStockService itembuyStockService;

		@RequestMapping(value = "/buyStockIn.do", method = RequestMethod.GET)
		public String forwardBuyIn(@RequestParam(value = "page", defaultValue = "1") int page, Model model,
		        HttpSession session) {
		    String bizNumber = (String) session.getAttribute("biz_number");
		   

		    List<ItemDTO> itemList = itembuyStockService.getItemsByBizNumber(bizNumber);
		   

				// 
				Timestamp adjustedTimestamp = Timestamp
						.from(Instant.ofEpochMilli(createdAt.getTime() + 9 * 60 * 60 * 1000));

				// 
				item.setCreatedAt(adjustedTimestamp); // 
			}

			// 
			int itemsPerPage = 10;

			//
			int totalItems = itemList.size();

			// 
			int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);

			// 꾩궛
			int startIndex = (page - 1) * itemsPerPage;
			int endIndex = Math.min(startIndex + itemsPerPage, totalItems);

			// 꽦
			List<ItemDTO> paginatedList = itemList.subList(startIndex, endIndex);

			// 
			model.addAttribute("itemList", paginatedList);
			model.addAttribute("totalPages", totalPages);
			model.addAttribute("currentPage", page);

			return "buyStock/buyStockIn"; // JSP
		}
		
		@PostMapping("/buyStockInCreate.do")
		public String createBuyStockIn(@RequestParam("bStockInDate") String stockInDateStr,
				@RequestParam("stockPlace") String stockPlace, 
				@RequestParam("stockIn") int stockIn,
				@RequestParam("itemName") String itemName, 
				@RequestParam("itemDesc") String itemDesc,
				@RequestParam("inPrice") double inPrice,
				@RequestParam("accountNo") String accountNo,
				@RequestParam("iDirrector") String iDirrector,
				HttpSession session) {
			
			// 샂
			String bizNumber = (String) session.getAttribute("biz_number");
			String userUuid = (String) session.getAttribute("uuid");
			
			// 
			LocalDate localDate = LocalDate.parse(stockInDateStr);
			LocalDateTime localDateTime = localDate.atStartOfDay(); // 
			Timestamp stockInDate = Timestamp.valueOf(localDateTime);

			// 한국 시간대의 현재 타임스탬프를 사용
			ZonedDateTime nowKST = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
			Timestamp currentTimestampKST = Timestamp.valueOf(nowKST.toLocalDateTime());

			// ITEM_CODE 생성: biz_number + "B" + 현재 타임스탬프
			String itemCode = bizNumber + "B" + currentTimestampKST.getTime();

			// ITEM 테이블에 저장할 데이터 설정
			ItemDTO itemDTO = new ItemDTO();
			itemDTO.setItemCode(itemCode);
			itemDTO.setItemName(itemName);
			itemDTO.setItemDesc(itemDesc);
			itemDTO.setCreatedAt(stockInDate); // 
			itemDTO.setStockPlace(stockPlace);
			itemDTO.setInPrice(inPrice);
			itemDTO.setBizNumber(bizNumber);
			itemDTO.setItemList(materialType); // 젙
			itemDTO.setStockIn(stockIn);
			itemDTO.setAccountName(accountNo);
			itemDTO.setiDirector(iDirrector);
			

			// ITEM
			itembuyStockService.insertItem(itemDTO);

			// B_STOCK_IN_ID: "B" + biz_number 
			String bStockInId = "B" + bizNumber + currentTimestampKST.getTime();

			//  테이블에 저장할 데이터 설정
			BuyStockInDTO buyStockInDTO = new BuyStockInDTO();
			buyStockInDTO.setbStockInId(bStockInId); // 맂 B_STOCK_IN_ID 
			buyStockInDTO.setItemCode(itemCode);
			buyStockInDTO.setbStockInDate(stockInDate); // Timestamp
			buyStockInDTO.setAccountNo(stockPlace);
			buyStockInDTO.setbStockInQty(stockIn);
			buyStockInDTO.setUuid(userUuid);

			//  
			BuyStockInService.insertBuyStockIn(buyStockInDTO);

			return "redirect:/buyStockIn.do"; // 
		}

		@GetMapping("/getBuyInDetails.do")
		public String getBuyInDetails(@RequestParam("itemCode") String itemCode, Model model) {
			// ITEM 
			ItemDTO itemDetails = itembuyStockService.getItemDetails(itemCode);
			// Buy_STOCK_IN 
			BuyStockInDTO BuyStockInDetails = BuyStockInService.getBuyStockInDetails(itemCode);

			// CREATED_AT
			Timestamp createdAt = itemDetails.getCreatedAt();
			Timestamp adjustedCreatedAt = Timestamp.from(Instant.ofEpochMilli(createdAt.getTime() + 9 * 60 * 60 * 1000)); // 9�떆媛�
																															// 異붽�
			itemDetails.setCreatedAt(adjustedCreatedAt); // Timestamp

			// UPDATED_AT
			Timestamp updatedAt = itemDetails.getUpdatedAt();
			if (updatedAt != null) {
				Timestamp adjustedUpdatedAt = Timestamp
						.from(Instant.ofEpochMilli(updatedAt.getTime() + 9 * 60 * 60 * 1000)); 
				itemDetails.setUpdatedAt(adjustedUpdatedAt); // 맂Timestamp 
			} else {
				// updatedAt
				itemDetails.setUpdatedAt(null); 
			}

			// 
			model.addAttribute("itemDetails", itemDetails);
			model.addAttribute("BuyStockInDetails", BuyStockInDetails);

			return "BuyStock/BuyStockInDetail"; // JSP 
		}
		
		@GetMapping("/BuyStockInDetailUpdate.do")
		public String showUpdateForm(@RequestParam("itemCode") String itemCode, Model model, HttpSession session) {
			// ITEM itemCode
			ItemDTO itemDetails = itembuyStockService.getItemDetails(itemCode);

			// CREATED_AT
			Timestamp createdAt = itemDetails.getCreatedAt();
			Timestamp adjustedCreatedAt = Timestamp.from(Instant.ofEpochMilli(createdAt.getTime() + 9 * 60 * 60 * 1000)); 
																															
			itemDetails.setCreatedAt(adjustedCreatedAt);  

			// Buy_STOCK_IN 
			BuyStockInDTO BuyStockInDetails = BuyStockInService.getBuyStockInDetails(itemCode);

			// UPDATED_AT뿉쭅
			Timestamp updatedAt = itemDetails.getUpdatedAt();
			if (updatedAt != null) {
				Timestamp adjustedUpdatedAt = Timestamp
						.from(Instant.ofEpochMilli(updatedAt.getTime() + 9 * 60 * 60 * 1000));
				itemDetails.setUpdatedAt(adjustedUpdatedAt); // Timestamp
			} else {
				itemDetails.setUpdatedAt(null); 
			}

			String bizNumber = (String) session.getAttribute("biz_number");
			// biz_number
			List<String> itemNames = itembuyStockService.getItemNamesByBizNumber(bizNumber);

			
			model.addAttribute("itemDetails", itemDetails);
			model.addAttribute("BuyStockInDetails", BuyStockInDetails);
			model.addAttribute("itemNames", itemNames);

			
			return "BuyStock/BuyStockInDetailUpdate"; 
		}
		
		@PostMapping("/updateBuyStockIn.do")
		public String updateBuyStockIn(@RequestParam("itemCode") String itemCode,
				@RequestParam("itemName") String itemName, @RequestParam("itemDesc") String itemDesc,
				@RequestParam("stockIn") int stockIn, @RequestParam("inPrice") double inPrice,
				@RequestParam("stockPlace") String stockPlace, @RequestParam("materialTypes") List<String> materialTypes) {

			
			Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

			// DB에서 현재 아이템 정보 가져오기
			ItemDTO currentItem = itembuyStockService.getItemDetails(itemCode);

			// stockOut이 null일 경우 0으로 설정
			Integer stockOut = currentItem.getStockOut() != null ? currentItem.getStockOut() : 0;

			// STOCK 계산: STOCK_IN - STOCK_OUT
			int stock = stockIn - stockOut;

			// ItemDTO 객체 생성 및 값 설정
			ItemDTO itemDTO = new ItemDTO();
			itemDTO.setItemCode(itemCode);
			itemDTO.setItemName(itemName);
			itemDTO.setItemDesc(itemDesc);
			itemDTO.setStockIn(stockIn);
			itemDTO.setStock(stock); // 현재 재고 설정
			itemDTO.setInPrice(inPrice);
			itemDTO.setStockPlace(stockPlace);
			itemDTO.setItemList(materialTypes); // List 설정
			itemDTO.setUpdatedAt(currentTimestamp);   // 현재 타임스탬프 설정

			// ITEM 테이블 업데이트
			itembuyStockService.updateItem(itemDTO);

			// BuyStockInDTO 객체 생성 및 값 설정
			BuyStockInDTO BuyStockInDTO = new BuyStockInDTO();
			BuyStockInDTO.setItemCode(itemCode);
			BuyStockInDTO.setbStockInQty(stockIn);
			BuyStockInDTO.setbStockInPlace(stockPlace);

			// BuyStockIn 테이블 업데이트
			BuyStockInService.updateBuyStockIn(BuyStockInDTO);

			return "redirect:/BuyStockIn.do";  // 업데이트 후 재고 목록 페이지로 리다이렉트
		}

		@PostMapping("/deleteBuyStockIn.do")
		public String deleteBuyStockIn(@RequestParam("itemCode") String itemCode, HttpSession session) {
		    // 1. 먼저 BuyStockIn 테이블에서 해당 아이템 삭제
		    BuyStockInService.deleteBuyStockInByItemCode(itemCode);

		    // 2. 그 후 ITEM 테이블에서 해당 아이템 삭제
		    itembuyStockService.deleteItemByCode(itemCode);

		    return "redirect:/buyStockIn.do"; // 삭제 후 재고 목록 페이지로 리다이렉트
		}



		
}
