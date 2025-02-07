package br.ifpb.dac.library_web.mapper;

import br.ifpb.dac.library_web.dto.BookRequest;
import br.ifpb.dac.library_web.dto.BookResponse;
import br.ifpb.dac.library_web.entity.Author;
import br.ifpb.dac.library_web.entity.Book;
import br.ifpb.dac.library_web.entity.Publisher;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hibernate.Hibernate.map;

public class BookMapper {
    public static Book toBook(BookRequest request) {
        ModelMapper mapper = new ModelMapper();
        Book book = mapper.map(request, Book.class);

        if (request.getPublisherName() != null) {
            Publisher publisher = new Publisher();
            publisher.setName(request.getPublisherName());
            book.setPublisher(publisher);
        }

        if (request.getAuthorNames() != null) {
            List<Author> authors = request.getAuthorNames().stream()
                    .map(name -> {
                        Author author = new Author();
                        author.setName(name);
                        return author;
                    })
                    .collect(Collectors.toList());
            book.setAuthors(authors);
        }

        return book;
    }

    public static BookResponse toBookResponse(Book book) {
        PropertyMap<Book, BookResponse> props = new PropertyMap<Book, BookResponse>() {
            @Override
            protected void configure() {
                map().setPublisherName(source.getPublisher() != null ? source.getPublisher().getName() : null);
                map().setAuthorNames(source.getAuthors().stream().map(Author::getName).toList());
            }
        };
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(props);
        return mapper.map(book, BookResponse.class);
    }

    public static List<BookResponse> toListBookResponse(List<Book> books) {
        return books.stream().map(BookMapper::toBookResponse).collect(Collectors.toList());
    }
}
