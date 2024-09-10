package com.ecom.wishlist.repository.entity;

import org.junit.jupiter.api.Test;

public class WishlistEntityTests {
    @Test
    void givenOnlyId_whenSettingId_thenIdIsSet() {
        WishlistEntity wishlistEntity = new WishlistEntity();
        wishlistEntity.setId("1");

        assert wishlistEntity.getId().equals("1");
    }

    @Test
    void givenOnlyProductId_whenSettingProductId_thenProductIdIsSet() {
        WishlistEntity wishlistEntity = new WishlistEntity();
        wishlistEntity.setProductId("1");

        assert wishlistEntity.getProductId().equals("1");
    }

    @Test
    void givenOnlyUserId_whenSettingWishlistEntity_thenUserIdIsSet() {
        WishlistEntity wishlistEntity = new WishlistEntity();
        wishlistEntity.setUserId("1");

        assert wishlistEntity.getUserId().equals("1");
    }
}