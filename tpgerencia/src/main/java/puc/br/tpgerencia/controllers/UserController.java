package puc.br.tpgerencia.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import puc.br.tpgerencia.models.User;
import puc.br.tpgerencia.models.dto.UserDTO;
import puc.br.tpgerencia.models.response.Response;
import puc.br.tpgerencia.services.UserService;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/user/")
@CrossOrigin(originPatterns = {"*"})
public class UserController {

    /* Dependency Injection */

    private final UserService USER_SERVICE;

    public UserController(UserService userService) {
        USER_SERVICE = userService;
    }

    /* Endpoints */

    @PostMapping
    public ResponseEntity<?> _insertUser(@RequestBody UserDTO dto){
        if(dto == null)
            return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Response response = USER_SERVICE._insertUser(dto);

        return ResponseEntity.status(response.getStatus_code()).body(response.getMessage());
    }

    @PutMapping
    public ResponseEntity<?> _editUser(@RequestBody User user){
        if(user == null)
            return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Response response = USER_SERVICE._editUser(user);

        return ResponseEntity.status(response.getStatus_code()).body(response.getMessage());
    }

    @DeleteMapping
    public ResponseEntity<?> _deleteUser(@RequestParam UUID id){
        if(id == null)
            return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Response response = USER_SERVICE._deleteUser(id);

        return ResponseEntity.status(response.getStatus_code()).body(response.getMessage());
    }

    @GetMapping("id/{id}")
    public ResponseEntity<?> _getUser(@PathVariable UUID id){
        if(id == null)
            return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Optional<User> user = USER_SERVICE._getUser(id);

        return ResponseEntity.ok(user);
    }

    @GetMapping("")
    public ResponseEntity<?> _getAllUsers(){
        return ResponseEntity.ok(USER_SERVICE._getAllUsers());
    }

    @GetMapping("{identifier}")
    public ResponseEntity<?> _getUserByUsernameOrCpf(@PathVariable String identifier){
        if(identifier == null)
            return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Optional<User> user = USER_SERVICE._getUserByUsernameOrCpf(identifier);

        return ResponseEntity.ok(user);
    }
}
