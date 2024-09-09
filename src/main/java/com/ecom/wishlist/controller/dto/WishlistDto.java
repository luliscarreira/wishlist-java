package com.ecom.wishlist.controller.dto;

public class WishlistDto {
	private String userId;
	private String productId;

	public WishlistDto(String userId, String productId) {
		this.userId = userId;
		this.productId = productId;
	}

	public String getUserId() {
		return userId;
	}

	public String getProductId() {
		return productId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
}
