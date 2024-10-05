package com.e3i3.moduerp.purchaseorders.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.e3i3.moduerp.purchaseorders.model.dao.PurchaseordersDAO;
import com.e3i3.moduerp.purchaseorders.model.dto.PurchaseordersDTO;

@Service
public class PurchaseordersServiceImpl implements PurchaseordersService {

    @Autowired
    private PurchaseordersDAO purchaseordersDAO;

    @Override
    public List<PurchaseordersDTO> getAllPurchaseorders() {
        return purchaseordersDAO.selectAllPurchaseorders();
    }
}
