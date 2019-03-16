/**
 * 
 */
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

import com.myretail.dao.PriceDao;
import com.myretail.model.product.CurrentPrice;
import com.myretail.controller.PriceRestController;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PriceRestController.class)
public class PriceRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	PriceDao priceDao;
	public static Long id = new Long(13860428);

	@Test
	public void getPrice() throws Exception {

		CurrentPrice cp = new CurrentPrice();
		cp.setProductId(id);
		cp.setPrice((float) 2011.11);
		cp.setCurrency("USD");

		Mockito.when(priceDao.findByProductId(id)).thenReturn(cp);

		RequestBuilder reqBldr = MockMvcRequestBuilders
				.get("/product/13860428/price").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(reqBldr).andReturn();

		String expected = "{value:2011.11,currency_code:USD}";

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
}
