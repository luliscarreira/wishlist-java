package com.ecom.wishlist.controller;

import java.util.List;

import com.ecom.wishlist.controller.dto.WishlistRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecom.wishlist.controller.dto.WishlistExistsResponse;
import com.ecom.wishlist.controller.dto.WishlistResponse;
import com.ecom.wishlist.controller.mapper.WishlistMapper;
import com.ecom.wishlist.repository.entity.WishlistEntity;
import com.ecom.wishlist.service.WishlistService;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

	@Autowired
	private WishlistService wishlistService;

	/**
	 * @param userId User ID
	 * @return List of products in the user's wishlist
	 */
	@GetMapping("/{userId}")
	@Operation(
			summary = "Get all wishlist products from user",
			responses = {
            	@ApiResponse(content = @Content(schema = @Schema(implementation = WishlistResponse.class)))
    		}
	)
	public ResponseEntity<List<WishlistResponse>> getAllWishlistProductsFromUser(
			@PathVariable String userId) {
		List<WishlistResponse> wishlist = wishlistService.getAllWishlistProductsFromUser(userId)
				.stream()
				.map(WishlistMapper::toResponse)
				.toList();

		return ResponseEntity.ok(wishlist);
	}

	/**
	 * @param userId          User ID
	 * @param wishlistRequest Wishlist request
	 * @return Wishlist response
	 * @throws IllegalArgumentException if user ID or product ID is null
	 */
	@PostMapping("/{userId}")
	@Operation(
			summary = "Save product to user wishlist",
			parameters = {
				@Parameter(name = "userId", required = true, description = "User ID"),
				@Parameter(name = "wishlistRequest", required = true, description = "Wishlist request")
			},
			responses = {
				@ApiResponse(content = @Content(schema = @Schema(implementation = WishlistResponse.class))),
				@ApiResponse(responseCode = "400", description = "Bad request")
			}
	)
	public ResponseEntity<WishlistResponse> saveProductToUserWishlist(
			@PathVariable String userId,
			@RequestBody WishlistRequest wishlistRequest) {
		try {
			WishlistEntity wishlistEntity = WishlistMapper.toEntity(userId, wishlistRequest);
			WishlistEntity savedWishlistEntity = wishlistService.saveProductToUserWishlist(wishlistEntity);
			WishlistResponse wishlistResponse = WishlistMapper.toResponse(savedWishlistEntity);

			return ResponseEntity.status(HttpStatus.CREATED).body(wishlistResponse);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	/**
	 * @param userId          User ID
	 * @param wishlistRequest Wishlist request
	 * @return No content
	 * @throws IllegalArgumentException if product is not found in the wishlist
	 */
	@DeleteMapping("/{userId}")
	@Operation(
			summary = "Remove product from user wishlist",
			parameters = {
				@Parameter(name = "userId", required = true, description = "User ID"),
				@Parameter(name = "wishlistRequest", required = true, description = "Wishlist request")
			},
			responses = {
				@ApiResponse(responseCode = "204", description = "No content"),
				@ApiResponse(responseCode = "404", description = "Not found")
			}
	)
	public ResponseEntity<?> removeProductFromUserWishlist(
			@PathVariable String userId,
			@RequestBody WishlistRequest wishlistRequest) {
		try {
			WishlistEntity wishlistEntity = WishlistMapper.toEntity(userId, wishlistRequest);
			wishlistService.removeProductFromUserWishlist(wishlistEntity);

			return ResponseEntity.noContent().build();
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * @param userId          User ID
	 * @param wishlistRequest Wishlist request
	 * @return Wishlist exists response
	 */
	@PostMapping("/{userId}/check")
	@Operation(
			summary = "Check if product is in user wishlist",
			parameters = {
				@Parameter(name = "userId", required = true, description = "User ID"),
				@Parameter(name = "wishlistRequest", required = true, description = "Wishlist request")
			},
			responses = {
				@ApiResponse(content = @Content(schema = @Schema(implementation = WishlistExistsResponse.class)))
			}
	)
	public ResponseEntity<WishlistExistsResponse> isProductInUserWishlist(
			@PathVariable String userId,
			@RequestBody WishlistRequest wishlistRequest) {
		WishlistEntity wishlistEntity = WishlistMapper.toEntity(userId, wishlistRequest);
		boolean wishlistExists = wishlistService.isProductInUserWishlist(wishlistEntity);
		WishlistExistsResponse response = WishlistMapper.toExistsResponse(wishlistExists);

		return ResponseEntity.ok(response);
	}
}
