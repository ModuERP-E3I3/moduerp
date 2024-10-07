package com.e3i3.moduerp.salesstock.controller;

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

    // Sales Stock GET
    @RequestMapping(value = "/salesStockIn.do", method = RequestMethod.GET)
    public String forwardSalesStockIn(@RequestParam(value = "page", defaultValue = "1") int page, Model model, HttpSession session) {
        String bizNumber = (String) session.getAttribute("biz_number");
        List<ItemDTO> itemList = itemSalesStockService.getItemsByBizNumber(bizNumber);

        // 9�ð� �߰� ó��
        for (ItemDTO item : itemList) {
            Timestamp createdAt = item.getCreatedAt();
            Timestamp adjustedTimestamp = Timestamp.from(Instant.ofEpochMilli(createdAt.getTime() + 9 * 60 * 60 * 1000));
            item.setCreatedAt(adjustedTimestamp);
        }

        // ������ ó��
        int itemsPerPage = 10;
        int totalItems = itemList.size();
        int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);
        int startIndex = (page - 1) * itemsPerPage;
        int endIndex = Math.min(startIndex + itemsPerPage, totalItems);
        List<ItemDTO> paginatedList = itemList.subList(startIndex, endIndex);

        model.addAttribute("itemList", paginatedList);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        
        return "salesStock/salesStockIn"; // JSP ���� ��� ��ȯ
    }

    @PostMapping("/salesStockInCreate.do")
    public String createSalesStockIn(@RequestParam("sStockInDate") String stockInDateStr,
            @RequestParam("stockPlace") String stockPlace, @RequestParam("stockIn") int stockIn,
            @RequestParam("itemName") String itemName, @RequestParam("itemDesc") String itemDesc,
            @RequestParam("inPrice") double inPrice, @RequestParam("materialType") List<String> materialType, 
            HttpSession session) {

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

        return "redirect:/salesStockIn.do"; // ��� �� ��� �������� �̵�
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

        return "salesStock/salesStockInDetail"; // JSP ���� ��� ��ȯ
    }

    @PostMapping("/updateSalesStockIn.do")
    public String updateSalesStockIn(@RequestParam("itemCode") String itemCode,
            @RequestParam("itemName") String itemName, @RequestParam("itemDesc") String itemDesc,
            @RequestParam("stockIn") int stockIn, @RequestParam("inPrice") double inPrice,
            @RequestParam("stockPlace") String stockPlace, @RequestParam("materialTypes") List<String> materialTypes) {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setItemCode(itemCode);
        itemDTO.setItemName(itemName);
        itemDTO.setItemDesc(itemDesc);
        itemDTO.setStockIn(stockIn);
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

        return "redirect:/salesStockIn.do"; // ������Ʈ �� ��� �������� �̵�
    }

    @PostMapping("/deleteSalesStockIn.do")
    public String deleteSalesStockIn(@RequestParam("itemCode") String itemCode, HttpSession session) {
        salesStockInService.deleteSalesStockInByItemCode(itemCode);
        itemSalesStockService.deleteItemByCode(itemCode);

        return "redirect:/salesStockIn.do"; // ���� �� ��� �������� �̵�
    }
}
