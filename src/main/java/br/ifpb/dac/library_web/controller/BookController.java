package br.ifpb.dac.library_web.controller;

import br.ifpb.dac.library_web.entity.Author;
import br.ifpb.dac.library_web.entity.Publisher;
import br.ifpb.dac.library_web.entity.Book;
import br.ifpb.dac.library_web.service.AuthorService;
import br.ifpb.dac.library_web.service.PublisherService;
import br.ifpb.dac.library_web.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final PublisherService publisherService;

    @GetMapping("/cadastrar-livro")
    public String showBookForm(Model model) {
        // Buscar todos os autores e editoras da base
        List<Author> authors = authorService.getAllAuthors();
        List<Publisher> publishers = publisherService.getAllPublishers();

        // Criar um novo objeto Livro para preencher no formulário
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authors);
        model.addAttribute("publishers", publishers);

        return "bookForm";  // O nome do seu template
    }

    @PostMapping("/salvar-livro")
    public String saveBook(@ModelAttribute("book") Book book) {
        // Salvar o livro na base de dados (com autores e publisher já associados)
        bookService.save(book);
        return "redirect:/livros";  // Redireciona para a lista de livros ou uma página de confirmação
    }
}
