package br.ifpb.dac.library_web.service;

import br.ifpb.dac.library_web.dto.AuthorRequest;
import br.ifpb.dac.library_web.entity.Author;
import br.ifpb.dac.library_web.entity.Book;
import br.ifpb.dac.library_web.exception.ResourceNotFoundException;
import br.ifpb.dac.library_web.exception.infra.MessageKeyEnum;
import br.ifpb.dac.library_web.repository.AuthorRepository;
import br.ifpb.dac.library_web.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {


    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    @Autowired
    @Lazy
    private BookService bookService;


    public Author getByid(Long id){
        return authorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Author com id nao encontrado"));
    }
    public Author save(Author author){
        return authorRepository.save(author);
    }
    public List<Author> findAllAuthor() {
        return authorRepository.findAll();
    }
    @Transactional
    public void delete(Long id) {

        Author autor = getByid(id);
        List<Book> books = bookRepository.findBooksByAuthors(autor.getName());
        if(books != null && !books.isEmpty()){
            books.forEach(book -> {
                if(book.getAuthors().contains(autor)){
                    if(book.getAuthors().size() == 1){
                        book.getAuthors().remove(autor);
                        bookService.deleteById(book.getId());
                    }else{
                        book.getAuthors().remove(autor);
                    }
                }
            });
        }
        authorRepository.deleteById(id);
    }
    public Author update(Long id, AuthorRequest authorRequest) {
        Author existsAuthor = this.getByid(id);
        existsAuthor.setName(authorRequest.getName());
        return authorRepository.save(existsAuthor);
    }

}
