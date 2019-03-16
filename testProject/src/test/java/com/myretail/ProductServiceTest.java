/**
 * 
 */
package com.myretail;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.myretail.model.product.CurrentPrice;
import com.myretail.model.product.Product;
import com.myretail.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
@PropertySource("classpath:thirdParty.properties")
public class ProductServiceTest {

	@Value("${redsky.url}")
	private String thirdPartyBaseURL;
	@Value("${redsky.suffix}")
	private String thirdPartyURLSuffix;

	@Mock
	private ProductService productService;

	@InjectMocks
	private ProductService productService1;
	public static Long id = new Long(13860428);
	public static String jsonString = "{\"product\": {\"item\": {\"product_description\": {\"title\": \"TEST MY DATA\"}}}}";

	@Mock
	RestTemplate restTemplate;

	@Test
	public void getProductDetails() throws Exception {
		CurrentPrice cp = new CurrentPrice();
		cp.setProductId(id);
		cp.setPrice((float) 2011.11);
		cp.setCurrency("USD");
		Product productBO = new Product();
		productBO.setId(id);
		productBO.setName("test product");
		productBO.setCurrentPrice(null);

		Mockito.when(productService.getProductDetailsFromThirdParty(id))
				.thenReturn(productBO);
		Mockito.when(productService.getProductPriceDetailsFromThirdParty(id))
				.thenReturn(cp);
		Mockito.when(productService.getProductDetails(id)).thenCallRealMethod();

		Product productRet = productService.getProductDetails(id);
		assertEquals(cp.getProductId(), productRet.getId());
		assertEquals(cp.getCurrency(), productRet.getCurrentPrice()
				.getCurrency());
		assertEquals(cp.getPrice(), productRet.getCurrentPrice().getPrice());
		assertEquals(productBO.getName(), productRet.getName());
	}

	@Test
	public void getProductDetailsFromThirdParty() throws Exception {
		Mockito.when(restTemplate.getForObject(id.toString(), String.class))
				.thenReturn(jsonString);
		Product productRet = productService1
				.getProductDetailsFromThirdParty(id);
		assertEquals("TEST MY DATA", productRet.getName());
	}

}
