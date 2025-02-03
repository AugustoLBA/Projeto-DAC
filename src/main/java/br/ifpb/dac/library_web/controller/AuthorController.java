package br.ifpb.dac.library_web.controller;

import br.ifpb.dac.library_web.dto.AuthorRequest;
import br.ifpb.dac.library_web.dto.AuthorResponse;
import br.ifpb.dac.library_web.mapper.AuthorMapper;
import br.ifpb.dac.library_web.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/author")
public class AuthorController {

    private final AuthorService authorService;


    @PostMapping
    public ResponseEntity<AuthorResponse> save(@Valid @RequestBody AuthorRequest authorRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).
                body(AuthorMapper.toResponse(authorService.save(AuthorMapper.toAuthor(authorRequest))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(AuthorMapper.toResponse(authorService.getByid(id)));
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponse>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).
                body(AuthorMapper.toResponseList(authorService.findAllAuthor()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthorById(@PathVariable Long id) {
        authorService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponse> update(@PathVariable Long id, @Valid @RequestBody AuthorRequest authorRequest) {
        return ResponseEntity.ok(AuthorMapper.toResponse(authorService.update(id, authorRequest)));
    }
}
