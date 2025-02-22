package br.ifpb.dac.library_web.mapper;

import br.ifpb.dac.library_web.dto.BookRequest;
import br.ifpb.dac.library_web.dto.BookResponse;
import br.ifpb.dac.library_web.entity.Author;
import br.ifpb.dac.library_web.entity.Book;
import br.ifpb.dac.library_web.entity.Publisher;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class BookMapper {
    private static final ModelMapper mapper = new ModelMapper();

    public static Book toBook(BookRequest request) {
        if (request == null) {
            return null;
        }

        Book book = mapper.map(request, Book.class);

        // Mapeamento do Publisher
        if (request.getPublisherId() != null) {
            Publisher publisher = new Publisher();
            publisher.setId(request.getPublisherId());
            book.setPublisher(publisher);
        }

        // Mapeamento dos Chapters
        if (request.getChapters() != null) {
            book.setChapters(request.getChapters());
        }

        // Mapeamento dos Authors
        if (request.getAuthorIds() != null) {
            List<Author> authors = request.getAuthorIds().stream()
                    .map(id -> {
                        Author author = new Author();
                        author.setId(id); // Define apenas o ID do autor
                        return author;
                    })
                    .collect(Collectors.toList());
            book.setAuthors(authors);
        }

        return book;
    }

    public static BookResponse toBookResponse(Book book) {
        if (book == null) {
            return null;
        }

        BookResponse bookResponse = mapper.map(book, BookResponse.class);

        // Mapeamento manual do nome do Publisher
        if (book.getPublisher() != null) {
            bookResponse.setPublisherName(book.getPublisher().getName());
        }

        // Mapeamento manual dos nomes dos Authors
        if (book.getAuthors() != null) {
            bookResponse.setAuthorNames(book.getAuthors()); // Aqui passamos a lista de autores diretamente
        }

        return bookResponse;



    }

    public static List<BookResponse> toListBookResponse(List<Book> books) {
        return books.stream().map(book -> toBookResponse(book)).collect(Collectors.toList());

    }
}
