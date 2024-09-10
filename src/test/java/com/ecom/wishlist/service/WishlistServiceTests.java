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
import org.springframework.data.domain.Example;

import java.util.stream.IntStream;

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

	// Tests for saveProductToUserWishlist
	@Test
	void givenProductWithoutUserId_whenSaveProductToUserWishlist_thenExceptionIsThrown() {
		WishlistEntity wishlistEntity = new WishlistEntity();
		wishlistEntity.setProductId("1");

		assertThrows(IllegalArgumentException.class, () -> {
			this.wishlistService.saveProductToUserWishlist(wishlistEntity);
		});
	}

	@Test
	void givenProductWithoutProductId_whenSaveProductToUserWishlist_thenExceptionIsThrown() {
		WishlistEntity wishlistEntity = new WishlistEntity();
		wishlistEntity.setUserId("1");

		assertThrows(IllegalArgumentException.class, () -> {
			this.wishlistService.saveProductToUserWishlist(wishlistEntity);
		});
	}

	@Test
	void givenFullWishlist_whenSaveProductToUserWishlist_thenExceptionIsThrown() {
		WishlistEntity wishlistEntity = new WishlistEntity();
		wishlistEntity.setUserId("1");
		wishlistEntity.setProductId("1");

		Mockito
				.when(this.wishlistRepository.findAll(Mockito.any(Example.class)))
				.thenReturn(IntStream.range(0, 20).mapToObj(i -> new WishlistEntity()).toList());

		assertThrows(IllegalArgumentException.class, () -> {
			this.wishlistService.saveProductToUserWishlist(wishlistEntity);
		});
	}

	@Test
	void givenProductAlreadyInWishlist_whenSaveProductToUserWishlist_thenExceptionIsThrown() {
		WishlistEntity wishlistEntity = new WishlistEntity();
		wishlistEntity.setUserId("1");
		wishlistEntity.setProductId("1");

		Mockito
				.when(this.wishlistRepository.exists(Mockito.any(Example.class)))
				.thenReturn(true);

		assertThrows(IllegalArgumentException.class, () -> {
			this.wishlistService.saveProductToUserWishlist(wishlistEntity);
		});
	}

	@Test
	void givenProductNotInWishlist_whenSaveProductToUserWishlist_thenProductSavedOnRepository() {
		WishlistEntity wishlistEntity = new WishlistEntity();
		wishlistEntity.setUserId("1");
		wishlistEntity.setProductId("1");
		Mockito.when(this.wishlistRepository.save(Mockito.any(WishlistEntity.class))).thenReturn(wishlistEntity);

		this.wishlistService.saveProductToUserWishlist(wishlistEntity);

		Mockito.verify(this.wishlistRepository, Mockito.times(1)).save(Mockito.any(WishlistEntity.class));
	}

	// Tests for getAllWishlistProductsFromUser
	@Test
	void givenUserId_whenGetAllWishlistProductsFromUser_thenRepositoryFindAllCalled() {
		String userId = "1";

		this.wishlistService.getAllWishlistProductsFromUser(userId);

		Mockito.verify(this.wishlistRepository, Mockito.never()).save(Mockito.any(WishlistEntity.class));
	}

	// Tests for removeProductFromUserWishlist
	@Test
	void givenProductNotInWishlist_whenRemoveProductFromUserWishlistIsCalled_thenExceptionIsThrown(){
		WishlistEntity wishlistEntity = new WishlistEntity();
		wishlistEntity.setUserId("1");
		wishlistEntity.setProductId("1");

		Mockito
				.when(this.wishlistRepository.findOne(Mockito.any(Example.class)))
				.thenReturn(java.util.Optional.empty());

		assertThrows(IllegalArgumentException.class, () -> {
			this.wishlistService.removeProductFromUserWishlist(wishlistEntity);
		});
	}

	@Test
	void givenProductInWishlist_whenRemoveProductFromUserWishlistIsCalled_thenProductIsDeletedFromDB() {
		WishlistEntity wishlistEntity = new WishlistEntity();
		wishlistEntity.setUserId("1");
		wishlistEntity.setProductId("1");

		Mockito
				.when(this.wishlistRepository.findOne(Mockito.any(Example.class)))
				.thenReturn(java.util.Optional.of(wishlistEntity));

		this.wishlistService.removeProductFromUserWishlist(wishlistEntity);

		Mockito.verify(this.wishlistRepository, Mockito.times(1)).delete(Mockito.any(WishlistEntity.class));
	}

	// Tests for isProductInUserWishlist
	@Test
	void givenProductInWishlist_whenIsProductInUserWishlistIsCalled_thenTrueIsReturned() {
		WishlistEntity wishlistEntity = new WishlistEntity();
		wishlistEntity.setUserId("1");
		wishlistEntity.setProductId("1");

		Mockito
				.when(this.wishlistRepository.exists(Mockito.any(Example.class)))
				.thenReturn(true);

		assertTrue(this.wishlistService.isProductInUserWishlist(wishlistEntity));
	}
}
