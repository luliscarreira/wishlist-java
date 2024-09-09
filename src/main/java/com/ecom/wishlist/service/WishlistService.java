package com.ecom.wishlist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.ecom.wishlist.repository.WishlistRepository;
import com.ecom.wishlist.repository.entity.WishlistEntity;

@Service
public class WishlistService {

	@Autowired
	private WishlistRepository wishlistRepository;

	public List<WishlistEntity> getAllWishlistProductsFromUser(String userId) {
		WishlistEntity wishlistEntity = new WishlistEntity();
		wishlistEntity.setUserId(userId);
		Example<WishlistEntity> example = Example.of(wishlistEntity);

		return wishlistRepository.findAll(example);
	}

	public WishlistEntity saveProductToUserWishlist(WishlistEntity wishlistEntity) {
		validateWishlist(wishlistEntity);

		return wishlistRepository.save(wishlistEntity);
	}

	public void removeProductFromUserWishlist(WishlistEntity wishlistEntity) {
		Example<WishlistEntity> example = Example.of(wishlistEntity);
		WishlistEntity entity = wishlistRepository.findOne(example)
				.orElseThrow(() -> new IllegalArgumentException("Product not found in wishlist"));

		wishlistRepository.delete(entity);
	}

	public boolean isProductInUserWishlist(WishlistEntity wishlistEntity) {
		Example<WishlistEntity> example = Example.of(wishlistEntity);

		return wishlistRepository.exists(example);
	}

	private void validateWishlist(WishlistEntity wishlistEntity) {
		if (wishlistEntity.getUserId() == null || wishlistEntity.getProductId() == null) {
			throw new IllegalArgumentException("User ID and Product ID are required");
		}

		List<WishlistEntity> wishlist = getAllWishlistProductsFromUser(wishlistEntity.getUserId());
		if (wishlist.size() >= 20) {
			throw new IllegalArgumentException("Wishlist is full");
		}

		if (isProductInUserWishlist(wishlistEntity)) {
			throw new IllegalArgumentException("Product already in wishlist");
		}
	}
}
