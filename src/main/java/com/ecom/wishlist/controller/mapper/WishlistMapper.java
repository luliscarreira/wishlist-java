package com.ecom.wishlist.controller.mapper;

import com.ecom.wishlist.controller.dto.WishlistExistsResponse;
import com.ecom.wishlist.controller.dto.WishlistRequest;
import com.ecom.wishlist.controller.dto.WishlistResponse;
import com.ecom.wishlist.repository.entity.WishlistEntity;

public class WishlistMapper {
	public static WishlistEntity toEntity(String userId, WishlistRequest request) {
		WishlistEntity wishlistEntity = new WishlistEntity();
		wishlistEntity.setUserId(userId);
		wishlistEntity.setProductId(request.productId());

		return wishlistEntity;
	}

	public static WishlistResponse toResponse(WishlistEntity wishlistEntity) {
		return new WishlistResponse(wishlistEntity.getUserId(), wishlistEntity.getProductId());
	}

	public static WishlistExistsResponse toExistsResponse(boolean exists) {
		return new WishlistExistsResponse(exists);
	}
}
