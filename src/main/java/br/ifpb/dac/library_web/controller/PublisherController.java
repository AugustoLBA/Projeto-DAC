package br.ifpb.dac.library_web.controller;

import br.ifpb.dac.library_web.entity.Publisher;
import br.ifpb.dac.library_web.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/publishers")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;

    @PostMapping("/save")
    public void save(Publisher publisher){
        publisher.setName("Editora B");
        publisherService.savePublisher(publisher);
    }
}
