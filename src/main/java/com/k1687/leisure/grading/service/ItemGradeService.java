package com.k1687.leisure.grading.service;

import com.k1687.leisure.grading.model.Item;
import com.k1687.leisure.grading.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemGradeService {

    Logger logger = LoggerFactory.getLogger(ItemGradeService.class);

    @Autowired
    private ItemRepository itemRepository;

    public Optional<Item> findById(Item item){
        return itemRepository.findById(item.getId());
    }

    public List<Item> findItemByCategory(Long category_id){
        List<Item> items = itemRepository.findItemsByCategory(category_id);
        return items;
    }

    public Item saveGradedItem(Item gradedItem){
        return itemRepository.save(gradedItem);
    }

    public boolean deleteGradedItem(Long id) {
        Item item = new Item();
        item.setId(id);
        Optional<Item> loadedItem = this.findById(item);
        if(loadedItem.isPresent()){
            itemRepository.delete(loadedItem.get());
            return true;
        }
        return false;
    }
}
