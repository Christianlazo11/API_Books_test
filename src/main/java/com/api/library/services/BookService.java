package com.api.library.services;

import com.api.library.dto.BookDTO;

import java.util.List;

public interface BookService {

    List<BookDTO> findAll();

    BookDTO findById(Long id);

    BookDTO save(BookDTO bookDTO);

    BookDTO update(BookDTO bookDTO, Long id);

    void delete(Long id);

}
