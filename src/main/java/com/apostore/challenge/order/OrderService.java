package com.apostore.challenge.order;

import com.apostore.challenge.item.Item;
import com.apostore.challenge.item.ItemRepository;
import com.apostore.challenge.position.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }

    public List<Order> list() {
        return orderRepository.findAll();
    }

    public Optional<Order> get(UUID id) {
        return orderRepository.findById(id);
    }

    public Order create(Order order) {
        return orderRepository.save(order);
    }

    public Order update(UUID id, Order order) {
        order.setUpdated(new Date());
        return orderRepository.save(order);
    }

    public Order request(UUID id, Order order) {
        order.setUpdated(new Date());
        order = orderRepository.save(order);
        HashMap<UUID, Integer> amounts = new HashMap<>();
        order.getPositions().forEach((Position position) -> amounts.merge(position.getItem().getId(), position.getAmount(), Integer::sum));

        List<Item> items = itemRepository.findAllById(order.getPositions().stream().map(position -> position.getItem().getId()).toList());
        items.forEach((Item item) -> {
            Integer amount = amounts.get(item.getId());
            if (item.getStock() < amount)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, item.getName() + ": " + amount + " / " + item.getStock());
            else {
                item.setUpdated(new Date());
                item.setStock(item.getStock() - amount);
            }
        });
        itemRepository.saveAll(items);
        return order;
    }

    public void delete(UUID id) {
        orderRepository.deleteById(id);
    }
}
