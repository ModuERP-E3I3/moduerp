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

}
