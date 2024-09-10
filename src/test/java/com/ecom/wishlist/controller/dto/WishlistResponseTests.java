package com.ecom.wishlist.controller.dto;

import org.junit.jupiter.api.Test;

public class WishlistResponseTests {

    @Test
    public void givenUserIdAndProductId_whenInstantiated_thenWishlistResponseIsCreated() {
        WishlistResponse response = new WishlistResponse("userId", "productId");

        assert response.userId().equals("userId");
        assert response.productId().equals("productId");
    }

    @Test
    public void givenJustUserId_whenInstantiated_thenWishlistResponseIsCreated() {
        WishlistResponse response = new WishlistResponse("userId", null);

        assert response.userId().equals("userId");
        assert response.productId() == null;
    }

    @Test
    public void givenJustProductId_whenInstantiated_thenWishlistResponseIsCreated() {
        WishlistResponse response = new WishlistResponse(null, "productId");

        assert response.userId() == null;
        assert response.productId().equals("productId");
    }
}
