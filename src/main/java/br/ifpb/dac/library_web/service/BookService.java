package br.ifpb.dac.library_web.service;
import br.ifpb.dac.library_web.entity.Author;
import br.ifpb.dac.library_web.entity.Book;
import br.ifpb.dac.library_web.exception.ResourceNotFoundException;
import br.ifpb.dac.library_web.exception.infra.MessageKeyEnum;
import br.ifpb.dac.library_web.repository.AuthorRepository;
import br.ifpb.dac.library_web.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final AuthorRepository authorRepository;

    public Book save(Book book, List <Long> authorIds ) {

        if (authorIds != null) {
            if (authorIds.stream().anyMatch(Objects::isNull)) {
                throw new ResourceNotFoundException("Author IDs list cannot contain null values.");
            }
        }
        List<Author> authors = new ArrayList<>();
        for (Long id : authorIds) {
            authors.add(authorService.getByid(id));
        }
        book.setAuthors(authors);
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

}
