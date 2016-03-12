package com.wtw.repository;


import com.wtw.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>, CustomCartRepository {
    Cart findCartById(Long id);

    @Transactional
    Long deleteCartById(Long id);
}
