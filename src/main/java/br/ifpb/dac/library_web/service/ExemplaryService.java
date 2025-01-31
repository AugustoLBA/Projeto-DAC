package br.ifpb.dac.library_web.service;

import br.ifpb.dac.library_web.dto.ExemplaryRequest;
import br.ifpb.dac.library_web.entity.Book;
import br.ifpb.dac.library_web.entity.Exemplary;
import br.ifpb.dac.library_web.exception.ResourceNotFoundException;
import br.ifpb.dac.library_web.exception.infra.MessageKeyEnum;
import br.ifpb.dac.library_web.mapper.ExemplaryMapper;
import br.ifpb.dac.library_web.repository.ExemplaryRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class ExemplaryService {

    private final ExemplaryRepository exemplaryRepository;
    private final BookService bookService;

    public Exemplary save(@Valid ExemplaryRequest exemplaryRequest) {
        Book book = bookService.findById(exemplaryRequest.getBookId());
        Exemplary exemplary = ExemplaryMapper.toExemplary(exemplaryRequest,book);
        return exemplaryRepository.save(exemplary);
    }

    public List<Exemplary> findAllExemplaries() {
        return exemplaryRepository.findAll();
    }

    public Exemplary findExemplaryById(long id) {
        return exemplaryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MessageKeyEnum.EXEMPLARY_NOT_FOUND_WITH_ID.getMessage(id)));
    }

    public void deleteExemplaryById(long id) {
        if (!exemplaryRepository.existsById(id)) {
            throw new ResourceNotFoundException(MessageKeyEnum.EXEMPLARY_NOT_FOUND_WITH_ID.getMessage(id));
        }
        exemplaryRepository.deleteById(id);
    }
}
