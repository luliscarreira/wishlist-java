package com.ecom.wishlist.controller.dto;

public class WishlistExistsResponse {
	private boolean exists;

	public WishlistExistsResponse(boolean exists) {
		this.exists = exists;
	}

	public boolean isExists() {
		return exists;
	}
}
