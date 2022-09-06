package com.cg.service.orderitem;


import com.cg.model.OrderItem;
import com.cg.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItem> findAll() {
        return null;
    }

    @Override
    public Optional<OrderItem> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<OrderItem> findById(String id) {
        return Optional.empty();
    }

    @Override
    public OrderItem getById(Long id) {
        return null;
    }

    @Override
    public OrderItem getById(String id) {
        return null;
    }

    @Override
    public OrderItem save(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public void remove(String id) {

    }
}
