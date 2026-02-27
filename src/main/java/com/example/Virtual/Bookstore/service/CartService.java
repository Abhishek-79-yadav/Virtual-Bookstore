package com.example.Virtual.Bookstore.service;

import com.example.Virtual.Bookstore.entity.Book;
import com.example.Virtual.Bookstore.entity.CartItem;
import com.example.Virtual.Bookstore.entity.User;
import com.example.Virtual.Bookstore.repository.BookRepository;
import com.example.Virtual.Bookstore.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final BookRepository bookRepository;

    public void addToCart(User user, Long bookId, int quantity) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        List<CartItem> existing = cartRepository.findByUserId(user.getId());
        for (CartItem item : existing) {
            if (item.getBook().getId().equals(bookId)) {
                item.setQuantity(item.getQuantity() + quantity);
                cartRepository.save(item);
                return;
            }
        }

        CartItem cartItem = new CartItem();
        cartItem.setUser(user);
        cartItem.setBook(book);
        cartItem.setQuantity(quantity);
        cartRepository.save(cartItem);
    }

    public List<CartItem> getUserCart(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    public void clearCart(Long userId) {
        cartRepository.deleteByUserId(userId);
    }
}