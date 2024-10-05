package com.e3i3.moduerp.quality.model.dao;

import java.util.List;
import com.e3i3.moduerp.quality.model.dto.QualityControlDTO;

public interface QualityControlDAO {
    List<QualityControlDTO> selectAllQualityControls();
}
