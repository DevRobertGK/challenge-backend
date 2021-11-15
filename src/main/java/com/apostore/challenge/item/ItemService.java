package com.apostore.challenge.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> list() {
        return itemRepository.findAll();
    }

    public Optional<Item> get(UUID id) {
        return itemRepository.findById(id);
    }

    public Item create(Item item) {
        return itemRepository.save(item);
    }

    public Item update(UUID id, Item item) {
        item.setUpdated(new Date());
        return itemRepository.save(item);
    }

    public void delete(UUID id) {
        itemRepository.deleteById(id);
    }
}
