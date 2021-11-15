package com.apostore.challenge.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> list() {
        return orderService.list();
    }

    @GetMapping(value = "/{id}")
    public Optional<Order> get(@PathVariable("id") UUID id) {
        return orderService.get(id);
    }

    @PostMapping
    public Order create(@RequestBody Order order) {
        return orderService.create(order);
    }

    @PutMapping(value = "/{id}")
    public Order update(@PathVariable("id") UUID id, @RequestBody Order order) {
        return orderService.update(id, order);
    }

    @PutMapping(value = "/{id}/request")
    public Order request(@PathVariable("id") UUID id, @RequestBody Order order) {
        try {
            return orderService.request(id, order);
        } catch (ResponseStatusException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unzureichende Lagermenge - " + ex.getReason());
        }
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") UUID id) {
        orderService.delete(id);
    }
}
