package com.e3i3.moduerp.item.model.service;

import java.util.List;

import com.e3i3.moduerp.item.model.dto.ItemDTO;

public interface ItemSalesStockService {
    List<String> getItemNamesByBizNumber(String bizNumber);

    List<String> getStockPlacesByBizNumber(String bizNumber); // �߰��� �޼���

    void insertItem(ItemDTO itemDTO);

    ItemDTO getItemDetails(String itemCode);

    // biz_number�� ���͸��� ������ ����Ʈ�� �������� �޼���
    List<ItemDTO> getItemsByBizNumber(String bizNumber);

    void updateItem(ItemDTO itemDTO);

    void deleteItemByCode(String itemCode);
}
