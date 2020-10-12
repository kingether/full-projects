package com.bookstore.onlinebookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.onlinebookstore.model.ShoppingCart;
import com.bookstore.onlinebookstore.model.ShoppingCartId;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, ShoppingCartId>{
	public ShoppingCart findByUserIdAndBookId(Long userId, Long bookId);
	public List<ShoppingCart> findByUserIdOrderByDateCreatedAsc(Long userId);
	public List<ShoppingCart> findAllByUserId(Long userId);
}
