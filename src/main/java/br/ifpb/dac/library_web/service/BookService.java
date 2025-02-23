package br.ifpb.dac.library_web.service;
import br.ifpb.dac.library_web.dto.BookUpdateRequest;
import br.ifpb.dac.library_web.entity.Author;
import br.ifpb.dac.library_web.entity.Book;
import br.ifpb.dac.library_web.entity.Exemplary;
import br.ifpb.dac.library_web.entity.Publisher;
import br.ifpb.dac.library_web.exception.ResourceNotFoundException;
import br.ifpb.dac.library_web.exception.infra.MessageKeyEnum;
import br.ifpb.dac.library_web.repository.AuthorRepository;
import br.ifpb.dac.library_web.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final PublisherService publisherService;
    @Lazy
    private  ExemplaryService exemplaryService;

    public Book save(Book book, List <Long> authorIds,Long publisherId, int numberOfCopies) {

        if (authorIds != null) {
            if (authorIds.stream().anyMatch(Objects::isNull)) {
                throw new ResourceNotFoundException("Author IDs list cannot contain null values.");
            }
        }
        List<Author> authors = new ArrayList<>();
        for (Long id : authorIds) {
            authors.add(authorService.getByid(id));
        }
        Publisher publisher = publisherService.findById(publisherId);  // Método para buscar editor por ID


        List<Exemplary> copies = new ArrayList<>();
        for (int i = 0; i < numberOfCopies; i++) {
            Exemplary exemplary = new Exemplary();
//            exemplary.setNumberExemplary(exemplary.getNumberExemplary()+1);
            exemplary.setBook(book);
            copies.add(exemplary);
        }

        book.setPublisher(publisher);
        book.setNumberCopies(copies);
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
    @Transactional
    public Book updateBook(Long id, BookUpdateRequest bookUpdateRequest) {
        Book book = findById(id);

        //Logica dos autores caso adicione ou remova
        List<Author> authors = new ArrayList<>();
        bookUpdateRequest.getAuthorIds().forEach( author -> authors.add(authorService.getByid(author)));
        book.getAuthors().removeIf(existingAuthor -> !bookUpdateRequest.getAuthorIds().contains(existingAuthor.getId()));
        book.setAuthors(authors);

        book.setTitle(bookUpdateRequest.getTitle());
        book.setYearPublication(bookUpdateRequest.getYearPublication());
        book.setPublisher(publisherService.findById(bookUpdateRequest.getPublisherId()));
        book.setGender(bookUpdateRequest.getGender());
        book.setNumberPages(bookUpdateRequest.getNumberPages());

        //Logica dos capitulos caso ele remova ou adicione
        book.setChapters(bookUpdateRequest.getChapters());

        // Logica dos exemplares caso adicione ou remova
        int currentCopies = book.getNumberCopies().size();
        int requestedCopies = bookUpdateRequest.getNumberCopies();

        if (requestedCopies > currentCopies) {
            // Adicionando exemplares
            int newCopies = requestedCopies - currentCopies;
            for (int i = 0; i < newCopies; i++) {
                Exemplary exemplary = Exemplary.builder().book(book).build();
                book.getNumberCopies().add(exemplary);
            }
        } else if (requestedCopies < currentCopies) {
            // Removendo exemplares
            int copiesToRemove = currentCopies - requestedCopies;
            for (int i = 0; i < copiesToRemove; i++) {
                Exemplary exemplary = book.getNumberCopies().remove(0);
                exemplaryService.deleteExemplaryById(exemplary.getId());
            }
        }
        return book;
    }
}
