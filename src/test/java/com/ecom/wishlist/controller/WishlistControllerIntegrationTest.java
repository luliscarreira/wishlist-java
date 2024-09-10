package com.ecom.wishlist.controller;

import com.ecom.wishlist.repository.WishlistRepository;
import com.ecom.wishlist.repository.entity.WishlistEntity;
import com.ecom.wishlist.service.WishlistService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest({WishlistController.class, WishlistService.class})
public class WishlistControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private WishlistRepository repository;

    @Test
    public void givenWishlist_whenGetWishlist_thenReturnJsonArray()
            throws Exception {
        WishlistEntity wishlistEntity = new WishlistEntity();
        wishlistEntity.setUserId("userId");
        wishlistEntity.setUserId("productId");

        Mockito.when(repository.findAll()).thenReturn(List.of(wishlistEntity));

        mvc.perform(get("/wishlist/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}