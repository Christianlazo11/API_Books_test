package com.api.library.controller;


import com.api.library.dto.BookDTO;
import com.api.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDTO>> findAll() {
        List<BookDTO> bookList = bookService.findAll();
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> findById(@PathVariable(name = "id") Long id) {
        BookDTO bookFind  = bookService.findById(id);
        return new ResponseEntity<>(bookFind, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookDTO> save(@RequestBody BookDTO bookDTO) {
        BookDTO bookSave = bookService.save(bookDTO);
        return new ResponseEntity<>(bookSave, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> update(@PathVariable(name = "id") Long id, @RequestBody BookDTO bookDTO) {
        BookDTO bookUpdate = bookService.update(bookDTO, id);
        return new ResponseEntity<>(bookUpdate, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") Long id) {
        bookService.delete(id);
        return new ResponseEntity<>("Libro eliminado con éxito!", HttpStatus.OK);
    }
}
