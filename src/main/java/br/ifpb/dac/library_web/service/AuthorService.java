package br.ifpb.dac.library_web.service;

import br.ifpb.dac.library_web.entity.Author;
import br.ifpb.dac.library_web.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    // Salvar um novo autor
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    // Obter um autor por ID
    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    // Listar todos os autores
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    // Atualizar um autor existente
    public Author updateAuthor(Long id, Author author) {
        if (authorRepository.existsById(id)) {
            author.setId(id);
            return authorRepository.save(author);
        }
        return null;  // Ou lance uma exceção caso o autor não seja encontrado
    }

    // Deletar um autor por ID
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}

