package com.e3i3.moduerp.quality.model.service;

import java.util.List;
import com.e3i3.moduerp.quality.model.dto.QualityControlDTO;

public interface QualityControlService {

	List<QualityControlDTO> getQualityControlsByBizNumber(String bizNumber);

	void insertQulityControl(QualityControlDTO qulityDTO);

	public int getTotalInspecQtyByOrderNumber(String orderNumber);

	QualityControlDTO getQualityControlByInspecCode(String inspecCode);

	void updateQualityControl(QualityControlDTO qualityControlDTO);

	void deleteQualityControlByInspecCode(String inspecCode);

	List<QualityControlDTO> getQualityByFilterDate(String bizNumber, String option, String filterText, String startDate,
			String endDate);

	List<QualityControlDTO> getQualityByFilter(String bizNumber, String option, String filterText);

	List<QualityControlDTO> getQualityByFilterOnlyDate(String bizNumber, String startDate, String endDate);

	List<QualityControlDTO> getQualityByFilterStartDate(String bizNumber, String startDate);

	List<QualityControlDTO> getQualityByFilterEndDate(String bizNumber, String endDate);

}
