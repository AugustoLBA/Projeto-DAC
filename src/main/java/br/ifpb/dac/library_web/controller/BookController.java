package br.ifpb.dac.library_web.controller;


import br.ifpb.dac.library_web.dto.BookRequest;
import br.ifpb.dac.library_web.dto.BookResponse;
import br.ifpb.dac.library_web.entity.Book;
import br.ifpb.dac.library_web.mapper.BookMapper;
import br.ifpb.dac.library_web.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/book")
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponse> save(@Valid @RequestBody BookRequest book) {
        return ResponseEntity.status(HttpStatus.CREATED).
                body(BookMapper.toBookResponse(bookService.save(BookMapper.toBook(book),book.getAuthorIds())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> findBookById(@PathVariable Long id) {
        BookResponse bookResponse = BookMapper.toBookResponse(bookService.findById(id));
        return ResponseEntity.status(HttpStatus.OK).body(bookResponse);
    }
    @GetMapping("/title")
    public ResponseEntity<BookResponse> findBookByTitle(@RequestParam String title) {
        BookResponse bookResponse = BookMapper.toBookResponse(bookService.findByTitle(title));
        return ResponseEntity.status(HttpStatus.OK).body(bookResponse);
    }

    @GetMapping("/authors")
    public ResponseEntity<List<BookResponse>> findAllBookByAuthor(@RequestParam String authors) {
        return ResponseEntity.status(HttpStatus.OK).body(BookMapper.toListBookResponse(bookService.findBooksByAuthors(authors)));
    }


    @GetMapping
    public ResponseEntity<List<BookResponse>> findAllBookBy() {
        return ResponseEntity.status(HttpStatus.OK).body(BookMapper.toListBookResponse(bookService.findAllBooks()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable Long id) {
        bookService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
