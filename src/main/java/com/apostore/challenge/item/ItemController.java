package com.apostore.challenge.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/items")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> list() {
        return itemService.list();
    }

    @GetMapping(value = "/{id}")
    public Optional<Item> get(@PathVariable("id") UUID id) {
        return itemService.get(id);
    }

    @PostMapping
    public Item create(@RequestBody Item item) {
        return itemService.create(item);
    }

    @PutMapping(value = "/{id}")
    public Item update(@PathVariable("id") UUID id, @RequestBody Item item) {
        return itemService.update(id, item);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") UUID id) {
        itemService.delete(id);
    }
}
