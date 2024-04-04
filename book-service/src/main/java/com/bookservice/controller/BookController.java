package com.bookservice.controller;

import com.bookservice.config.EnvConfiguration;
import com.bookservice.dto.BookDTO;
import com.bookservice.model.Book;
import com.bookservice.response.Cambio;
import com.bookservice.service.BookService;
import com.bookservice.service.ResponseService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "book-service/")
@Tag(name = "Book endpoint")
public class BookController {

    private final EnvConfiguration ENVS;

    private final BookService BOOK_SERVICE;

    private final ResponseService RESPONSE_SERVICE;

    private final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    public BookController(EnvConfiguration configurationEnv, BookService bookService, ResponseService responseService){
        this.ENVS = configurationEnv;
        this.BOOK_SERVICE = bookService;
        this.RESPONSE_SERVICE = responseService;
    }

    /* Retry realiza x tentativas de execução, caso dê erro ele imprime na tela */
    /* Circuit breaker realiza a mecânica de estados de 'Aberto, 'Meio-Aberto' e 'Fechado' */
    /* RateLimiter, limtia as requisições por um período de tempo e x vezes */
    /* Bulkhead determina a quantidade máxima de requisições recorrentes */

    @GetMapping("{id}/{currency}")
    @Retry(name = "find-book", fallbackMethod = "fallbackFindBook")
    @CircuitBreaker(name = "find-book", fallbackMethod = "fallbackFindBook")
    @RateLimiter(name = "default")
    @Bulkhead(name = "default")
    @Operation(summary = "Find a book by id")
    public BookDTO findBook(

            @PathVariable(name = "id") Long id,
            @PathVariable(name = "currency") String currency

    ){


        LOGGER.info("TENTATIVA DE REALIZAR REQUISIÇÃO");

        Book book = BOOK_SERVICE.getBookById(id);

        String port = ENVS.returnPort();

        Cambio cambio = RESPONSE_SERVICE.retornaCambio(currency, book.getPrice());

        return BOOK_SERVICE.factoryBookDTO(book, currency, port, cambio.getConvertedValue());
    }

    /* Sempre recebe um exception (Recomendado criar um para cada execução) */
    public String fallbackFindBook(Exception e){

        return "ERROR IN FIND-BOOK";

    }

}
