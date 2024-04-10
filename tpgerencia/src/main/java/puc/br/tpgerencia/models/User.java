package puc.br.tpgerencia.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Table(name = "tb_user")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String cpf;

    private String password;

    private String username;

    public User(String email, String password, String cpf, String username) {
        this.email = email;
        this.password = password;
        this.cpf = cpf;
        this.username = username;
    }
}
