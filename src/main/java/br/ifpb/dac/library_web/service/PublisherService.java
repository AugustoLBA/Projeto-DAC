package br.ifpb.dac.library_web.service;

import br.ifpb.dac.library_web.entity.Publisher;
import br.ifpb.dac.library_web.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    // Salvar uma nova editora
    public Publisher savePublisher(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    // Obter uma editora por ID
    public Optional<Publisher> getPublisherById(Long id) {
        return publisherRepository.findById(id);
    }

    // Listar todas as editoras
    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    // Atualizar uma editora existente
    public Publisher updatePublisher(Long id, Publisher publisher) {
        if (publisherRepository.existsById(id)) {
            publisher.setId(id);
            return publisherRepository.save(publisher);
        }
        return null;  // Ou lance uma exceção, caso necessário
    }

    // Deletar uma editora por ID
    public void deletePublisher(Long id) {
        publisherRepository.deleteById(id);
    }
}
