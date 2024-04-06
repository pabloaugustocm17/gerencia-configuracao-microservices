package puc.br.tpgerencia.models.dto;

public record UserDTO(
        String username,
        String email,
        String password,
        String cpf
) { }
