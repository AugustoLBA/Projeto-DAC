package br.ifpb.dac.library_web.controller;

import br.ifpb.dac.library_web.dto.ExemplaryRequest;
import br.ifpb.dac.library_web.dto.ExemplaryResponse;
import br.ifpb.dac.library_web.entity.Book;
import br.ifpb.dac.library_web.entity.Exemplary;
import br.ifpb.dac.library_web.mapper.ExemplaryMapper;
import br.ifpb.dac.library_web.service.ExemplaryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/exemplary")
public class ExemplaryController {

    private final ExemplaryService exemplaryService;


    @PostMapping
    public ResponseEntity<ExemplaryResponse> saveExemplary(@Valid @RequestBody ExemplaryRequest exemplary){
        return ResponseEntity.status(HttpStatus.CREATED).
                body(ExemplaryMapper.toExemplaryResponse(exemplaryService.save(exemplary)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExemplaryResponse> findExemplaryById(@PathVariable Long id){
        ExemplaryResponse response = ExemplaryMapper.toExemplaryResponse(exemplaryService.findExemplaryById(id));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ExemplaryResponse>> findAllExemplaries() {
        return ResponseEntity.status(HttpStatus.OK).
                body(ExemplaryMapper.toListExemplaryResponse(exemplaryService.findAllExemplaries()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExemplaryById(@PathVariable Long id) {
        exemplaryService.deleteExemplaryById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
