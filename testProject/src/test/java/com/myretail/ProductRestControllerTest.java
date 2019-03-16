package com.myretail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.myretail.model.product.CurrentPrice;
import com.myretail.model.product.Product;
import com.myretail.controller.ProductRestController;
import com.myretail.service.ProductService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductRestController.class)
public class ProductRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;
	
	
	
	public static Long id = new Long(13860428);

	@Test
	public void getProductDetails() throws Exception {

		
		CurrentPrice cp = new CurrentPrice();
		cp.setProductId(id);
		cp.setPrice((float) 2011.11);
		cp.setCurrency("USD");
		Product productBO = new Product();
		productBO.setId(id);
		productBO.setName("test product");
		productBO.setCurrentPrice(cp);

		Mockito.when(productService.getProductDetails(id)).thenReturn(productBO);

		RequestBuilder reqBldr = MockMvcRequestBuilders
				.get("/product/13860428").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(reqBldr).andReturn();

		String expected = "{id:13860428,name:'test product',current_price:{value:2011.11,currency_code:USD}}";
		
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
}
