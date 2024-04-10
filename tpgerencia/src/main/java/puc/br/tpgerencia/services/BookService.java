package puc.br.tpgerencia.services;

import org.springframework.stereotype.Service;
import puc.br.tpgerencia.mapper.BookMapper;
import puc.br.tpgerencia.models.Book;
import puc.br.tpgerencia.models.dto.BookDTO;
import puc.br.tpgerencia.models.interfaces.IMapper;
import puc.br.tpgerencia.models.response.InsertResponse;
import puc.br.tpgerencia.models.response.Response;
import puc.br.tpgerencia.repositories.BookRepository;
import puc.br.tpgerencia.utils.Dictionary;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {

    /* Dependency Injection */

    private final BookRepository BOOK_REPOSITORY;

    private final IMapper MAPPER;

    /* Constructor */

    public BookService(BookRepository bookRepository) {
        this.BOOK_REPOSITORY = bookRepository;
        this.MAPPER = new BookMapper();
    }

    /* Public Methods */

    public Response _insertBook(BookDTO dto){

        Optional<Book> book_find = BOOK_REPOSITORY._findByNameAndAuthor(dto.name(), dto.author());

        if(book_find.isPresent())
            return new Response(Dictionary.FAILED_INSERT, 400);

        Book book_insert = (Book) MAPPER._convertObject(dto);

        BOOK_REPOSITORY.save(book_insert);

        return new InsertResponse(Dictionary.SUCCESS_INSERT, 201, book_insert.getId());
    }

    public Response _deleteBook(UUID id){

        Optional<Book> book_delete = _getBookById(id);

        if(book_delete.isEmpty())
            return new Response(Dictionary.FAILED_FIND, 404);

        BOOK_REPOSITORY.delete(book_delete.get());

        return new Response(Dictionary.SUCCESS_DELETE, 202);
    }

    public Response _editBook(Book book){

        BOOK_REPOSITORY.saveAndFlush(book);

        return new Response(Dictionary.SUCCESS_EDIT, 202);
    }

    public List<Book> _getAllBooks(){
        return BOOK_REPOSITORY.findAll();
    }

    public Optional<Book> _getBookById(UUID id){
        return BOOK_REPOSITORY.findById(id);
    }

    /* Private Methods */
}
