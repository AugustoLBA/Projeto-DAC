package br.ifpb.dac.library_web.controller;

import br.ifpb.dac.library_web.entity.Author;
import br.ifpb.dac.library_web.entity.Publisher;
import br.ifpb.dac.library_web.entity.Book;
import br.ifpb.dac.library_web.service.AuthorService;
import br.ifpb.dac.library_web.service.PublisherService;
import br.ifpb.dac.library_web.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final PublisherService publisherService;

    // Lista de gêneros predefinidos
    private static final List<String> GENRES = Arrays.asList(
            "Fiction", "Non-Fiction", "Science Fiction", "Fantasy",
            "Biography", "Mystery", "Romance", "Historical");

    @GetMapping("/cadastrar-livro")
    public String showBookForm(Model model) {
        List<Author> authors = authorService.getAllAuthors();
        List<Publisher> publishers = publisherService.getAllPublishers();

        model.addAttribute("book", new Book());
        model.addAttribute("authors", authors);
        model.addAttribute("publishers", publishers);
        model.addAttribute("genres", GENRES); // Passa a lista de gêneros para o modelo

        return "bookForm";
    }

    @PostMapping("/salvar-livro")
    public String saveBook(@ModelAttribute("book") Book book) {
        bookService.save(book);
        return "redirect:/livros";
    }

    @GetMapping("/livros")
    public String listBooks(@RequestParam(defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 10); // Padrão de 10 livros por página
        Page<Book> booksPage = bookService.getAllBooks(pageable);

        model.addAttribute("books", booksPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", booksPage.getTotalPages());

        return "bookList";
    }

    @GetMapping("/editar-livro/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Book book = bookService.findById(id);
        List<Author> authors = authorService.getAllAuthors();
        List<Publisher> publishers = publisherService.getAllPublishers();

        model.addAttribute("book", book);
        model.addAttribute("authors", authors);
        model.addAttribute("publishers", publishers);
        model.addAttribute("genres", GENRES); // Passa a lista de gêneros para o modelo

        return "bookForm";
    }

    @GetMapping("/deletar-livro/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookService.deleteById(id);
        return "redirect:/livros";
    }

    @GetMapping("/buscar-livros")
    public String searchBooks(@RequestParam("title") String title, @RequestParam(defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Book> booksPage = bookService.searchBooksByTitle(title, pageable);

        model.addAttribute("books", booksPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", booksPage.getTotalPages());
        model.addAttribute("title", title); // Para manter o título da busca no campo de pesquisa

        return "bookList";
    }

    @GetMapping("/find-livros")
    public String buscarLivros(@RequestParam(value = "title", required = false) String title,
            @RequestParam(defaultValue = "0") int page, Model model) {

        // Define a paginação com tamanho de página fixo (10 itens por página, por exemplo)
        Pageable pageable = PageRequest.of(page, 5);

        // Busca os livros se o título for informado
        if (title != null && !title.isEmpty()) {
            Page<Book> booksPage = bookService.searchBooksByTitle(title, pageable);
            model.addAttribute("books", booksPage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", booksPage.getTotalPages());
            model.addAttribute("title", title); // Mantém o título da busca no formulário
        }

        return "findLivros"; // Nome do arquivo HTML da página de busca
    }
    @GetMapping("/biblioteca")
    public String biblioteca() {
        return "biblioteca"; // Nome do arquivo HTML da biblioteca
    }
}
