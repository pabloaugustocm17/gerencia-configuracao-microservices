package puc.br.tpgerencia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import puc.br.tpgerencia.models.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("SELECT U FROM User AS U WHERE U.username =:identifier OR U.cpf =:identifier OR U.email =:identifier")
    Optional<User> _findByUsernameOrCpf(String identifier);
    @Query("SELECT U FROM User AS U WHERE U.username =:username")
    Optional<User> _findByUsername(String username);
    @Query("SELECT U FROM User AS U WHERE U.cpf =:cpf")
    Optional<User> _findByCpf(String cpf);
}
