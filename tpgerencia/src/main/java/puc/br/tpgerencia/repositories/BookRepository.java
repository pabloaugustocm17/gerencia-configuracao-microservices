package puc.br.tpgerencia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import puc.br.tpgerencia.models.Book;

import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    @Query("SELECT B FROM Book AS B WHERE B.name =:name AND B.author =:author")
    Optional<Book> _findByNameAndAuthor(String name, String author);
}
