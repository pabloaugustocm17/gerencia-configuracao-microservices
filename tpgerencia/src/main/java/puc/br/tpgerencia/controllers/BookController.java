package puc.br.tpgerencia.controllers;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import puc.br.tpgerencia.models.Book;
import puc.br.tpgerencia.models.dto.BookDTO;
import puc.br.tpgerencia.models.response.Response;
import puc.br.tpgerencia.services.BookService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/book/")
@CrossOrigin(originPatterns = {"*"})
public class BookController {

    /* Dependency Injection */

    private final BookService BOOK_SERVICE;

    /* Constructor */

    public BookController(BookService bookService) {
        this.BOOK_SERVICE = bookService;
    }

    /* Endpoints */

    @PostMapping
    public ResponseEntity<?> _insertBook(
            @RequestBody BookDTO dto
    ){
        if(dto == null)
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));

        Response response = BOOK_SERVICE._insertBook(dto);

        return ResponseEntity.status(response.getStatus_code()).body(response);
    }

    @PutMapping
    public ResponseEntity<?> _editBook(
            @RequestBody Book book
    ){
        if(book == null)
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));

        Response response = BOOK_SERVICE._editBook(book);

        return ResponseEntity.status(response.getStatus_code()).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> _deleteBook(
            @PathVariable UUID id
    ){

        if(id == null)
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));

        Response response = BOOK_SERVICE._deleteBook(id);

        return ResponseEntity.status(response.getStatus_code()).body(response);
    }

    @GetMapping
    public ResponseEntity<?> _getAllBooks(){
        List<Book> books = BOOK_SERVICE._getAllBooks();

        if(books.isEmpty())
            return new ResponseEntity<>(HttpStatusCode.valueOf(204));

        return ResponseEntity.ok(books);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> _getBookById(
            @PathVariable UUID id
    ){
        Optional<Book> book = BOOK_SERVICE._getBookById(id);

        if(book.isEmpty())
            return new ResponseEntity<>(HttpStatusCode.valueOf(204));

        return ResponseEntity.ok(book.get());
    }


}
