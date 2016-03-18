package com.wtw.repository;

import com.wtw.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long>, CustomOrderRepository {
    Order findOrderById(Long id);

    @Transactional
    Long deleteOrderById(Long id);
}
