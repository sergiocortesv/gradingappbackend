package com.k1687.leisure.grading.controller;

import com.k1687.leisure.grading.configuration.APICreate;
import com.k1687.leisure.grading.configuration.ItemCreate;
import com.k1687.leisure.grading.dto.ErrorResponse;
import com.k1687.leisure.grading.dto.Item;
import com.k1687.leisure.grading.mapper.ItemMapper;
import com.k1687.leisure.grading.service.ItemGradeService;
import org.apache.coyote.Response;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/item")
public class ItemController {

    @Autowired
    private ItemGradeService itemGradeService;

    private ItemMapper itemMapper = Mappers.getMapper(ItemMapper.class);

    @PostMapping(consumes = {"application/json"})
    public ResponseEntity<Item> saveGradedItem(@Validated(value = ItemCreate.class) @RequestBody Item item){

        com.k1687.leisure.grading.model.Item savedItem = itemGradeService.saveGradedItem(itemMapper.sourceToDest(item));
        Item savedItemDto = itemMapper.destToSource(savedItem);
        ResponseEntity<Item> response = new ResponseEntity<>(savedItemDto, HttpStatus.OK);
        return response;
    }

    @DeleteMapping
    @RequestMapping("/{id}")
    public ResponseEntity deleteGradedItem(@PathVariable("id") Long id){
        boolean outcome = itemGradeService.deleteGradedItem(id);
        HttpStatus status = outcome ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        ResponseEntity response = new ResponseEntity(status);
        return response;
    }

    // Using global handlerExceptionResolver handling instead of controller-level
    // @ExceptionHandler(value = MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleLocalExceptions(MethodArgumentNotValidException ex){
        final List<String> errors = new ArrayList<String>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        final ErrorResponse apiError = new ErrorResponse();
        apiError.setError(HttpStatus.BAD_REQUEST.name());
        apiError.setStatus(HttpStatus.BAD_REQUEST.value());
        apiError.setMessage("Validation Error");
        apiError.setDetailedMessage(errors.stream().collect(Collectors.joining(",")));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

}
