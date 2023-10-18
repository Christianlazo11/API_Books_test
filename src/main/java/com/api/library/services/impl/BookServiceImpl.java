package com.api.library.services.impl;

import com.api.library.dto.BookDTO;
import com.api.library.entities.Book;
import com.api.library.exceptions.ResourceNotFoundException;
import com.api.library.repository.BookRepository;
import com.api.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<BookDTO> findAll() {
        List<BookDTO> listBooks = bookRepository.findAll().stream().map(
                (book) -> mapBookDTO(book)
        ).toList();


        return listBooks;
    }

    @Override
    public BookDTO findById(Long id) {
        Book bookFind = bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Book", "id", id)
        );
        return mapBookDTO(bookFind);
    }

    @Override
    public BookDTO save(BookDTO bookDTO) {
        Book bookSave = bookRepository.save(mapBook(bookDTO));
        return mapBookDTO(bookSave);
    }

    @Override
    public BookDTO update(BookDTO bookDTO, Long id) {
        Book bookFind = bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Book", "id", id)
        );

        bookFind.setTitle(bookDTO.getTitle());
        bookFind.setAuthor(bookDTO.getAuthor());
        bookFind.setGender(bookDTO.getGender());
        bookFind.setYearOfPublication(bookDTO.getYearOfPublication());

        Book bookSave = bookRepository.save(bookFind);

        return mapBookDTO(bookSave);
    }

    @Override
    public void delete(Long id) {
        Book bookFind = bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Book", "id", id)
        );

        bookRepository.delete(bookFind);
    }

    private BookDTO mapBookDTO(Book book) {
        BookDTO bookDTO = BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .gender(book.getGender())
                .yearOfPublication(book.getYearOfPublication())
                .build();

        return bookDTO;
    }

    private Book mapBook(BookDTO bookDTO) {
        Book book = Book.builder()
                .id(bookDTO.getId())
                .title(bookDTO.getTitle())
                .author(bookDTO.getAuthor())
                .gender(bookDTO.getGender())
                .yearOfPublication(bookDTO.getYearOfPublication())
                .build();

        return book;
    }
}
