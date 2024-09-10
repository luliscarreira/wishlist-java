package com.ecom.wishlist.controller;

import com.ecom.wishlist.controller.dto.WishlistRequest;
import com.ecom.wishlist.controller.dto.WishlistExistsResponse;
import com.ecom.wishlist.controller.dto.WishlistResponse;
import com.ecom.wishlist.controller.mapper.WishlistMapper;
import com.ecom.wishlist.repository.entity.WishlistEntity;
import com.ecom.wishlist.service.WishlistService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class WishlistControllerTests {
    @Mock
    private WishlistService wishlistService;

    @InjectMocks
    private WishlistController wishlistController;

    // Tests for getAllWishlistProductsFromUser
    @Test
    public void givenUserWithOneProductInWishlist_whenGetAllWishlistProductsFromUserIsCalled_thenOneProductIsReturned() {
        String userId = "user1";
        WishlistEntity wishlistEntity = new WishlistEntity();
        wishlistEntity.setUserId(userId);

        Mockito.when(wishlistService.getAllWishlistProductsFromUser(userId)).thenReturn(List.of(wishlistEntity));

        ResponseEntity<List<WishlistResponse>> response = wishlistController.getAllWishlistProductsFromUser(userId);

        Mockito.verify(wishlistService).getAllWishlistProductsFromUser(userId);

        assert response.getStatusCode().is2xxSuccessful();
        assert response.getBody().size() == 1;
    }

    @Test
    public void givenUserWithMultipleProductsInWishlist_whenGetAllWishlistProductsFromUserIsCalled_thenMultipleProductsAreReturned() {
        String userId = "user1";
        WishlistEntity wishlistEntity = new WishlistEntity();
        wishlistEntity.setUserId(userId);
        wishlistEntity.setProductId("product1");
        WishlistEntity wishlistEntity2 = new WishlistEntity();
        wishlistEntity2.setUserId(userId);
        wishlistEntity2.setProductId("product2");

        Mockito.when(wishlistService.getAllWishlistProductsFromUser(userId)).thenReturn(List.of(wishlistEntity, wishlistEntity2));

        ResponseEntity<List<WishlistResponse>> response = wishlistController.getAllWishlistProductsFromUser(userId);

        Mockito.verify(wishlistService).getAllWishlistProductsFromUser(userId);

        assert response.getStatusCode().is2xxSuccessful();
        assert response.getBody().size() == 2;
    }

    @Test
    public void givenUserWithNoProductsInWishlist_whenGetAllWishlistProductsFromUserIsCalled_thenNoProductsAreReturned() {
        String userId = "user1";

        Mockito.when(wishlistService.getAllWishlistProductsFromUser(userId)).thenReturn(List.of());

        ResponseEntity<List<WishlistResponse>> response = wishlistController.getAllWishlistProductsFromUser(userId);

        Mockito.verify(wishlistService).getAllWishlistProductsFromUser(userId);

        assert response.getStatusCode().is2xxSuccessful();
        assert response.getBody().isEmpty();
    }

    // Tests for saveProductToUserWishlist
    @Test
    public void givenValidWishlistRequest_whenSaveProductToUserWishlistIsCalled_thenValidWishlistResponseIsReturned() {
        String userId = "user1";
        String productId = "product1";
        WishlistRequest request = new WishlistRequest(productId);
        WishlistEntity wishlistEntity = WishlistMapper.toEntity(userId, request);

        Mockito.when(wishlistService.saveProductToUserWishlist(Mockito.any(WishlistEntity.class))).thenReturn(wishlistEntity);

        ResponseEntity<WishlistResponse> response = wishlistController.saveProductToUserWishlist(userId, request);

        ArgumentCaptor<WishlistEntity> wishlistEntityArgumentCaptor = ArgumentCaptor.forClass(WishlistEntity.class);
        Mockito.verify(wishlistService).saveProductToUserWishlist(wishlistEntityArgumentCaptor.capture());

        assert wishlistEntityArgumentCaptor.getValue().getUserId().equals(userId);
        assert wishlistEntityArgumentCaptor.getValue().getProductId().equals(productId);

        assert response.getStatusCode().is2xxSuccessful();
        assert response.getBody().userId().equals(userId);
        assert response.getBody().productId().equals(productId);
    }

    @Test
    public void givenNullUserId_whenSaveProductToUserWishlistIsCalled_thenIllegalArgumentExceptionIsThrown() {
        String productId = "product1";
        WishlistRequest request = new WishlistRequest(productId);

        Mockito.when(wishlistService.saveProductToUserWishlist(Mockito.any())).thenThrow(new IllegalArgumentException());

        ResponseEntity<WishlistResponse> response = wishlistController.saveProductToUserWishlist(null, request);

        assert response.getStatusCode().is4xxClientError();
    }

    // Tests for removeProductFromUserWishlist
    @Test
    public void givenValidWishlistRequest_whenRemoveProductFromUserWishlistIsCalled_thenNoContentIsReturned() {
        String userId = "user1";
        String productId = "product1";
        WishlistRequest request = new WishlistRequest(productId);

        Mockito.doNothing().when(wishlistService).removeProductFromUserWishlist(Mockito.any(WishlistEntity.class));

        ResponseEntity<?> response = wishlistController.removeProductFromUserWishlist(userId, request);

        assert response.getStatusCode().equals(HttpStatus.NO_CONTENT);
    }

    @Test
    public void givenProductNotFoundInWishlist_whenRemoveProductFromUserWishlistIsCalled_thenIllegalArgumentExceptionIsThrown() {
        String userId = "user1";
        String productId = "product1";
        WishlistRequest request = new WishlistRequest(productId);

        Mockito.doThrow(new IllegalArgumentException()).when(wishlistService).removeProductFromUserWishlist(Mockito.any(WishlistEntity.class));

        ResponseEntity<?> response = wishlistController.removeProductFromUserWishlist(userId, request);

        assert response.getStatusCode().equals(HttpStatus.NOT_FOUND);
    }

    // Tests for isProductInUserWishlist
    @Test
    public void givenProductInWishlist_whenIsProductInUserWishlistIsCalled_thenWishlistExistsResponseIsReturned() {
        String userId = "user1";
        String productId = "product1";
        WishlistRequest request = new WishlistRequest(productId);
        WishlistEntity wishlistEntity = WishlistMapper.toEntity(userId, request);

        Mockito.when(wishlistService.isProductInUserWishlist(Mockito.any(WishlistEntity.class))).thenReturn(true);

        ResponseEntity<WishlistExistsResponse> response = wishlistController.isProductInUserWishlist(userId, request);

        assert response.getStatusCode().is2xxSuccessful();
        assert response.getBody().exists();
    }

    @Test
    public void givenProductNotInWishlist_whenIsProductInUserWishlistIsCalled_thenWishlistDoesNotExistResponseIsReturned() {
        String userId = "user1";
        String productId = "product1";
        WishlistRequest request = new WishlistRequest(productId);
        WishlistEntity wishlistEntity = WishlistMapper.toEntity(userId, request);

        Mockito.when(wishlistService.isProductInUserWishlist(Mockito.any(WishlistEntity.class))).thenReturn(false);

        ResponseEntity<WishlistExistsResponse> response = wishlistController.isProductInUserWishlist(userId, request);

        assert response.getStatusCode().is2xxSuccessful();
        assert !response.getBody().exists();
    }
}
