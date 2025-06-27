package org.yearup.data.mysql;


import org.springframework.stereotype.Repository;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
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
        String sql = "UPDATE shopping_cart SET quantity = ? WHERE user_id = ? AND product_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quantity);
            stmt.setInt(2, userId);
            stmt.setInt(3, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating cart item", e);
        }
    }

    @Override
    public void clearCart(int userId) {
        String sql = "DELETE FROM shopping_cart WHERE user_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error clearing cart", e);
        }
    }

    @Override
    public ShoppingCart getByUserId(int userId) {
        return null;
    }

    @Override
    public List<ShoppingCartItem> getCartItemsByUserId(int userId) {
        List<ShoppingCartItem> items = new ArrayList<>();

        String sql = "SELECT * FROM shopping_cart WHERE user_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ShoppingCartItem item = new ShoppingCartItem();
                item.setUserId(rs.getInt("user_id"));
                item.setProductId(rs.getInt("product_id"));
                item.setQuantity(rs.getInt("quantity"));
                // add more fields if needed

                items.add(item);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching shopping cart items", e);
        }

        return items;
    }
}