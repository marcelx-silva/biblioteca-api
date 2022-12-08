package com.newgo.bibliotecaapi.bootstrap;

import com.newgo.bibliotecaapi.model.author.Author;
import com.newgo.bibliotecaapi.model.book.Book;
import com.newgo.bibliotecaapi.repository.AuthorRepository;
import com.newgo.bibliotecaapi.repository.BookRepository;
import com.newgo.bibliotecaapi.service.author.AuthorService;
import com.newgo.bibliotecaapi.service.book.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Component
public class BoostrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final BookService bookService;
    private Book book;
    private Author author;

    public BoostrapData(AuthorRepository authorRepository, BookRepository bookRepository, AuthorService authorService, BookService bookService) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.bookService = bookService;
    }


    private void logDatabase(){
        System.out.println("Log:");
        System.out.println("Quantidade de Autores: "+authorRepository.count());
        System.out.println("Quantidade de Livros: "+bookRepository.count());
    }

    private void createAuthors(){
        author = new Author("Yoshiyuki Sadamoto",LocalDate.of(1962,1,29));
        this.authorService.save(author);
        author = new Author("Junji Ito",LocalDate.of(1963,5,31));
        this.authorService.save(author);
        author = new Author("Yoshitoki Oima",LocalDate.of(1989,3,15));
        this.authorService.save(author);
        System.out.println(this.authorService.findAuthorByName("Junji Ito").getId());
        System.out.println(this.authorService.findAuthorByName("Yoshiyuki Sadamoto").getId());
        System.out.println(this.authorService.findAuthorByName("Yoshitoki Oima").getId());
    }


    private void createBooks(){
        Set<Author> authors = new HashSet<>();
        authors.add(this.authorService.findAuthorByName("Junji Ito"));
        book = new Book("Contos de Horror da Mimi (Edição Completa)","Horror","Darkside","Português",248,"978-6555982237",authors);
        this.bookService.save(book);
        book = new Book("Sensor (mangá volume único)","Horror","Darkside","Português",244,"978-6554480635",authors);
        this.bookService.save(book);
        authors = new HashSet<>();
        authors.add(this.authorService.findAuthorByName("Yoshiyuki Sadamoto"));
        book = new Book("Neon Genesis Evangelion Collector's Edition Vol. 01","Mecha","Editora JBC","Português",336,"978-6555943412",authors);
        this.bookService.save(book);
        authors = new HashSet<>();
        authors.add(this.authorService.findAuthorByName("Yoshitoki Oima"));
        book = new Book("A Voz do Silêncio (Edição Definitiva) – Volume 1","Drama","NewPOP","Português",392,"978-6586799071",authors);
        this.bookService.save(book);
        book = new Book("A Voz do Silêncio (Edição Definitiva) – Volume 2","Drama","NewPOP","Português",392,"978-6586799002",authors);
        this.bookService.save(book);
        book = new Book("A Voz do Silêncio (Edição Definitiva) – Volume 3 ","Drama","NewPOP","Português",392,"978-6586799019",authors);
        this.bookService.save(book);


    }


    @Override
    public void run(String... args) throws Exception {
        createAuthors();
        createBooks();
        logDatabase();
    }
}
