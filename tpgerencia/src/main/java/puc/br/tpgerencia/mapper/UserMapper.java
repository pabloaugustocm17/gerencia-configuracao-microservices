package puc.br.tpgerencia.mapper;

import puc.br.tpgerencia.models.User;
import puc.br.tpgerencia.models.dto.UserDTO;
import puc.br.tpgerencia.models.interfaces.IMapper;

public class UserMapper implements IMapper {
    @Override
    public Object _convertObject(Object object_convert) {

        UserDTO dto = (UserDTO) object_convert;

        return  new User(
                dto.email(),
                dto.password(),
                dto.cpf(),
                dto.username()
        );
    }
}
