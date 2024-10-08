package com.e3i3.moduerp.quality.model.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.e3i3.moduerp.quality.model.dao.QualityControlDAO;
import com.e3i3.moduerp.quality.model.dto.QualityControlDTO;

@Service
public class QualityControlServiceImpl implements QualityControlService {

	@Autowired
	private QualityControlDAO qualityControlDAO;

	@Override
	public List<QualityControlDTO> getQualityControlsByBizNumber(String bizNumber) {
		return qualityControlDAO.findByBizNumber(bizNumber);
	}

	@Override
	public void insertQulityControl(QualityControlDTO qulityDTO) {
		qualityControlDAO.insertQulityControl(qulityDTO);
	}

	@Override
	public int getTotalInspecQtyByOrderNumber(String orderNumber) {
		return qualityControlDAO.getTotalInspecQtyByOrderNumber(orderNumber);
	}

	@Override
	public QualityControlDTO getQualityControlByInspecCode(String inspecCode) {
		return qualityControlDAO.getQualityControlByInspecCode(inspecCode);
	}

	@Override
	public void updateQualityControl(QualityControlDTO qualityControlDTO) {
		qualityControlDAO.updateQualityControl(qualityControlDTO);
	}

	@Override
	public void deleteQualityControlByInspecCode(String inspecCode) {
		// DAO를 호출하여 데이터 삭제
		qualityControlDAO.deleteQualityControlByInspecCode(inspecCode);
	}
}
