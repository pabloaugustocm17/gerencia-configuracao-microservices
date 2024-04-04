package puc.br.tpgerencia.models.dto;

import java.util.Date;

public record BookDTO(
        Date launch_date,
        String name,
        String author,
        String description,
        Double price
) {
}
