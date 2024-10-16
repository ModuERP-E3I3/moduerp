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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

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

		    // Null 체크 추가
		    if (itemDetails == null) {
		        // itemDetails가 null일 경우 에러 메시지 추가
		        model.addAttribute("errorMessage", "Item not found.");
		        itemDetails = new ItemDTO(); // 빈 객체 생성
		    }

		    // delivery
		    DeliveryDTO deliveryDetails = DeliveryService.getDeliveryDetails(itemCode);

		    // Null 체크 
		    if (deliveryDetails == null) {
		        deliveryDetails = new DeliveryDTO(); // 빈 DTO 객체 생성
		    }
		    System.out.println("================================");
		    System.out.println(deliveryDetails);
		    System.out.println("================================");
		    // 배송 업체 코드 변환
		    String deliveryCompany = deliveryDetails.getDeliveryCompany();
		    
		    if (deliveryCompany == null) {
		        deliveryCompany = ""; // 기본값 설정 또는 빈 문자열
		    }
		    String deliveryCompanyName = getDeliveryCompanyName(deliveryCompany);  // 변환 함수 호출

		    // 변환된 값과 함께 모델에 추가
		    model.addAttribute("itemDetails", itemDetails);
		    model.addAttribute("deliveryDetails", deliveryDetails);
		    model.addAttribute("deliveryCompanyName", deliveryCompanyName);  // 변환된 업체명 추가

		    return "delivery/deliveryDetail"; // JSP 
		}



	
		private String getDeliveryCompanyName(String deliveryCompanyCode) {
		    switch (deliveryCompanyCode) {
		        case "01":
		            return "우체국택배";
		        case "04":
		            return "CJ대한통운";
		        case "05":
		            return "한진택배";
		        case "06":
		            return "로젠택배";
		        case "08":
		            return "롯데택배";
		        case "94":
		            return "카카오 T 당일배송";
		        case "95":
		            return "큐익스프레스";
		        case "11":
		            return "일양로지스";
		        case "22":
		            return "대신택배";
		        case "23":
		            return "경동택배";
		        case "24":
		            return "GS Postbox 택배";
		        case "46":
		            return "CU편의점택배";
		        default:
		            return "";
		    }
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
