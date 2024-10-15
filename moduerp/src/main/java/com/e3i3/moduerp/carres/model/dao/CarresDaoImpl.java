package com.e3i3.moduerp.carres.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.carres.model.dto.CarresDto;
import com.e3i3.moduerp.employee.model.dto.Employee;

@Repository
public class CarresDaoImpl implements CarresDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "CarresMapper";
	
	@Override
	public List<CarresDto> getAllCarres(String bizNumber){
		return sqlSession.selectList(namespace + ".getAllCarres", bizNumber);
	}
	
	public CarresDto getCarresId(String carId) {
		return sqlSession.selectOne(namespace + ".getCarresId", carId);
	}

	@Override
	public List<Employee> getEmpNameDepart(String bizNumber) {
		return sqlSession.selectList(namespace + ".getEmpNameDepart" , bizNumber);
	}

	@Override
	public List<CarresDto> getCarsByBizNumber(String bizNumber) {
		return sqlSession.selectList(namespace + ".getCarsByBizNumber", bizNumber);
	}

	@Override
	public void insertCarres(CarresDto carresDto) {
		sqlSession.insert(namespace + ".insertCarres", carresDto);
	}

	@Override
	public CarresDto selectcarReserveCode(String carReserveCode) {
		return sqlSession.selectOne(namespace + ".selectcarReserveCode", carReserveCode);
	}

	@Override
	public void updateCarres(CarresDto carresDto) {
		sqlSession.update(namespace + ".updateCarres", carresDto);
	}

	@Override
	public void deleteCarres(String carReserveCode) {
		sqlSession.delete(namespace + ".deleteCarres", carReserveCode);
		
	}

	@Override
	public List<CarresDto> getCarByCarModelDate(String bizNumber, String filterText, String startDate, String endDate) {
		return sqlSession.selectList(namespace + ".selectCarByCarModelDate",
				Map.of("bizNumber", bizNumber, "filterText", filterText, "startDate", startDate, "endDate", endDate));
	}

	@Override
	public List<CarresDto> getCarByCarNumDate(String bizNumber, String filterText, String startDate, String endDate) {
		return sqlSession.selectList(namespace + ".selectCarByCarNumDate",
				Map.of("bizNumber", bizNumber, "filterText", filterText, "startDate", startDate, "endDate", endDate));
	}

	@Override
	public List<CarresDto> getCarByEmpNameDate(String bizNumber, String filterText, String startDate, String endDate) {
		return sqlSession.selectList(namespace + ".selectCarByEmpNameDate",
				Map.of("bizNumber", bizNumber, "filterText", filterText, "startDate", startDate, "endDate", endDate));
	}

	@Override
	public List<CarresDto> getCarByDepartmentIdDate(String bizNumber, String filterText, String startDate,
			String endDate) {
		return sqlSession.selectList(namespace + ".selectCarByDepartmentIdDate",
				Map.of("bizNumber", bizNumber, "filterText", filterText, "startDate", startDate, "endDate", endDate));
	}

	@Override
	public List<CarresDto> getCarByDrivingStatusDate(String bizNumber, String filterText, String startDate,
			String endDate) {
		return sqlSession.selectList(namespace + ".selectCarByDrivingStatusDate",
				Map.of("bizNumber", bizNumber, "filterText", filterText, "startDate", startDate, "endDate", endDate));
	}

	@Override
	public List<CarresDto> getCarByCarModel(String bizNumber, String filterText) {
		return sqlSession.selectList(namespace + ".selectCarByCarModel",
				Map.of("bizNumber", bizNumber, "filterText", filterText));
	}

	@Override
	public List<CarresDto> getCarByCarNum(String bizNumber, String filterText) {
		return sqlSession.selectList(namespace + ".selectCarByCarNum",
				Map.of("bizNumber", bizNumber, "filterText", filterText));
	}

	@Override
	public List<CarresDto> getCarByEmpName(String bizNumber, String filterText) {
		return sqlSession.selectList(namespace + ".selectCarByEmpName",
				Map.of("bizNumber", bizNumber, "filterText", filterText));
	}

	@Override
	public List<CarresDto> getCarByDepartmentId(String bizNumber, String filterText) {
		return sqlSession.selectList(namespace + ".selectCarByDepartmentId",
				Map.of("bizNumber", bizNumber, "filterText", filterText));
	}

	@Override
	public List<CarresDto> getCarByDrivingStatus(String bizNumber, String filterText) {
		return sqlSession.selectList(namespace + ".selectCarByDrivingStatus",
				Map.of("bizNumber", bizNumber, "filterText", filterText));
	}

	@Override
	public List<CarresDto> getCarByFilterOnlyDate(String bizNumber, String startDate, String endDate) {
		return sqlSession.selectList(namespace + ".selectCarByFilterOnlyDate",
				Map.of("bizNumber", bizNumber, "startDate", startDate, "endDate", endDate));
	}

	@Override
	public List<CarresDto> getCarByFilterStartDate(String bizNumber, String startDate) {
		return sqlSession.selectList(namespace + ".selectCarByFilterStartDate",
				Map.of("bizNumber", bizNumber, "startDate", startDate));
	}

	@Override
	public List<CarresDto> getCarByFilterEndDate(String bizNumber, String endDate) {
		return sqlSession.selectList(namespace + ".selectCarByFilterEndDate",
				Map.of("bizNumber", bizNumber, "endDate", endDate));
	}
	
	
}
