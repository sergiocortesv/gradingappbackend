package com.k1687.leisure.grading.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.k1687.leisure.grading.dto.Category;
import com.k1687.leisure.grading.dto.ErrorResponse;
import com.k1687.leisure.grading.dto.Grade;
import com.k1687.leisure.grading.dto.Item;
import com.k1687.leisure.grading.mapper.ItemMapper;
import com.k1687.leisure.grading.service.ItemGradeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = ItemController.class)
public class ItemControllerTest {

    @MockBean
    private ItemGradeService itemGradeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private Item item;

    @BeforeEach
    public void setup(){
        item = new Item();
        item.setName("Item Name");

        Grade grade = new Grade();
        grade.setId(1L);

        Category category = new Category();
        category.setId(1L);

        item.setGrade(grade);
        item.setCategory(category);
    }

    @Test
    public void saveGradedItem_success() throws Exception {
        String body = objectMapper.writeValueAsString(item);

        // TODO how to use the mapStruct programatically
        Mappers.getMapper(ItemMapper.class);
        com.k1687.leisure.grading.model.Item itemModel = new com.k1687.leisure.grading.model.Item();
        itemModel.setName(item.getName());
        when(itemGradeService.saveGradedItem(any())).thenReturn(itemModel);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/item")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(result);
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        Item responseItem = objectMapper.readValue(result.getResponse().getContentAsString(), Item.class);

        assertEquals("Item Name", responseItem.getName());
    }


    // https://stackoverflow.com/questions/72258605/best-way-to-write-validation-groups-in-spring-boot
    // https://www.baeldung.com/javax-validation-groups
    @Test
    public void saveGradedItem_invalid_null_name() throws Exception {
        item.setName(null);
        String body = objectMapper.writeValueAsString(item);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/item")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
        ErrorResponse errorResponse = objectMapper.readValue(result.getResponse().getContentAsString(),
                ErrorResponse.class);
        assertEquals("name: Value cannot be empty", errorResponse.getDetailedMessage());
    }

    @Test
    public void saveGradedItem_invalid_itemid() throws Exception{
        item.setId(10L);
        String body = objectMapper.writeValueAsString(item);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/item")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        ErrorResponse errorResponse = objectMapper.readValue(result.getResponse().getContentAsString(), ErrorResponse.class);

        assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
        assertEquals("id: Not required when creating", errorResponse.getDetailedMessage());
    }

    @Test
    public void deleteGradedItem_valid_itemid() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/item/1")
                .contentType(MediaType.APPLICATION_JSON);

        when(itemGradeService.deleteGradedItem(any())).thenReturn(true);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

    }

}
