package com.ecom.wishlist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.wishlist.controller.dto.WishlistExistsResponse;
import com.ecom.wishlist.controller.dto.WishlistDto;
import com.ecom.wishlist.controller.mapper.WishlistMapper;
import com.ecom.wishlist.repository.entity.WishlistEntity;
import com.ecom.wishlist.service.WishlistService;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

	@Autowired
	private WishlistService wishlistService;

	@GetMapping()
	public ResponseEntity<List<WishlistDto>> getAllWishlistProductsFromUser(
			@RequestParam(value = "userId") String userId) {
		List<WishlistDto> wishlist = wishlistService.getAllWishlistProductsFromUser(userId)
				.stream()
				.map(WishlistMapper::toResponse)
				.toList();

		return ResponseEntity.ok(wishlist);
	}

	@PostMapping()
	public ResponseEntity<WishlistDto> saveProductToUserWishlist(@RequestBody WishlistDto wishlistRequest) {
		try {
			WishlistEntity wishlistEntity = WishlistMapper.toEntity(wishlistRequest);
			WishlistEntity savedWishlistEntity = wishlistService.saveProductToUserWishlist(wishlistEntity);
			WishlistDto wishlistResponse = WishlistMapper.toResponse(savedWishlistEntity);

			return ResponseEntity.status(HttpStatus.CREATED).body(wishlistResponse);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping
	public ResponseEntity<?> removeProductFromUserWishlist(@RequestBody WishlistDto wishlistRequest) {
		try {
			WishlistEntity wishlistEntity = WishlistMapper.toEntity(wishlistRequest);
			wishlistService.removeProductFromUserWishlist(wishlistEntity);

			return ResponseEntity.noContent().build();
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/check")
	public ResponseEntity<WishlistExistsResponse> isProductInUserWishlist(
			@RequestBody WishlistDto wishlistRequest) {
		WishlistEntity wishlistEntity = WishlistMapper.toEntity(wishlistRequest);
		Boolean wishlistExists = wishlistService.isProductInUserWishlist(wishlistEntity);
		WishlistExistsResponse response = WishlistMapper.toExistsResponse(wishlistExists);

		return ResponseEntity.ok(response);
	}
}
