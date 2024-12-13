package br.ifpb.dac.library_web.dto;

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
    private Map<String,Integer> chapters;
    private List<String> authorNames;

}
