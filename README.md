# Virtual Bookstore Platform

A Spring Boot online bookstore with JWT authentication, shopping cart, orders, reviews, and Stripe payment.

## Features
- User registration & login (JWT)  
- Browse/search books by category/genre  
- Shopping cart: add/update/remove items  
- Orders: checkout, track, and view history  
- Reviews & ratings per book  
- Admin: manage books and view all orders  
- Stripe payment integration  

## Tech Stack
- **Backend:** Spring Boot 3.2  
- **Security:** Spring Security + JWT  
- **Database:** MySQL  
- **ORM:** Spring Data JPA / Hibernate  
- **Payment:** Stripe API  
- **Build:** Maven  

## Setup
1. **Database:**  
```sql
CREATE DATABASE bookstore_db;

spring.datasource.url=jdbc:mysql://localhost:3306/bookstore_db
spring.datasource.username=your_username
spring.datasource.password=your_password
jwt.secret=your-256-bit-secret
stripe.secret.key=sk_test_your_stripe_key

API Endpoints

Auth: /api/auth/register, /api/auth/login

Books (Public): /api/books, /api/books/{id}, search & category

Cart (User): /api/cart (add, update, remove)

Orders (User): /api/orders (checkout, list, get)

Payment (User): /api/payment/create-intent, /api/payment/confirm

Reviews (User): /api/reviews (CRUD)

Admin: manage books & view all orders

Security

JWT auth, role-based access, password hashing (BCrypt), data isolation

License

Educational purposes only


---

If you want, I can also make an **even shorter 10–15 line “super concise” version** suitable for GitHub with just **features, tech stack, and setup**, perfect for quick reference.  

Do you want me to do that?
