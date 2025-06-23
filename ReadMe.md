# 🛒 EasyShop – E-Commerce API & Website

EasyShop is a full-stack e-commerce platform for browsing, filtering, and purchasing products online. It includes a Spring Boot backend with RESTful APIs and a MySQL database, as well as a frontend website for users to interact with products, categories, profiles, carts, and orders.

This project was completed as **Capstone 3** for the *Java Fundamentals Program*, incorporating both bug fixing and feature development in an existing system.

---

## 🏁 Project Status
✅ Backend Bug Fixes  
✅ Spring Boot API Enhancements  
✅ Category and Product Controller Updates  
✅ JWT Authentication with Postman Testing  
✅ Shopping Cart + Checkout System (Optional)  
✅ Frontend Demo Website Running via `localhost:8080`

---

## 🔧 Technologies Used

<details>
  <summary>🌐 Backend</summary>

- Spring Boot
- Java
- RESTful API Design
- JWT (JSON Web Token) Auth
- MySQL (Schema via `create_database.sql`)
</details>

<details>
  <summary>🧪 Testing</summary>

- Postman (with token-based requests)
- Manual debugging
- Unit testing
</details>

<details>
  <summary>💻 Frontend</summary>

- HTML/CSS (provided template)
- Hosted locally via Spring Boot static assets
</details>

---

## 📚 Features Implemented

### ✅ Core Features
- Browse products by category
- Filter products by color, price range, and category
- User registration and secure login with JWT
- View and manage shopping cart
- View and update user profile
- Checkout flow (convert cart to order)

### 🐞 Bug Fixes
- Fixed incorrect product filtering logic
- Resolved product duplication on update

### 🛒 Shopping Cart (Optional)
- Persistent user cart via database
- Add, update, remove items
- Auto-load cart after login

### 📦 Checkout (Optional)
- OrdersController created
- Transfers cart to order, clears cart

---

## 📸 Screenshots

- ✅ [Capstone 3 - Running the Demo Website](./Capstone%203%20-%20Running%20the%20Demo%20Website.pdf)
- 🧪 [Capstone 3 - Testing with Postman](./Capstone%203%20-%20Testing%20with%20Postman.pdf)

---

## 🧠 Interesting Code Snippet

```java
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
    User user = authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
    if (user != null) {
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(user, token));
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
}
