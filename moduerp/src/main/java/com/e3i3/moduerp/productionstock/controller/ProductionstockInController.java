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

	// ���� GET �޼���
	@RequestMapping(value = "/productionStockIn.do", method = RequestMethod.GET)
	public String forwardProductionIn(Model model) {
		List<ProductionStockInDTO> stockList = productionStockInService.getAllProductionStockIn();
		model.addAttribute("stockList", stockList);
		return "productionStock/productionStockIn"; // JSP ���� ��� ��ȯ
	}

	@PostMapping("/productionStockInCreate.do")
	public String createProductionStockIn(@RequestParam("pStockInDate") String stockInDateStr,
			@RequestParam("stockPlace") String stockPlace, @RequestParam("stockInQty") int stockInQty,
			@RequestParam("itemName") String itemName, @RequestParam("itemDesc") String itemDesc,
			@RequestParam("inPrice") double inPrice, @RequestParam("materialType") List<String> materialType,
			HttpSession session) {

		// ���ǿ��� biz_number�� uuid�� ������
		String bizNumber = (String) session.getAttribute("biz_number");
		String userUuid = (String) session.getAttribute("uuid");

		// ITEM_CODE ����: biz_number + "P" + ���� Ÿ�ӽ�����
		String itemCode = bizNumber + "P" + new Timestamp(System.currentTimeMillis()).getTime();

		// stockInDateStr�� LocalDate�� ��ȯ�� �� LocalDateTime���� ��ȯ (���� �ð� �߰�)
		LocalDate localDate = LocalDate.parse(stockInDateStr);
		LocalDateTime localDateTime = localDate.atStartOfDay(); // ���� �ð� �߰�
		Timestamp stockInDate = Timestamp.valueOf(localDateTime);

		// ITEM ���̺� ������ ������ ����
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setItemCode(itemCode);
		itemDTO.setItemName(itemName);
		itemDTO.setItemDesc(itemDesc);

		// TOTAL_STOCK_QTY ��� �� ����
		// ó�� �Էµ� ��� TOTAL_STOCK_QTY�� 0���� ����
		int totalStockQty = 0;

		// ���ݰ� ������ ������� TOTAL_STOCK_QTY ���
		totalStockQty += stockInQty; // ���� �԰� ������ TOTAL_STOCK_QTY�� �߰�
		itemDTO.setTotalStockQty(totalStockQty); // TOTAL_STOCK_QTY ����

		itemDTO.setCreatedAt(stockInDate); // ��ȯ�� Timestamp ���
		itemDTO.setStockPlace(stockPlace);
		itemDTO.setInPrice(inPrice);
		itemDTO.setBizNumber(bizNumber);
		itemDTO.setItemList(materialType);

		// ITEM ���̺� ������ ����
		itemProductionstockService.insertItem(itemDTO);

		// PRODUCTION_STOCK_IN ���̺� ������ ������ ����
		ProductionStockInDTO productionStockInDTO = new ProductionStockInDTO();
		productionStockInDTO.setPStockInId(itemCode); // ITEM_CODE ���
		productionStockInDTO.setItemCode(itemCode);
		productionStockInDTO.setPStockInDate(stockInDate); // ��ȯ�� Timestamp ���
		productionStockInDTO.setPStockPlace(stockPlace);
		productionStockInDTO.setPStockInQty(stockInQty);
		productionStockInDTO.setUUID(userUuid);

		// PRODUCTION_STOCK_IN ���̺� ������ ����
		productionStockInService.insertProductionStockIn(productionStockInDTO);

		return "redirect:/productionStockIn.do"; // ��� �� ��� �������� �����̷�Ʈ
	}

}
