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

}
