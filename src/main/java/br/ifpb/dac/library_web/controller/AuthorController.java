package br.ifpb.dac.library_web.controller;

import br.ifpb.dac.library_web.entity.Author;
import br.ifpb.dac.library_web.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {
    private  final AuthorService authorService;

    @PostMapping("/save")
    public void save(Author author){
        author.setName("Suasuna");
        authorService.saveAuthor(author);
    }
}
