package com.myretail.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myretail.common.constant.Constant;
import com.myretail.model.product.Product;
import com.myretail.service.ProductService;

/**
* This is RESTFul service that provides API to get the product name and pric
* It also provides API for changing the price of a product.
* 
* @author vyadav
* 
*/

@RestController
@EnableAutoConfiguration
@Api(value = "API to fetch product detail")
public class ProductRestController {
	
	private Product product;

	@Autowired
	private ProductService productService;
	
	Logger logger = LoggerFactory.getLogger(ProductRestController.class);
	
    /**
     * This method is a HTTP GET method.
     * It is used to get the product details and price for a given productId.
     * 
     * @param productId Get product details for this parameter. 
     * @return Product  Business object with product detail and price detail
     * @throws Exception Generic exception
     */
	@ApiOperation(value = "Get product detail for particular product Id", response = Product.class)
	@GetMapping(value="/product/{productId}")
	public Product getProductDetails(@ApiParam(value = "Product id", required = true) @PathVariable("productId") Long productId) throws Exception   {
		 logger.debug("BEGIN getProductDetails with productId : " + productId);
		 if (productId == null || productId <= 0) {
			    logger.error("The 'productId' parameter must not be null or empty or <=0");
			    throw new IllegalArgumentException(Constant.INVALID_PRODUCT);
	     }
		 
		 // service call to retrive product detail
		 product = productService.getProductDetails(productId);
		 logger.info("END getProductDetails product detail : " + product.toString());
		 return product;
	}
	
    /**
     * This method is a HTTP PUT method.
     * It is used to get update price for input productId.
     * 
     * @param productId Get product details for this parameter. 
     * @return Product  Business object with product detail and price detail
     * @throws Exception Generic exception
     */
	/**
	 * This method is a HTTP PUT method.
	 * It is used to get update price for input productId.
	 * 
	 * @param productId of product for which price will be updated
	 * @param productDetail Product detail along with price to be updated.
	 * @return Ppdated status
	 * @throws Exception Generic Exception
	 */
	@ApiOperation(value = "Update current price for product", response = Product.class)
	@PutMapping(value="/product/{productId}")
	public String updateProductPrice(@ApiParam(value = "Product id", required = true) @PathVariable("productId") Long productId, @ApiParam(value = "Product data  to be update", required = true) @RequestBody Product productDetail) throws Exception{
		logger.info("BEGIN updateProductPrice with request data " + productDetail.toString());
		if (productId == null || productId <= 0) {
			logger.error("The 'productId' parameter must not be null or empty or <=0");
            throw new IllegalArgumentException(Constant.INVALID_PRODUCT);
		}
		return productService.updatePrice(productId,productDetail.getCurrentPrice());
		
	}	
		
}