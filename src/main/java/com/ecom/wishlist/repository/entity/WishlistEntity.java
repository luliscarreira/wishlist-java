package com.ecom.wishlist.repository.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "wishlist")
public class WishlistEntity {

	@MongoId
	private String id;
	private String productId;
	private String userId;

	public WishlistEntity() {
	}

	public String getId() {
		return id;
	}

	public String getProductId() {
		return productId;
	}

	public String getUserId() {
		return userId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
