package com.myretail.model.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Product business object, which will represent an unique product in Retail
 * 
 * @author vyadav
 *
 */

@ApiModel(description = "Product Business Object")
public class Product {

	/** Unique Id/Primary key of this entity */
	@ApiModelProperty(notes = "Product id", position = 1)
	private Long id;

	/** To identify product name/title */
	@ApiModelProperty(notes = "Product name", position = 2)
	private String name;

	/** To identify price related information */
	@JsonProperty("current_price")
	@ApiModelProperty(notes = "Current Price", position = 3)
	private CurrentPrice currentPrice;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CurrentPrice getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(CurrentPrice currentPrice) {
		this.currentPrice = currentPrice;
	}

	@Override
	public String toString() {
		return "Product{" + " id:" + id + ", name:'" + name + '\'' + ","
				+ (currentPrice != null ? currentPrice.toString() : "") + '}';
	}
}
