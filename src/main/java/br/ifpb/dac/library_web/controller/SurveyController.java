package br.ifpb.dac.library_web.controller;

import br.ifpb.dac.library_web.dto.AuthorRequest;
import br.ifpb.dac.library_web.dto.AuthorResponse;
import br.ifpb.dac.library_web.dto.SurveyRequest;
import br.ifpb.dac.library_web.dto.SurveyResponse;
import br.ifpb.dac.library_web.entity.Exemplary;
import br.ifpb.dac.library_web.entity.Survey;
import br.ifpb.dac.library_web.mapper.AuthorMapper;
import br.ifpb.dac.library_web.mapper.SurveyMapper;
import br.ifpb.dac.library_web.repository.ExemplaryRepository;
import br.ifpb.dac.library_web.repository.SurveyRepository;
import br.ifpb.dac.library_web.service.ExemplaryService;
import br.ifpb.dac.library_web.service.SurveyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/survey")
public class SurveyController {

    private final SurveyService surveyService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SurveyResponse> save(@Valid @RequestBody SurveyRequest surveyRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).
                body(SurveyMapper.toResponse(surveyService.saveSurvey(SurveyMapper.toSurvey(surveyRequest))));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<SurveyResponse> findSurveyById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(SurveyMapper.toResponse(surveyService.findSurveyById(id)));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN') OR hasAnyRole('USER')")
    public ResponseEntity<List<SurveyResponse>> findAllSurveys() {
        return ResponseEntity.status(HttpStatus.OK).body(SurveyMapper.toResponseList(surveyService.findSurveyAll()));
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        surveyService.deleteSurveyById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<SurveyResponse> update(@PathVariable Long id, @Valid @RequestBody SurveyRequest surveyRequest) {
        return ResponseEntity.ok(SurveyMapper.toResponse(surveyService.update(id, surveyRequest)));
    }

}
