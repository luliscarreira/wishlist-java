package com.ecom.wishlist.controller.mapper;

import com.ecom.wishlist.controller.dto.WishlistExistsResponse;
import com.ecom.wishlist.controller.dto.WishlistDto;
import com.ecom.wishlist.repository.entity.WishlistEntity;

public class WishlistMapper {
	public static WishlistEntity toEntity(WishlistDto dto) {
		WishlistEntity wishlistEntity = new WishlistEntity();
		wishlistEntity.setProductId(dto.getProductId());
		wishlistEntity.setUserId(dto.getUserId());

		return wishlistEntity;
	}

	public static WishlistDto toResponse(WishlistEntity wishlistEntity) {
		return new WishlistDto(wishlistEntity.getUserId(), wishlistEntity.getProductId());
	}

	public static WishlistExistsResponse toExistsResponse(boolean exists) {
		return new WishlistExistsResponse(exists);
	}
}
