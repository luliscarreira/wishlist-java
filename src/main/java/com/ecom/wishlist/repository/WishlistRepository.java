package com.ecom.wishlist.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ecom.wishlist.repository.entity.WishlistEntity;

@Repository
public interface WishlistRepository extends MongoRepository<WishlistEntity, String> {
}
