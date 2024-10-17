package com.e3i3.moduerp.purchaseorders.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.employee.model.dto.Employee;
import com.e3i3.moduerp.item.model.dto.ItemDTO;
import com.e3i3.moduerp.purchaseorders.model.dao.PurchaseOrdersDAO;
import com.e3i3.moduerp.purchaseorders.model.dto.PurchaseOrdersDTO;

@Service
public class PurchaseOrdersServiceImpl implements PurchaseOrdersService {

	@Autowired
	private PurchaseOrdersDAO purchaseOrdersDao;

	@Override
	public List<PurchaseOrdersDTO> getAllPurchaseOrders(String bizNumber) {
		return purchaseOrdersDao.getAllPurchaseOrders(bizNumber);
	}

	@Override
	public void purchaseOrderCreate(PurchaseOrdersDTO purchaseOrderDto) {
		purchaseOrdersDao.purchaseOrderCreate(purchaseOrderDto);
	}

	@Override
	public List<String> getEmpNamesByBizNumber(String bizNumber) {
		return purchaseOrdersDao.getEmpNamesByBizNumber(bizNumber);
	}

	@Override
	public List<String> getDepartmentIdsByBizNumber(String bizNumber) {
		return purchaseOrdersDao.getDepartmentIdsByBizNumber(bizNumber);
	}

	@Override
	public List<PurchaseOrdersDTO> getPurchaseOrdersByBizNumber(String bizNumber) {
		return purchaseOrdersDao.getPurchaseOrdersByBizNumber(bizNumber);
	}

	@Override
	public List<Employee> getEmpNameDepart(String bizNumber) {
		return purchaseOrdersDao.getEmpNameDepart(bizNumber);
	}

	@Override
	public PurchaseOrdersDTO getPurchaseOrderDetail(String orderId) {
		return purchaseOrdersDao.selectPurchaseOrderByOrderId(orderId);
	}

	@Override
	public void updatePurchaseOrder(PurchaseOrdersDTO purchaseOrderDto) {
		purchaseOrdersDao.updatePurchaseOrder(purchaseOrderDto);
	}

	@Override
	public void deletePurchaseOrderByOrderId(String orderId) {
		purchaseOrdersDao.deletePurchaseOrderByOrderId(orderId);
	}

	// accountNo 리스트 가져오기 !!!!
	@Override
	public List<Map<String, Object>> getAllAccountNames() {
		return purchaseOrdersDao.getAllAccountNames();
	}

	@Override
	public List<String> getAllItemNames() {
		return null;
	}

	// -----------------------------------------------
	// Purchase Orders filter !!!!!
	@Override
	public List<PurchaseOrdersDTO> getPurchaseOrdersByFilterDate(String bizNumber, String option, String filterText,
			String startDate, String endDate) {
		if (option.equals("accountName")) {
			return purchaseOrdersDao.getPurchaseOrdersByAccountNameDate(bizNumber, filterText, startDate, endDate);
		} else if (option.equals("puItemName")) {
			return purchaseOrdersDao.getPurchaseOrdersByItemNameDate(bizNumber, filterText, startDate, endDate);
		} else if (option.equals("ODirector")) {
			return purchaseOrdersDao.getPurchaseOrdersByODirectorDate(bizNumber, filterText, startDate, endDate);
		}

		return null;
	}

	@Override
	public List<PurchaseOrdersDTO> getPurchaseOrdersByFilter(String bizNumber, String option, String filterText) {
		if (option.equals("accountName")) {
			return purchaseOrdersDao.getPurchaseOrdersByAccountName(bizNumber, filterText);
		} else if (option.equals("puItemName")) {
			return purchaseOrdersDao.getPurchaseOrdersByItemName(bizNumber, filterText);
		} else if (option.equals("ODirector")) {
			return purchaseOrdersDao.getPurchaseOrdersByODirector(bizNumber, filterText);
		}
		return null;
	}
	
	@Override
	public List<PurchaseOrdersDTO> getPurchaseOrdersByFilterOnlyDate(String bizNumber, String startDate, String endDate) {
		return purchaseOrdersDao.getPurchaseOrdersByFilterOnlyDate(bizNumber, startDate, endDate);
	}

	@Override
	public List<PurchaseOrdersDTO> getPurchaseOrdersByFilterStartDate(String bizNumber, String startDate) {
		return purchaseOrdersDao.getPurchaseOrdersByFilterStartDate(bizNumber, startDate);
	}

	@Override
	public List<PurchaseOrdersDTO> getPurchaseOrdersByFilterEndDate(String bizNumber, String endDate) {
		return purchaseOrdersDao.getPurchaseOrdersByFilterEndDate(bizNumber, endDate);
	}

	@Override
	public List<PurchaseOrdersDTO> getAllPurchaseOrders() {
		// TODO Auto-generated method stub
		return null;
	}

}




