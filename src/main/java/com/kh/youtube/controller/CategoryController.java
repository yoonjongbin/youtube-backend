package com.kh.youtube.controller;


import com.kh.youtube.domain.Category;
import com.kh.youtube.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/*")
public class CategoryController {
    @Autowired
    private CategoryService service;

    @GetMapping("/category")
    public ResponseEntity<List<Category>> showAll(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.showAll());
        } catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // or build()
        }
    }

    @GetMapping("/category/{categoryCode}")
    public ResponseEntity<Category> show(@PathVariable int categoryCode){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.show(categoryCode));
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/category")
    public ResponseEntity<Category> create(@RequestBody Category category){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.create(category));
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/category")
    public ResponseEntity<Category> update(@RequestBody Category category){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.update(category));
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/category/{categoryCode}")
    public ResponseEntity<Category> delete(@PathVariable int categoryCode){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.delete(categoryCode));
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
