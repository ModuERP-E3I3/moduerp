package com.e3i3.moduerp.salesstock.model.dao;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.salesstock.model.dto.SalesStockOutDTO;

@Repository
public class SalesStockOutDAOImpl implements SalesStockOutDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String namespace = "SalesStockOutMapper";

    @Override
    public List<SalesStockOutDTO> selectAllSalesStockOuts() {
        return sqlSession.selectList(namespace + ".selectAllSalesStockOuts");
    }

    @Override
    public void insertSalesStockOut(SalesStockOutDTO salesStockOutDTO) {
        sqlSession.insert(namespace + ".insertSalesStockOut", salesStockOutDTO);
    }

    @Override
    public List<SalesStockOutDTO> selectSalesStockOutByItemCode(String itemCode) {
        return sqlSession.selectList(namespace + ".selectSalesStockOutByItemCode", itemCode);
    }

    public SalesStockOutDTO selectSalesStockOutBySStockId(String sStockOutId) {
        return sqlSession.selectOne(namespace + ".selectSalesStockOutBySStockId", sStockOutId);
    }

    @Override
    public void updateSalesStockOut(SalesStockOutDTO salesStockOutDTO) {
        // MyBatis 매퍼 호출하여 출고 정보 업데이트
        sqlSession.update(namespace + ".updateSalesStockOut", salesStockOutDTO);
    }

    @Override
    public int selectTotalStockOutByItemCode(String itemCode) {
        Integer totalStockOut = sqlSession.selectOne(namespace + ".getTotalStockOutByItemCode", itemCode);
        return totalStockOut != null ? totalStockOut : 0;  // null인 경우 0을 반환
    }

    @Override
    public void deleteSalesStockOut(String sStockOutId) {
        sqlSession.delete(namespace + ".deleteSalesStockOut", sStockOutId);
    }

    @Override
    public Timestamp getLatestStockOutDateByItemCode(String itemCode) {
        return sqlSession.selectOne(namespace + ".selectLatestStockOutDateByItemCode", itemCode);
    }

    @Override
    public double getLatestOutPriceByItemCode(String itemCode) {
        return sqlSession.selectOne(namespace + ".selectLatestOutPriceByItemCode", itemCode);
    }

    @Override
    public Map<String, Object> getLatestOutPlaceAndPriceByItemCode(String itemCode) {
        return sqlSession.selectOne(namespace + ".selectLatestOutPlaceAndPriceByItemCode", itemCode);
    }

    @Override
    public void updateOutPlaceAndPriceInItem(String itemCode, Map<String, Object> data) {
        Map<String, Object> params = new HashMap<>();
        params.put("itemCode", itemCode);
        params.put("sStockOutPlace", data.get("S_STOCK_OUT_PLACE"));
        params.put("sStockOutPrice", data.get("S_STOCK_OUT_PRICE"));

        sqlSession.update(namespace + ".updateOutPlaceAndPriceInItem", params);
    }

    @Override
    public SalesStockOutDTO selectMostRecentStockOutDetails(String itemCode) {
        return sqlSession.selectOne(namespace + ".selectMostRecentStockOutDetails", itemCode);
    }
}
