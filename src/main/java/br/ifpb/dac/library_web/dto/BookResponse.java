package br.ifpb.dac.library_web.dto;

import br.ifpb.dac.library_web.entity.Author;
import br.ifpb.dac.library_web.entity.Book;
import br.ifpb.dac.library_web.entity.Exemplary;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {

    private Long id;
    private String title;
    private String isbn;
    private Integer yearPublication;
    private Integer numberPages;
    private String publisherName;
    private List<Exemplary> numberCopies;
    private Map<String,Integer> chapters;
    private List<Author> authorNames;
    private String gender;


    public BookResponse(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.isbn = book.getIsbn();
        this.yearPublication = book.getYearPublication();
        this.numberPages = book.getNumberPages();
        this.publisherName = book.getPublisher().getName();
        this.numberCopies = book.getNumberCopies();
        this.chapters = book.getChapters();
        this.authorNames = book.getAuthors();
        this.gender = book.getGender();

    }
}
