package com.e3i3.moduerp.carmgt.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.carmgt.model.dao.CarmgtDao;
import com.e3i3.moduerp.carmgt.model.dto.CarmgtDto;
import com.e3i3.moduerp.employee.model.dto.Employee;

@Service
public class CarmgtServiceImpl implements CarmgtService {
	@Autowired
	private CarmgtDao carmgtDao;

	@Override
	public List<CarmgtDto> getAllCarmgt(String bizNumber) {
		return carmgtDao.getAllCarmgt(bizNumber);
	}

	@Override
	public void insertCarmgt(CarmgtDto carmgtDto) {
		carmgtDao.insertCarmgt(carmgtDto);
	}

	@Override
	public List<String> getEmpNamesByBizNumber(String bizNumber) {
		return carmgtDao.getEmpNamesByBizNumber(bizNumber);
	}

	@Override
	public List<String> getDepartmentIdsByBizNumber(String bizNumber) {
		return carmgtDao.getDepartmentIdsByBizNumber(bizNumber);
	}

	@Override
	public List<CarmgtDto> getCarsByBizNumber(String bizNumber) {
		return carmgtDao.getCarsByBizNumber(bizNumber);
	}

	@Override
	public List<Employee> getEmpNameDepart(String bizNumber) {
		return carmgtDao.getEmpNameDepart(bizNumber);
	}

	@Override
	public CarmgtDto getCarmgtListDetail(String paymentHistoryCode) {
		return carmgtDao.selectpaymentHistoryCode(paymentHistoryCode);
	}

	@Override
	public void updateCarmgt(CarmgtDto carmgtDto) {
		carmgtDao.updateCarmgt(carmgtDto);
	}

	@Override
	public void deleteCarmgt(String paymentHistoryCode) {
		carmgtDao.deleteCarmgt(paymentHistoryCode);
	}

	@Override
	public List<CarmgtDto> getCarByFilterDate(String bizNumber, String option, String filterText, String startDate,
			String endDate) {
		if (option.equals("carModel")) {
			return carmgtDao.getCarByCarModelDate(bizNumber, filterText, startDate, endDate);
		} else if (option.equals("carNum")) {
			return carmgtDao.getCarByCarNumDate(bizNumber, filterText, startDate, endDate);
		} else if (option.equals("empName")) {
			return carmgtDao.getCarByEmpNameDate(bizNumber, filterText, startDate, endDate);
		} else if (option.equals("departmentId")) {
			return carmgtDao.getCarByDepartmentIdDate(bizNumber, filterText, startDate, endDate);
		}
		return null;
	}

	@Override
	public List<CarmgtDto> getCarByFilter(String bizNumber, String option, String filterText) {
		if (option.equals("carModel")) {
			return carmgtDao.getCarByCarModel(bizNumber, filterText);
		} else if (option.equals("carNum")) {
			return carmgtDao.getCarByCarNum(bizNumber, filterText);
		} else if (option.equals("empName")) {
			return carmgtDao.getCarByEmpName(bizNumber, filterText);
		} else if (option.equals("departmentId")) {
			return carmgtDao.getCarByDepartmentId(bizNumber, filterText);
		}
		return null;
	}

	@Override
	public List<CarmgtDto> getCarByFilterOnlyDate(String bizNumber, String startDate, String endDate) {
		return carmgtDao.getCarByFilterOnlyDate(bizNumber, startDate, endDate);
	}

	@Override
	public List<CarmgtDto> getCarByFilterStartDate(String bizNumber, String startDate) {
		return carmgtDao.getCarByFilterStartDate(bizNumber, startDate);
	}

	@Override
	public List<CarmgtDto> getCarByFilterEndDate(String bizNumber, String endDate) {
		return carmgtDao.getCarByFilterEndDate(bizNumber, endDate);
	}

	/*
	 * @Override public void deleteCarmgt(String carId) {
	 * 
	 * carmgtDao.deleteCarmgt(carId); }
	 */
}
