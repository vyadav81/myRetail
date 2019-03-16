package com.myretail.dao;

import com.myretail.model.product.CurrentPrice;
/**
 * Price DAO to get and update price 
 * 
 * @author vyadav
 *
 */
public interface PriceDao {
	public CurrentPrice findByProductId(Long productId) throws Exception;

	public String updateProductPrice(CurrentPrice cp) throws Exception;
}