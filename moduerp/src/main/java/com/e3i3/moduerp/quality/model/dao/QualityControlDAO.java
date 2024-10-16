package com.e3i3.moduerp.quality.model.dao;

import java.util.List;
import com.e3i3.moduerp.quality.model.dto.QualityControlDTO;

public interface QualityControlDAO {

	List<QualityControlDTO> findByBizNumber(String bizNumber);

	void insertQulityControl(QualityControlDTO qulityDTO);

	public int getTotalInspecQtyByOrderNumber(String orderNumber);

	QualityControlDTO getQualityControlByInspecCode(String inspecCode);

	void updateQualityControl(QualityControlDTO qualityControlDTO);

	void deleteQualityControlByInspecCode(String inspecCode);

	List<QualityControlDTO> getQualityItemNameByFilterDate(String bizNumber, String filterText, String startDate,
			String endDate);

	List<QualityControlDTO> getQualityinspecTypeByFilterDate(String bizNumber, String filterText, String startDate,
			String endDate);

	List<QualityControlDTO> getQualityrprogressStatusByFilterDate(String bizNumber, String filterText, String startDate,
			String endDate);

	List<QualityControlDTO> getQualityqDirectorByFilterDate(String bizNumber, String filterText, String startDate,
			String endDate);

	List<QualityControlDTO> getQualityItemNameByFilter(String bizNumber, String filterText);

	List<QualityControlDTO> getQualityinspecTypeByFilter(String bizNumber, String filterText);

	List<QualityControlDTO> getQualityrprogressStatusByFilter(String bizNumber, String filterText);

	List<QualityControlDTO> getQualityqDirectorByFilter(String bizNumber, String filterText);

	List<QualityControlDTO> getQualityByFilterOnlyDate(String bizNumber, String startDate, String endDate);

	List<QualityControlDTO> getQualityByFilterStartDate(String bizNumber, String startDate);

	List<QualityControlDTO> getQualityByFilterEndDate(String bizNumber, String endDate);

}
