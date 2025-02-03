package br.ifpb.dac.library_web.controller;

import br.ifpb.dac.library_web.dto.PublisherRequest;
import br.ifpb.dac.library_web.dto.PublisherResponse;
import br.ifpb.dac.library_web.entity.Publisher;
import br.ifpb.dac.library_web.mapper.PublisherMapper;
import br.ifpb.dac.library_web.service.PublisherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/publisher")
public class PublisherController {

    private final PublisherService publisherService;

    @PostMapping
    public ResponseEntity<PublisherResponse> savePublisher(@Valid @RequestBody PublisherRequest publisherRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).
                body(PublisherMapper.toResponse(publisherService.save(PublisherMapper.toPublisher(publisherRequest))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherResponse> findPublisherById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).
                body(PublisherMapper.toResponse(publisherService.findById(id)));
    }

    @GetMapping
    public ResponseEntity<List<PublisherResponse>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).
                body(PublisherMapper.toResponseList(publisherService.findAllPublishers()));
    }
    @PutMapping("/{id}")
    public ResponseEntity<PublisherResponse> update(@PathVariable Long id,@Valid @RequestBody PublisherRequest publisherRequest) {
        return ResponseEntity.ok(PublisherMapper.toResponse(publisherService.update(id, publisherRequest)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisherById(@PathVariable Long id) {
        publisherService.deletePublisherById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
