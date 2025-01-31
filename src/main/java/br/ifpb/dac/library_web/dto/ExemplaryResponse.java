package br.ifpb.dac.library_web.dto;

import br.ifpb.dac.library_web.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExemplaryResponse {

    private Long id;
    private Integer numberExemplary;
    private BookResponse book;


}
