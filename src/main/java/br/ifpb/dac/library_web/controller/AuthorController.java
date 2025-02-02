package br.ifpb.dac.library_web.controller;

import br.ifpb.dac.library_web.dto.AuthorRequest;
import br.ifpb.dac.library_web.dto.AuthorResponse;
import br.ifpb.dac.library_web.mapper.AuthorMapper;
import br.ifpb.dac.library_web.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/author")
public class AuthorController {

    private final AuthorService authorService;


    @PostMapping
    public ResponseEntity<AuthorResponse> save (@Valid @RequestBody AuthorRequest authorRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).
                body(AuthorMapper.toResponse(authorService.save(AuthorMapper.toAuthor(authorRequest))));
    }

}
