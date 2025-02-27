package br.ifpb.dac.library_web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {

    @NotBlank(message = "The isbn field cannot be empty")
    private String isbn;

    @NotBlank(message = "The title field cannot be empty")
    private String title;

    @NotNull(message = "The year publication field cannot be empty")
    private Integer yearPublication;

    @NotNull(message = "The field number pages cannot be empty")
    private Integer numberPages;

    @NotNull(message = "The PublisherName cannot be empty")
    private Long publisherId;

    @NotNull
    @Min(value = 1, message = "There must be at least one author")
    private Integer numberCopies;

    @NotNull
    @Size(min = 1, message = "There must be at least one author")
    private List<Long> authorIds;

    @NotNull(message = "Chapters cannot be null")
    private Map<String,Integer> chapters;

    @NotBlank(message = "The book must have a genre")
    private String gender;
}
