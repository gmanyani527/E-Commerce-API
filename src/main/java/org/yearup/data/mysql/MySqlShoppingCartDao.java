package org.yearup.data.mysql;


import org.yearup.data.ShoppingCartDao;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlShoppingCartDao implements ShoppingCartDao
{
    private final DataSource dataSource;

    public MySqlShoppingCartDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addToCart(int userId, int productId) {
        String sql = "INSERT INTO shopping_cart (user_id, product_id, quantity) VALUES (?, ?, 1)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error adding to cart", e);
        }
    }

    @Override
    public void updateCartItem(int userId, int productId, int quantity) {
        // UPDATE shopping_cart SET quantity = ? WHERE ...
    }

    @Override
    public void clearCart(int userId) {
        // DELETE FROM shopping_cart WHERE ...
    }

    @Override
    public ShoppingCart getByUserId(int userId) {
        return null;
    }

    @Override
    public List<ShoppingCartItem> getCartItemsByUserId(int userId) {
        // SELECT * FROM shopping_cart WHERE user_id = ...
        return new ArrayList<>();
    }
}