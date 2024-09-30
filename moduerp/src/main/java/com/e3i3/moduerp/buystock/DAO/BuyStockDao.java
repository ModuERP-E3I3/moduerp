package com.e3i3.moduerp.buystock.DAO;

import com.e3i3.moduerp.buystock.DTO.BuyStockInDto;
import com.e3i3.moduerp.buystock.DTO.BuyStockOutDto;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;



public class BuyStockDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	 // 备概 涝绊 DB 贸府
	public boolean insertBuyStockIn(BuyStockInDto buyStockDto) {
		 String sql = "INSERT INTO Buy_stock_in (b_stock_in_id, item_code, uuid, account_no, bank_id, department_id, order_id, b_stock_in_date, b_stock_in_place, b_stock_in_qty, b_stock_in_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        Object buyStockInDto;
			int result = jdbcTemplate.update(sql, buyStockInDto.getBStockInId(), 
					buyStockInDto.getItemCode(), buyStockInDto.getUuid(),
	        		buyStockInDto.getAccountNo(), buyStockInDto.getBankId(),
	                buyStockInDto.getDepartmentId(), buyStockInDto.getOrderId(),
	                buyStockInDto.getBStockInDate(), buyStockInDto.getBStockInPlace(),
	                buyStockInDto.getBStockInQty(), buyStockInDto.getBStockInPrice());
	        return result > 0;
	}
	// 备概 免绊 DB 贸府
    public boolean insertBuyStockOut(BuyStockOutDto buyStockOut) {
        String sql = "INSERT INTO Buy_stock_out (b_stock_out_id, item_code, u"
        		+ "uid, account_no, bank_id, department_id, b_stock_out_date, b_stock_out_place, b_stock_out_qty) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int result = jdbcTemplate.update(sql, buyStockOut.getBStockOutId(),
        		buyStockOut.getItemCode(), buyStockOut.getUuid(),
                buyStockOut.getAccountNo(), buyStockOut.getBankId(), 
                buyStockOut.getDepartmentId(), buyStockOut.getBStockOutDate(),
                buyStockOut.getBStockOutPlace(), 
                buyStockOut.getBStockOutQty());
        return result > 0;
    }

}
