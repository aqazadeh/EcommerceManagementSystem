package com.aqazadeh.ecommerce.service.imp;

import com.aqazadeh.ecommerce.dto.request.CreateCartRequest;
import com.aqazadeh.ecommerce.dto.response.CartDto;
import com.aqazadeh.ecommerce.exception.ApplicationException;
import com.aqazadeh.ecommerce.exception.ExceptionType;
import com.aqazadeh.ecommerce.mapper.CartMapper;
import com.aqazadeh.ecommerce.model.Cart;
import com.aqazadeh.ecommerce.model.Product;
import com.aqazadeh.ecommerce.model.ProductVariant;
import com.aqazadeh.ecommerce.model.User;
import com.aqazadeh.ecommerce.repository.CartRepository;
import com.aqazadeh.ecommerce.service.CartService;
import com.aqazadeh.ecommerce.service.ProductService;
import com.aqazadeh.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 19.02.2024
 * Time: 01:51
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {
    private final ProductService productService;
    private final UserService userService;
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    @Override
    public void addToCart(User user, CreateCartRequest request) {
        List<Cart> cartList = user.getCart();

        Product product = productService.findById(request.productId());

        ProductVariant variant = product.getVariants().stream()
                .filter(var -> var.getId().equals(request.variantId()))
                .findFirst()
                .orElseThrow(() -> new ApplicationException(ExceptionType.VARIANT_NOT_FOUND));

        if (variant.getQuantity() < request.quantity()) {
            throw new ApplicationException(ExceptionType.STOCK_IS_ENDED);
        }

        Optional<Cart> cart = cartList.stream()
                .filter(cartItem -> cartItem.getVariant().equals(variant))
                .findFirst();

        if (cart.isEmpty()) {
            Cart build = Cart.builder()
                    .user(user)
                    .quantity(request.quantity())
                    .product(product)
                    .variant(variant)
                    .build();
            cartList.add(build);
        } else {
            Cart build = cart.get();
            build.setQuantity(build.getQuantity() + request.quantity());
        }

        userService.updateUser(user);

    }

    @Override
    public List<CartDto> getUserCart(User user) {
        List<Cart> cartList = user.getCart();
        return cartList.stream().map(cartMapper::toDto).toList();
    }

    @Override
    public void removeFromCart(User user, Long itemId) {
        Cart cart = findById(itemId);
        if (cart.getUser().equals(user))
            cartRepository.delete(cart);
        else
            throw new ApplicationException(ExceptionType.CART_ITEM_NOT_FOUND);

    }

    @Override
    public void clearCart(User user) {
        user.setCart(new ArrayList<>());
        userService.updateUser(user);
    }

    @Override
    public Cart findById(Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ExceptionType.CART_ITEM_NOT_FOUND));
    }
}
