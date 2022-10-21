package com.k1687.leisure.grading.service;

import com.k1687.leisure.grading.model.Category;
import com.k1687.leisure.grading.model.Item;
import com.k1687.leisure.grading.repository.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    private ItemGradeService service;

    @BeforeEach
    public void setup(){
        service = new ItemGradeService();
        ReflectionTestUtils.setField(service, "itemRepository", itemRepository);
    }

    @Test
    public void saveGradedItem_duplicatedItem(){
        List<Item> duplicatedItemList = new ArrayList<>();
        duplicatedItemList.add(new Item());
        when(itemRepository.findItemsByNameAndCategory(any(), any())).thenReturn(duplicatedItemList);

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () ->{
            Item duplicatedItem = new Item();
            duplicatedItem.setName("Arms");
            Category cat = new Category();
            cat.setId(10L);
            duplicatedItem.setCategory(cat);
            service.saveGradedItem(duplicatedItem);
        });
    }

}
