package com.e3i3.moduerp.carmgt.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.carmgt.model.dao.CarmgtDao;
import com.e3i3.moduerp.carmgt.model.dto.CarmgtDto;

@Service
public class CarmgtServiceImpl implements CarmgtService{
	@Autowired
	private CarmgtDao carmgtDao;
	
	@Override
	public List<CarmgtDto> getAllCarmgt(){
		return carmgtDao.getAllCarmgt();
	}

	/*
	 * @Override public void deleteCarmgt(String carId) {
	 * 
	 * carmgtDao.deleteCarmgt(carId); }
	 */
}
