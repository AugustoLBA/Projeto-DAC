package br.ifpb.dac.library_web.mapper;

import br.ifpb.dac.library_web.dto.PublisherRequest;
import br.ifpb.dac.library_web.dto.PublisherResponse;
import br.ifpb.dac.library_web.entity.Book;
import br.ifpb.dac.library_web.entity.Publisher;

import java.util.List;
import java.util.stream.Collectors;

public class PublisherMapper {
    // Converte PublisherRequest para Publisher
    public static Publisher toPublisher(PublisherRequest request) {
        Publisher publisher = new Publisher();
        publisher.setName(request.getName());
        return publisher;
    }

    // Converte Publisher para PublisherResponse
    public static PublisherResponse toResponse(Publisher publisher) {
        List<Long> bookIds = publisher.getBooks() != null
                ? publisher.getBooks().stream()
                .map(Book::getId)
                .collect(Collectors.toList())
                : List.of(); // Retorna uma lista vazia se não houver livros

        return new PublisherResponse(
                publisher.getId(),
                publisher.getName(),
                bookIds
        );
    }

    // Converte uma lista de Publisher para uma lista de PublisherResponse
    public static List<PublisherResponse> toResponseList(List<Publisher> publishers) {
        return publishers.stream()
                .map(PublisherMapper::toResponse)
                .collect(Collectors.toList());
    }
}
