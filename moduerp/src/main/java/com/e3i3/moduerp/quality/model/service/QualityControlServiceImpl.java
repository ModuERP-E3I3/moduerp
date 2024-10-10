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

	@Override
	public List<QualityControlDTO> getQualityByFilterDate(String bizNumber, String option, String filterText,
			String startDate, String endDate) {

		if (option.equals("itemName")) {
			return qualityControlDAO.getQualityItemNameByFilterDate(bizNumber, filterText, startDate, endDate);
		} else if (option.equals("inspecType")) {
			return qualityControlDAO.getQualityinspecTypeByFilterDate(bizNumber, filterText, startDate, endDate);
		} else if (option.equals("progressStatus")) {
			return qualityControlDAO.getQualityrprogressStatusByFilterDate(bizNumber, filterText, startDate, endDate);
		} else if (option.equals("qDirector")) {
			return qualityControlDAO.getQualityqDirectorByFilterDate(bizNumber, filterText, startDate, endDate);
		}

		return null;
	}

	@Override
	public List<QualityControlDTO> getQualityByFilter(String bizNumber, String option, String filterText) {
		if (option.equals("itemName")) {
			return qualityControlDAO.getQualityItemNameByFilter(bizNumber, filterText);
		} else if (option.equals("inspecType")) {
			return qualityControlDAO.getQualityinspecTypeByFilter(bizNumber, filterText);
		} else if (option.equals("progressStatus")) {
			return qualityControlDAO.getQualityrprogressStatusByFilter(bizNumber, filterText);
		} else if (option.equals("qDirector")) {
			return qualityControlDAO.getQualityqDirectorByFilter(bizNumber, filterText);
		}

		return null;
	}
}
