package com.ecom.wishlist.controller.dto;

import org.junit.jupiter.api.Test;

public class WishlistRequestTests {

    @Test
    public void givenProductId_whenInstantiated_thenWishlistRequestIsCreated() {
        WishlistRequest request = new WishlistRequest("wishlist");

        assert(request.productId().equals("wishlist"));
    }
}
