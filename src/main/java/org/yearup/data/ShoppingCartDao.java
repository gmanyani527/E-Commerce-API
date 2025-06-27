package org.yearup.data;

import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import java.util.List;

public interface ShoppingCartDao
{
    ShoppingCart getByUserId(int userId);
    // add additional method signatures here
    List<ShoppingCartItem> getCartItemsByUserId(int userId);
    void addToCart(int userId, int productId);
    void updateCartItem(int userId, int productId, int quantity);
    void clearCart(int userId);
}
