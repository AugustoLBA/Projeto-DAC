package br.ifpb.dac.library_web.mapper;

import br.ifpb.dac.library_web.dto.BookResponse;
import br.ifpb.dac.library_web.dto.ExemplaryRequest;
import br.ifpb.dac.library_web.dto.ExemplaryResponse;
import br.ifpb.dac.library_web.entity.Book;
import br.ifpb.dac.library_web.entity.Exemplary;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;


public class ExemplaryMapper {

    // Mapear de ExemplaryRequest para Exemplary (para criação)
    public static Exemplary toExemplary(ExemplaryRequest request, Book book) {
        ModelMapper mapper = new ModelMapper();
        Exemplary exemplary = mapper.map(request, Exemplary.class);
        exemplary.setBook(book); // Associa o Book ao Exemplary
        return exemplary;
    }
    public static ExemplaryResponse toExemplaryResponse(Exemplary exemplary) {
        ExemplaryResponse exemplaryResponse = new ExemplaryResponse();
//        exemplaryResponse.setNumberExemplary(exemplary.getNumberExemplary());
        exemplaryResponse.setId(exemplary.getId());
        exemplaryResponse.setBook(new BookResponse(exemplary.getBook()));
        return exemplaryResponse;
    }

    public static List<ExemplaryResponse> toListExemplaryResponse(List<Exemplary> exemplaries) {
        return exemplaries.stream().map(exemplary -> toExemplaryResponse(exemplary)).collect(Collectors.toList());

    }

}
