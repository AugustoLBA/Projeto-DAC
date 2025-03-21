package br.ifpb.dac.library_web.controller;


import br.ifpb.dac.library_web.dto.BookRequest;
import br.ifpb.dac.library_web.dto.BookResponse;
import br.ifpb.dac.library_web.dto.BookUpdateRequest;
import br.ifpb.dac.library_web.mapper.BookMapper;
import br.ifpb.dac.library_web.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/book")
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponse> save(@Valid @RequestBody BookRequest book) {
        return ResponseEntity.status(HttpStatus.CREATED).
                body(BookMapper.toBookResponse(bookService.save(BookMapper.toBook(book),book.getAuthorIds(), book.getPublisherId(), book.getNumberCopies())));
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
    @PreAuthorize("hasAnyRole('ADMIN') OR hasAnyRole('USER')")
    public ResponseEntity<List<BookResponse>> findAllBookBy() {
        return ResponseEntity.status(HttpStatus.OK).body(BookMapper.toListBookResponse(bookService.findAllBooks()));
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable("id") Long id) {
        bookService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable("id") Long id,@Valid @RequestBody BookUpdateRequest bookUpdateRequest){
        return ResponseEntity.status(HttpStatus.OK).body(BookMapper.toBookResponse(bookService.updateBook(id, bookUpdateRequest)));
    }

}
