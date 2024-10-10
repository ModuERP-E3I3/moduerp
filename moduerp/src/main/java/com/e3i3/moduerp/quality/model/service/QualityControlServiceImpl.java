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
	public List<QualityControlDTO> getAllQualityControls() {
		return qualityControlDAO.selectAllQualityControls();
	}
}
