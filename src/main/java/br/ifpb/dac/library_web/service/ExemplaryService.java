package br.ifpb.dac.library_web.service;

import br.ifpb.dac.library_web.entity.Book;
import br.ifpb.dac.library_web.entity.Exemplary;
import br.ifpb.dac.library_web.repository.ExemplaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExemplaryService {

    private final ExemplaryRepository exemplaryRepository;
    public List<Exemplary> saveAll(Book book, int copies){
        int aux = copies;
        List<Exemplary> list = new ArrayList<>();
        while (aux != 0){
            Exemplary exemplary = new Exemplary();
            exemplary.setBook(book);
            list.add(exemplary);
            aux--;
        }
        return list;
    }

    public List<Exemplary> findAllById(Long bookId){
        return exemplaryRepository.findAllByBookId(bookId);
    }

    public void deleteAll(Long bookId, int quantity){
        List<Exemplary> list = findAllById(bookId);

        int aux = 0;

        while (aux <= quantity){
            exemplaryRepository.deleteById(list.get(aux++).getId());
        }
    }
}
