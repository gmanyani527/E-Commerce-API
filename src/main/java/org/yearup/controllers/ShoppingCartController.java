package org.yearup.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.ProductDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.data.UserDao;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
import org.yearup.models.User;

import java.security.Principal;
import java.util.List;

// convert this class to a REST controller
// only logged in users should have access to these actions

@RestController
@RequestMapping("/cart")
@CrossOrigin
public class ShoppingCartController
{
    // a shopping cart requires
    private ShoppingCartDao shoppingCartDao;
    private UserDao userDao;
    private ProductDao productDao;


    public ShoppingCartController(UserDao userDao, ShoppingCartDao shoppingCartDao)
    {
        this.userDao = userDao;
        this.shoppingCartDao = shoppingCartDao;
    }

    // each method in this controller requires a Principal object as a parameter
 /*   public ShoppingCart getCart(Principal principal)
    {
        try
        {
            // get the currently logged in username
            String userName = principal.getName();
            // find database user by userId
            User user = userDao.getByUserName(userName);
            int userId = user.getId();

            // use the shoppingcartDao to get all items in the cart and return the cart
            return null;
        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    } */

    // add a POST method to add a product to the cart - the url should be
    // https://localhost:8080/cart/products/15 (15 is the productId to be added
    @GetMapping("")
    public List<ShoppingCartItem> getCart(Principal principal)
    {
        try {
            String username = principal.getName();
            User user = userDao.getByUsername(username);

            if (user == null) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found.");
            }

            int userId = user.getId();
            return shoppingCartDao.getCartItemsByUserId(userId);

        } catch (ResponseStatusException e) {
            throw e; // Re-throw custom exceptions
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to fetch cart.", e);
        }
    }

    @PostMapping("/products/{productId}")
    public void addToCart(@PathVariable int productId, Principal principal) {
        String username = principal.getName();
        int userId = userDao.getByUsername(username).getId();
        shoppingCartDao.addToCart(userId, productId);
    }
    // add a PUT method to update an existing product in the cart - the url should be
    // https://localhost:8080/cart/products/15 (15 is the productId to be updated)
    // the BODY should be a ShoppingCartItem - quantity is the only value that will be updated
    @PutMapping("/products/{productId}")
    public void updateCartItem(@PathVariable int productId,
                               @RequestBody ShoppingCartItem item,
                               Principal principal)
    {
        String username = principal.getName();
        int userId = userDao.getByUsername(username).getId();
        int quantity = item.getQuantity();

        shoppingCartDao.updateCartItem(userId, productId, quantity);
    }


    // add a DELETE method to clear all products from the current users cart
    // https://localhost:8080/cart
    @DeleteMapping("")
    public void clearCart(Principal principal)
    {
        String username = principal.getName();
        int userId = userDao.getByUsername(username).getId();

        shoppingCartDao.clearCart(userId);
    }

}
