package puc.br.tpgerencia.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertResponse extends Response{

    private UUID id;

    public InsertResponse(String message, int status_code, UUID id) {
        super(message, status_code);
        this.id = id;
    }
}
