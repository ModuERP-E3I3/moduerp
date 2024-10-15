package com.e3i3.moduerp.delivery.controller;

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

import com.e3i3.moduerp.buystock.model.dto.BuyStockOutDTO;
import com.e3i3.moduerp.delivery.model.dto.DeliveryDTO;
import com.e3i3.moduerp.delivery.model.service.DeliveryService;
import com.e3i3.moduerp.item.model.dto.ItemDTO;
import com.e3i3.moduerp.item.model.service.ItemDeliveryService;



@Controller
@RequestMapping("/")
public class DeliveryController {
	

		@Autowired
		private DeliveryService DeliveryService;
		
		@Autowired
		private ItemDeliveryService itemDeliveryService;		

		@RequestMapping(value = "/delivery.do", method = RequestMethod.GET)
		public String forwardDelivery(@RequestParam(value = "page", defaultValue = "1") int page, Model model,
		        HttpSession session) {
		    String bizNumber = (String) session.getAttribute("biz_number");
		    //List<DeliveryDTO> deliveryTableList = DeliveryService.getAllDelivery(bizNumber);

		    List<ItemDTO> itemList = itemDeliveryService.getItemsByBizNumber(bizNumber);
		    
		   

		    
		    for (ItemDTO item : itemList) {
		        if (item.getCreatedAt() != null) {
		            Timestamp createdAt = item.getCreatedAt();
		            Timestamp adjustedTimestamp = Timestamp
		                    .from(Instant.ofEpochMilli(createdAt.getTime() + 9 * 60 * 60 * 1000));
		            item.setCreatedAt(adjustedTimestamp);
		        }
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

		    return "delivery/delivery"; // JSP
		}

		@RequestMapping(value = "/deliveryFilter.do", method = RequestMethod.GET)
		public String forwardBuyInFilter(@RequestParam(value = "page", defaultValue = "1") int page,
				@RequestParam(value = "filterOption", required = false) String option,
				@RequestParam(value = "filterText", required = false) String filterText,
				@RequestParam(value = "startDate", required = false) String startDate,
				@RequestParam(value = "endDate", required = false) String endDate, Model model, HttpSession session) {
			String bizNumber = (String) session.getAttribute("biz_number");
			List<ItemDTO> itemList;

			
			if (option != null && filterText != null) {
				if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
					System.out.println("날짜있는거 실행");
					itemList = itemDeliveryService.getItemByFilterDate(bizNumber, option, filterText, startDate,
							endDate);
				} else if (startDate == null || startDate.isEmpty()) {
					System.out.println("날짜없는거 실행");
					itemList = itemDeliveryService.getItemsByFilter(bizNumber, option, filterText);
				} else {
					System.out.println("실행 못함");
					itemList = itemDeliveryService.getItemsByBizNumber(bizNumber);
				}
			} else {
				itemList = itemDeliveryService.getItemsByBizNumber(bizNumber);
			}

			// 페이지네이션 처리
			int itemsPerPage = 10;
			int totalItems = itemList.size();
			int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);
			int startIndex = (page - 1) * itemsPerPage;
			int endIndex = Math.min(startIndex + itemsPerPage, totalItems);

			List<ItemDTO> paginatedList = itemList.subList(startIndex, endIndex);

			// 모델에 추가
			model.addAttribute("itemList", paginatedList);
			model.addAttribute("totalPages", totalPages);
			model.addAttribute("currentPage", page);
			model.addAttribute("option", option);
			model.addAttribute("filterText", filterText);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);

			return "delivery/deliveryFilter"; // JSP 파일 경로 반환
		}
		

		@PostMapping("/deliveryCreate.do")
		public String createDelivery(  
				@RequestParam("inDate") String inDateToday,
				@RequestParam("spec") String spec, 
				@RequestParam("receiverId") String receiverId,
				@RequestParam("address") String address, 
				@RequestParam("recipient") String recipient,
				@RequestParam("waybill") String waybill,
				@RequestParam("deliveryCompany") String deliveryCompany,
				@RequestParam("itemCode") String itemCode,
				HttpSession session) {
			
			String bizNumber = (String) session.getAttribute("biz_number");
			
			System.out.println("와와와와아아와와와와아아와와와와아아와와와와아아" + bizNumber);
			System.out.println("와와와와아아와와와와아아와와와와아아와와와와아아" + itemCode);
			
			
			LocalDate localDate = LocalDate.parse(inDateToday);
			LocalDateTime localDateTime = localDate.atStartOfDay(); // 
			Timestamp inDate = Timestamp.valueOf(localDateTime);

			// 한국 시간대의 현재 타임스탬프를 사용
			ZonedDateTime nowKST = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
			Timestamp currentTimestampKST = Timestamp.valueOf(nowKST.toLocalDateTime());

			//Delivery_ID: itemCode +  지금시간
			String deleveryId = itemCode + currentTimestampKST.getTime();

		
			//  테이블에 저장할 데이터 설정
			DeliveryDTO DeliveryDTO = new DeliveryDTO();
			DeliveryDTO.setInDate(inDate); 
			DeliveryDTO.setDeliveryId(deleveryId); 
			DeliveryDTO.setItemCode(itemCode);
			DeliveryDTO.setSpec(spec); 
			DeliveryDTO.setReceiverId(receiverId);
			DeliveryDTO.setAddress(address);
			DeliveryDTO.setRecipient(recipient);
			DeliveryDTO.setWaybill(waybill);
			DeliveryDTO.setDeliveryCompany(deliveryCompany);

			//  
			DeliveryService.insertDelivery(DeliveryDTO);

			return "redirect:/delivery.do";
		}

		@GetMapping("/getDeliveryDetails.do")
		public String getDeliveryDetails(@RequestParam("itemCode") String itemCode, Model model) {
			// ITEM 
			ItemDTO itemDetails = itemDeliveryService.getItemDetails(itemCode);
			// delivery
			DeliveryDTO deliveryDetails = DeliveryService.getDeliveryDetails(itemCode);

			
			model.addAttribute("itemDetails", itemDetails);
			model.addAttribute("DeliveryDetails", deliveryDetails);

			return "delivery/deliveryDetail"; // JSP 
		}
		
		@GetMapping("/deliveryDetailUpdate.do")
		public String showUpdateForm(@RequestParam("itemCode") String itemCode, Model model, HttpSession session) {
			// ITEM itemCode
			ItemDTO itemDetails = itemDeliveryService.getItemDetails(itemCode);

			DeliveryDTO deliveryDetails = DeliveryService.getDeliveryDetails(itemCode);

			
			String bizNumber = (String) session.getAttribute("biz_number");
			
			List<String> itemNames = itemDeliveryService.getItemNamesByBizNumber(bizNumber);

			
			model.addAttribute("itemDetails", itemDetails);
			model.addAttribute("DeliveryDetails", deliveryDetails);
			model.addAttribute("itemNames", itemNames);

			
			return "delivery/deliveryDetailUpdate"; 
		}
		
		@GetMapping("/getDeliveryDetailsSub.do")
		public String getBuyInDetailsSub(@RequestParam("deliveryId") String deliveryId,
				@RequestParam("itemCode") String itemCode, Model model) {
			ItemDTO itemDetails = itemDeliveryService.getItemDetails(itemCode);


			DeliveryDTO deliveryDetails = DeliveryService
					.getDeliveryDetailsSub(deliveryId);

			// delivery 테이블에서 itemCode로 데이터 가져오기
			DeliveryDTO deliveryDetailsSub = DeliveryService.getItemDetails(itemCode);
			
			System.out.println("=========================================================");
			System.out.println(deliveryDetailsSub);
			System.out.println("=========================================================");

			// 모델에 추가
			model.addAttribute("itemDetails", itemDetails);
			model.addAttribute("DeliveryDetails", deliveryDetails);
			model.addAttribute("itemDetailsSub", deliveryDetailsSub);

			return "delivery/deliveryDetailSub"; // JSP 파일 경로
		}
		
		
		@PostMapping("/updateDelivery.do")
		public String updateDelivery(@RequestParam("itemCode") String itemCode, 
				@RequestParam("spec") String spec,
				@RequestParam("receiverId") String receiverId, 
				@RequestParam("address") String address,
				@RequestParam("recipient") String recipient, 
				@RequestParam("waybill") String waybill,
				@RequestParam("deliveryCompany") String deliveryCompany) {

			// DeliveryDTO 객체 생성 및 값 설정
			DeliveryDTO DeliveryDTO = new DeliveryDTO();
			DeliveryDTO.setItemCode(itemCode);
			DeliveryDTO.setSpec(spec);
			DeliveryDTO.setReceiverId(receiverId);
			DeliveryDTO.setAddress(address);
			DeliveryDTO.setRecipient(recipient);
			DeliveryDTO.setWaybill(waybill);
			DeliveryDTO.setDeliveryCompany(deliveryCompany);

			// DELIVERY 테이블 업데이트
			DeliveryService.updateDelivery(DeliveryDTO);

			return "redirect:/delivery.do"; // 업데이트 후 재고 목록 페이지로
		}

		@PostMapping("/deleteDelivery.do")
		public String deleteDelivery(@RequestParam("itemCode") String itemCode, 
				HttpSession session) {
		   
		    DeliveryService.deleteDeliveryByItemCode(itemCode);


		    return "redirect:/delivery.do"; 
		}

		
		


		
}
