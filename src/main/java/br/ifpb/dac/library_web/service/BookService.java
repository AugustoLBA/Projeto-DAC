package br.ifpb.dac.library_web.service;

import br.ifpb.dac.library_web.entity.Book;
import br.ifpb.dac.library_web.exception.ResourceNotFoundException;
import br.ifpb.dac.library_web.exception.infra.MessageKeyEnum;
import br.ifpb.dac.library_web.mapper.BookMapper;
import br.ifpb.dac.library_web.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;



@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MessageKeyEnum.BOOK_NOT_FOUND_WITH_ID.getMessage(id)));
    }

    public List<Book> findAllBooks(){
        return bookRepository.findAll();
    }

    public void deleteById(Long id) {
        if(!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException(MessageKeyEnum.BOOK_NOT_FOUND_WITH_ID.getMessage(id));
        }
        bookRepository.deleteById(id);
    }

    public Book findByTitle(String title) {
        return bookRepository.findByTitle(title)
                .orElseThrow(()->new ResourceNotFoundException(MessageKeyEnum.BOOK_NOT_FOUND_WITH_TITLE.getMessage(title)));
    }

    public List<Book> findBooksByAuthors(String authorName){
        return bookRepository.findBooksByAuthors(authorName);
    }

    // Método para listar todos os livros com paginação
    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    // Método para buscar livros por título com paginação
    public Page<Book> searchBooksByTitle(String title, Pageable pageable) {
        return bookRepository.findByTitleContainingIgnoreCase(title, pageable);
    }
}
