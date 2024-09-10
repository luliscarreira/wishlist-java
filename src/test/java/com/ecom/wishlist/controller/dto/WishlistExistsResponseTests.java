package com.ecom.wishlist.controller.dto;

import org.junit.jupiter.api.Test;

public class WishlistExistsResponseTests {

    @Test
    public void givenExists_whenInstantiated_thenWishlistExistsResponseIsCreated() {
        WishlistExistsResponse response = new WishlistExistsResponse(true);
        assert(response.exists());
    }

    @Test
    public void givenNotExists_whenInstantiated_thenWishlistExistsResponseIsCreated() {
        WishlistExistsResponse response = new WishlistExistsResponse(false);
        assert(!response.exists());
    }
}
