package br.ifpb.dac.library_web.controller;

import br.ifpb.dac.library_web.dto.ExemplaryRequest;
import br.ifpb.dac.library_web.dto.ExemplaryResponse;
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


@RequiredArgsConstructor
@Controller
@RequestMapping("/exemplary")
public class ExemplaryController {

    private final ExemplaryService exemplaryService;


    @PostMapping                                    //verificar stava dando erro usando o ExeplaryRequest
    public ResponseEntity<ExemplaryResponse> saveExemplary(@Valid @RequestBody Exemplary exemplary){
        return ResponseEntity.status(HttpStatus.CREATED).
                body(ExemplaryMapper.toExemplary(exemplaryService.save(exemplary));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExemplaryResponse> getExemplaryById(@PathVariable Long id){
        ExemplaryResponse response = ExemplaryMapper.toExemplaryResponse(exemplaryService.findExemplaryById(id));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ExemplaryResponse>> getAllExemplaries() {

        return ResponseEntity.status(HttpStatus.OK).body(ExemplaryMapper.toExemplaryResponse(exemplaryService.findAllExemplaries()));
    }

}
