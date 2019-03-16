/**
 * 
 */
package com.myretail.model.product;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Price business object, which will represent an unique product in Retail
 * system. It will be the Entity to Store/Retrieve price information.
 * 
 * @author vyadav
 */
@Document(collection = "CurrentPrice")
@ApiModel(description = "Price Business Object")
public class CurrentPrice {
	/** Unique Id/Primary key of this entity */
	@Id
	@JsonIgnore
	private ObjectId id;
	/** Unique Id to identify product */
	@JsonIgnore
	private Long productId;

	/** To identify the current Price */
	@JsonProperty("value")
	@ApiModelProperty(notes = "Current price", position = 1)
	private Float price;

	/** To identify the currenct code */
	@JsonProperty("currency_code")
	@ApiModelProperty(notes = "Currency code", position = 2)
	private String currency;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "current_price :{" + " value:" + price + ", currency_code:'"
				+ currency + '\'' + '}';
	}

}
