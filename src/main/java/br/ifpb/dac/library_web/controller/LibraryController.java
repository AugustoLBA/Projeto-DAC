package br.ifpb.dac.library_web.controller;

import br.ifpb.dac.library_web.dto.LibraryRequest;
import br.ifpb.dac.library_web.dto.LibraryResponse;
import br.ifpb.dac.library_web.mapper.LibraryMapper;
import br.ifpb.dac.library_web.service.LibraryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/libraries")
public class LibraryController {

    private final LibraryService libraryService;

    @PostMapping
    public ResponseEntity<LibraryResponse> save(@Valid @RequestBody LibraryRequest libraryRequest){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(LibraryMapper.toResponse(libraryService.save(LibraryMapper.toLibrary(libraryRequest))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibraryResponse> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(LibraryMapper.toResponse(libraryService.findById(id)));
    }

    @GetMapping
    public ResponseEntity<List<LibraryResponse>> findAllLibraries(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(LibraryMapper.toResponseList(libraryService.findAll()));
    }
    @PutMapping("/{id}")
        public ResponseEntity<LibraryResponse> updateLibrary(@PathVariable Long id,@Valid @RequestBody LibraryRequest libraryRequest){

            return ResponseEntity.status(HttpStatus.OK).
                    body(LibraryMapper.toResponse(libraryService.update(id,libraryRequest)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibrary(@PathVariable Long id){
        libraryService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
