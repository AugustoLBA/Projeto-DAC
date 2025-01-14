package br.ifpb.dac.library_web.service;

import br.ifpb.dac.library_web.entity.Author;
import br.ifpb.dac.library_web.exception.ResourceNotFoundException;
import br.ifpb.dac.library_web.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public Author getByid(Long id){
        return authorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Author com id nao encontrado"));
    }
}
