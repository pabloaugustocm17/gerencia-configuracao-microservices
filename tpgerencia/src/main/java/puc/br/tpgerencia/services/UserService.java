package puc.br.tpgerencia.services;

import org.springframework.stereotype.Service;
import puc.br.tpgerencia.mapper.UserMapper;
import puc.br.tpgerencia.models.User;
import puc.br.tpgerencia.models.dto.UserDTO;
import puc.br.tpgerencia.models.interfaces.IMapper;
import puc.br.tpgerencia.models.response.InsertResponse;
import puc.br.tpgerencia.models.response.Response;
import puc.br.tpgerencia.repositories.UserRepository;
import puc.br.tpgerencia.utils.Dictionary;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    /* Dependency Injection */

    private final UserRepository USER_REPOSITORY;

    private final IMapper MAPPER;

    /* Constructor */
    public UserService(UserRepository userRepository) {
        USER_REPOSITORY = userRepository;
        MAPPER = new UserMapper();
    }

    /* Public Methods */

    public Response _insertUser(UserDTO dto){

        Optional<User> user_find = USER_REPOSITORY._findByUsername(dto.username());

        if(user_find.isPresent())
            return new Response(Dictionary.FAILED_INSERT, 400);

        User user_insert = (User) MAPPER._convertObject(dto);
        USER_REPOSITORY.save(user_insert);
        return new InsertResponse(Dictionary.SUCCESS_INSERT, 201, user_insert.getId());
    }

    public Optional<User> _getUser(UUID id){
        return USER_REPOSITORY.findById(id);
    }
    public Optional<User> _getUserByUsernameOrCpf(String identifier){
         return USER_REPOSITORY._findByUsernameOrCpf(identifier);
    }

    public Response _editUser(User user){
        USER_REPOSITORY.saveAndFlush(user);
        return  new Response(Dictionary.SUCCESS_EDIT, 202);
    }

    public Response _deleteUser(UUID id){
        Optional<User> user_find = USER_REPOSITORY.findById(id);

        if(user_find.isEmpty())
            return new Response(Dictionary.FAILED_FIND, 404);

        USER_REPOSITORY.delete(user_find.get());
        return new Response(Dictionary.SUCCESS_DELETE, 202);
    }

    public List<User> _getAllUsers(){
        return USER_REPOSITORY.findAll();
    }

}
