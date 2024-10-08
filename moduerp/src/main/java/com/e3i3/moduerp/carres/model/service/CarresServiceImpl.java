package com.e3i3.moduerp.carres.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.carres.model.dao.CarresDao;
import com.e3i3.moduerp.carres.model.dto.CarresDto;

@Service
public class CarresServiceImpl implements CarresService{
	@Autowired
	private CarresDao carresDao;
	
	@Override
	public List<CarresDto> getAllCarres(){
		return carresDao.getAllCarres();
	}
}
