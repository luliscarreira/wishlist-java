package com.ecom.wishlist.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecom.wishlist.repository.WishlistRepository;
import com.ecom.wishlist.repository.entity.WishlistEntity;

@ExtendWith(MockitoExtension.class)
public class WishlistServiceTests {
	@Mock
	private WishlistRepository wishlistRepository;

	@InjectMocks
	private WishlistService wishlistService;

	@BeforeAll
	public static void setUp() {
		MockitoAnnotations.openMocks(WishlistServiceTests.class);
	}

	@Test
	void whenValidProductAdded_thenProductSavedOnRepository() {
		WishlistEntity wishlistEntity = new WishlistEntity();
		wishlistEntity.setUserId("1");
		wishlistEntity.setProductId("1");
		Mockito.when(this.wishlistRepository.save(Mockito.any(WishlistEntity.class))).thenReturn(wishlistEntity);

		this.wishlistService.saveProductToUserWishlist(wishlistEntity);

		Mockito.verify(this.wishlistRepository, Mockito.times(1)).save(Mockito.any(WishlistEntity.class));
	}

	@Test
	void whenProductWithoutUserIdAdded_thenServiceThrowsException() {
		WishlistEntity wishlistEntity = new WishlistEntity();
		wishlistEntity.setProductId("1");

		assertThrows(IllegalArgumentException.class, () -> {
			this.wishlistService.saveProductToUserWishlist(wishlistEntity);
		}, "ASDADASß");
	}
}
