package com.ecom.wishlist.repository.entity;

import org.junit.jupiter.api.Test;

public class WishlistEntityTests {
    @Test
    public void givenOnlyId_whenSettingId_thenIdIsSet() {
        WishlistEntity wishlistEntity = new WishlistEntity();
        wishlistEntity.setId("1");

        assert wishlistEntity.getId().equals("1");
    }

    @Test
    public void givenOnlyProductId_whenSettingProductId_thenProductIdIsSet() {
        WishlistEntity wishlistEntity = new WishlistEntity();
        wishlistEntity.setProductId("1");

        assert wishlistEntity.getProductId().equals("1");
    }

    @Test
    public void givenOnlyUserId_whenSettingWishlistEntity_thenUserIdIsSet() {
        WishlistEntity wishlistEntity = new WishlistEntity();
        wishlistEntity.setUserId("1");

        assert wishlistEntity.getUserId().equals("1");
    }
}