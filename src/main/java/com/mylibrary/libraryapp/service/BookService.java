package com.mylibrary.libraryapp.service;

import com.mylibrary.libraryapp.dto.BookDTO;

import java.util.List;

public interface BookService {

    BookDTO getBookById(Long bookId);

    List<BookDTO> getAllBooks();

    List<BookDTO> findBooksByTitle(String title);

    List<BookDTO> findBooksByCriteria(String title, String author, String barcodeNumber, String isbn);

    BookDTO addBook(BookDTO bookDTO);

    BookDTO updateBook(BookDTO bookDTO);

    void deleteBook(Long Id);
}
