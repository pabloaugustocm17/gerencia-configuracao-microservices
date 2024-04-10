package puc.br.tpgerencia.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Table(name = "tb_book")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Date launch_date;

    private String name;

    private String author;

    private String description;

    private Double price;

    public Book(Date launch_date, String name, String author, String description, Double price) {
        this.launch_date = launch_date;
        this.name = name;
        this.author = author;
        this.description = description;
        this.price = price;
    }
}
