package com.myretail.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.myretail.common.constant.Constant;
import com.myretail.dao.PriceDao;
import com.myretail.model.product.CurrentPrice;

/**
* This is RESTFul service that provides API to get the price of product
*  
* @author vyadav
* 
*/
@RestController
@EnableAutoConfiguration
@Api(value = "API to fetch price")
public class PriceRestController {
	

	@Autowired
	PriceDao priceDao;

	Logger logger = LoggerFactory.getLogger(PriceRestController.class);
	
	/**
	 * This method is a HTTP GET method.
	 * It is used to get the price for a given productId
	 * 
	 * @param productId for which price need to be fetched
	 * @return price
	 * @throws Exception generic exception
	 */
	@ApiOperation(value = "Get current price for particular product Id", response = CurrentPrice.class)
	@GetMapping(value="/product/{productId}/price")
    public CurrentPrice getPrice(@PathVariable("productId") Long productId) throws Exception   {
		
		logger.info("BEGIN getPrice with productId : " + productId);
		if (productId == null || productId <= 0) {
            throw new IllegalArgumentException(Constant.INVALID_PRODUCT);
		}
		 
		 return priceDao.findByProductId(productId);
		 

	}
	
}