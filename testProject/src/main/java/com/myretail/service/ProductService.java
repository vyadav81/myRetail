package com.myretail.service;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.myretail.common.constant.Constant;
import com.myretail.dao.PriceDao;
import com.myretail.model.product.CurrentPrice;
import com.myretail.model.product.Product;
import com.myretail.exception.ProductNotFoundException;

/**
 * This Service class is responsible for getting product data from third party api.
 * 
 * @author vyadav
 *
 */
@Service
@PropertySource("classpath:thirdParty.properties")
public class ProductService {

	@Value("${redsky.url}")
	private String thirdPartyBaseURL;
	@Value("${redsky.suffix}")
	private String thirdPartyURLSuffix;

	@Value("${myprice.url}")
	private String priceAPIBaseURL;
	@Value("${myprice.suffix}")
	private String priceAPIBaseSuffix;

	public static Logger logger = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	PriceDao priceDao;

	private RestTemplate restTemplate = new RestTemplate();

	/*
	 * public void setLoggerObject(Long Id){ if(logger!=null){ logger =
	 * LoggerFactory.getLogger(ProductService.class); } }
	 */

	/**
	 * This method performs combines data from external service and
	 * 
	 * @param productId
	 *            for which product details need to fetched
	 * @return Product Business object
	 * @throws Exception
	 *             generic exception
	 */
	public Product getProductDetails(Long productId) throws Exception {
		logger.info("BEGIN ProductService.getProductDetails with productId : "
				+ productId);
		Product productBO = getProductDetailsFromThirdParty(productId);
		CurrentPrice currentPrice = getProductPriceDetailsFromThirdParty(productId);
		productBO.setCurrentPrice(currentPrice);
		logger.info("END ProductService.getProductDetails with product detail : "
				+ productBO.toString());
		return productBO;
	}

	/**
	 * This method performs a HTTP GET call to an external service and get
	 * product information product details in JSON fromat
	 * 
	 * @param productId
	 *            for which product details need to fetched
	 * @return Product Business object
	 * @throws Exception
	 *             generic exception
	 */
	public Product getProductDetailsFromThirdParty(Long productId)
			throws Exception {
		try {

			logger.info("BEGIN ProductService.getProductDetailsFromThirdParty with productId : "
					+ productId);
			String Url = (thirdPartyBaseURL != null ? thirdPartyBaseURL : "")
					+ productId
					+ (thirdPartyURLSuffix != null ? thirdPartyURLSuffix : "");
			logger.info("Fetching Data from URL : " + Url);
			String jsonData = restTemplate.getForObject(Url, String.class);

			JSONObject obj = new JSONObject(jsonData);
			JSONObject product = obj.getJSONObject(Constant.PRODUCT);
			JSONObject item = product.getJSONObject(Constant.ITEM);
			JSONObject productDescription = item
					.getJSONObject(Constant.PRODUCT_DESCRIPTION);
			Product productBO = new Product();
			productBO.setId(productId);
			productBO.setName(productDescription.getString(Constant.TITLE));
			return productBO;
		} catch (Exception ex) {
			logger.error("Exception 'ProductNotFoundException' thrown");
			throw new ProductNotFoundException(Constant.PRODUCTNOTFOUND);
		}
	}

	/**
	 * This method performs a HTTP GET call to get price information
	 * 
	 * @param productId for which product details need to fetched
	 * @return Current price business object
	 * @throws Exception generic exception
	 */
	public CurrentPrice getProductPriceDetailsFromThirdParty(Long productId)
			throws Exception {
		logger.info("BEGIN ProductService.getProductPriceDetailsFromThirdParty with productId : "
				+ productId);
		String Url = (priceAPIBaseURL != null ? priceAPIBaseURL : "")
				+ productId
				+ (priceAPIBaseSuffix != null ? priceAPIBaseSuffix : "");
		logger.info("Fetching price Data from URL : " + Url);
		CurrentPrice cp = restTemplate.getForObject(Url, CurrentPrice.class);
		return cp;
	}

	/**
	 * this method will call interact with price dao for updating the price
	 * 
	 * @param productID for which price needs to be updated
	 * @param cp price data to be updated
	 * @return success status message
	 * @throws Exception generic exception
	 */
	public String updatePrice(Long productID, CurrentPrice cp) throws Exception {
		logger.info("BEGIN ProductService.updatePrice with productId : "
				+ productID + " Price :" + cp.toString());
		cp.setProductId(productID);
		return priceDao.updateProductPrice(cp);
	}

}
