package br.ifpb.dac.library_web.service;

import br.ifpb.dac.library_web.dto.PublisherRequest;
import br.ifpb.dac.library_web.entity.Publisher;
import br.ifpb.dac.library_web.entity.Survey;
import br.ifpb.dac.library_web.exception.SurveyNotFoundException;
import br.ifpb.dac.library_web.exception.infra.MessageKeyEnum;
import br.ifpb.dac.library_web.mapper.PublisherMapper;
import br.ifpb.dac.library_web.repository.PublisherRepository;
import br.ifpb.dac.library_web.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherService {

    private final PublisherRepository publisherRepository;
    private final SurveyRepository surveyRepository;

    public Publisher save(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    public List<Publisher> findAllPublishers() {
        return publisherRepository.findAll();
    }

    public Publisher findById(Long id) {
        return publisherRepository.findById(id).
                orElseThrow(()-> new SurveyNotFoundException(MessageKeyEnum.PUBLISHER_WITH_ID_NOT_FOUND.getMessage(id)));
    }


    public Publisher update(Long id, PublisherRequest request) {
        Publisher existingPublisher = publisherRepository.findById(id)
                .orElseThrow(() -> new SurveyNotFoundException(MessageKeyEnum.PUBLISHER_WITH_ID_NOT_FOUND.getMessage(id)));
        existingPublisher.setName(request.getName());
        return publisherRepository.save(existingPublisher);
    }

    public void deletePublisherById(Long id) {
        if(!publisherRepository.existsById(id)) {
            throw  new SurveyNotFoundException(MessageKeyEnum.PUBLISHER_WITH_ID_NOT_FOUND.getMessage(id));
        }
        publisherRepository.deleteById(id);
    }

}
