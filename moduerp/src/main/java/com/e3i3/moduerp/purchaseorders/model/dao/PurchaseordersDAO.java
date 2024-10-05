package com.e3i3.moduerp.purchaseorders.model.dao;

import java.util.List;
import com.e3i3.moduerp.purchaseorders.model.dto.PurchaseordersDTO;

public interface PurchaseordersDAO {
    List<PurchaseordersDTO> selectAllPurchaseorders();
}
