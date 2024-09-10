package com.ecom.wishlist.controller.mapper;

import com.ecom.wishlist.controller.dto.WishlistRequest;
import com.ecom.wishlist.controller.dto.WishlistResponse;
import com.ecom.wishlist.controller.dto.WishlistExistsResponse;
import com.ecom.wishlist.repository.entity.WishlistEntity;
import org.junit.jupiter.api.Test;

public class WishlistMapperTests {

    @Test
    public void givenUserIdAndProductId_whenToEntityIsCalled_thenWishlistEntityIsCreatedWithSameData() {
        String userId = "1";
        WishlistRequest request = new WishlistRequest( "productId");

        WishlistEntity entity = WishlistMapper.toEntity(userId, request);

        assert entity.getUserId().equals(userId);
        assert entity.getProductId().equals(request.productId());
    }

    @Test
    public void givenUserIdAndProductId_whenToResponseIsCalled_thenWishlistDtoIsCreatedWithSameData() {
        WishlistEntity entity = new WishlistEntity();
        entity.setUserId("userId");
        entity.setProductId("productId");

        WishlistResponse dto = WishlistMapper.toResponse(entity);

        assert dto.userId().equals(entity.getUserId());
        assert dto.productId().equals(entity.getProductId());
    }

    @Test
    public void givenExists_whenToExistsResponseIsCalled_thenWishlistExistsResponseIsCreatedWithSameData() {
        boolean exists = true;

        WishlistExistsResponse response = WishlistMapper.toExistsResponse(exists);

        assert response.exists() == exists;
    }
}
