package br.ifpb.dac.library_web.service;

import br.ifpb.dac.library_web.dto.AuthorRequest;
import br.ifpb.dac.library_web.entity.Author;
import br.ifpb.dac.library_web.exception.ResourceNotFoundException;
import br.ifpb.dac.library_web.exception.infra.MessageKeyEnum;
import br.ifpb.dac.library_web.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;


    public Author getByid(Long id){
        return authorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Author com id nao encontrado"));
    }
    public Author save(Author author){
        return authorRepository.save(author);
    }
    public List<Author> findAllAuthor() {
        return authorRepository.findAll();
    }
    public void delete(Long id) {
        if(!authorRepository.existsById(id)){
            throw new ResourceNotFoundException(MessageKeyEnum.AUTHOR_WITH_ID_NOT_FOUND.getMessage(id));
        };
        authorRepository.deleteById(id);
    }
    public Author update(Long id, AuthorRequest authorRequest) {
        Author existsAuthor = this.getByid(id);
        existsAuthor.setName(authorRequest.getName());
        return authorRepository.save(existsAuthor);
    }

}
