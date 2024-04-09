package puc.br.tpgerencia.servicesTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import puc.br.tpgerencia.models.User;
import puc.br.tpgerencia.models.dto.UserDTO;
import puc.br.tpgerencia.repositories.UserRepository;
import puc.br.tpgerencia.services.UserService;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;



@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setup() {

        userService = new UserService(userRepository);
    }
    @Test
    void insertUser_Success() {
        UserDTO dto = new UserDTO("TESTE", "USER@TESTE.COM", "124", "123");
        User user = new User();
        when(userRepository._findByUsername(dto.username())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);
        Assertions.assertEquals(201, userService._insertUser(dto).getStatus_code());
    }

    @Test
    void insertUser_Failure() {
        UserDTO dto = new UserDTO("TESTE", "USER@TESTE.COM", "124", "123");
        when(userRepository._findByUsername(dto.username())).thenReturn(Optional.of(new User()));
        Assertions.assertEquals(400, userService._insertUser(dto).getStatus_code());
    }

    @Test
    void getUser_Success() {
        UUID id = UUID.randomUUID(); // Preencha o ID conforme necessário
        User user = new User(); // Preencha o usuário conforme necessário
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        Assertions.assertTrue(userService._getUser(id).isPresent());
    }

    @Test
    void getUser_NotFound() {
        UUID id = UUID.randomUUID(); // Preencha o ID conforme necessário
        when(userRepository.findById(id)).thenReturn(Optional.empty());
        Assertions.assertTrue(userService._getUser(id).isEmpty());
    }
}
