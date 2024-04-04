package puc.br.tpgerencia.mapper;

import puc.br.tpgerencia.models.Book;
import puc.br.tpgerencia.models.dto.BookDTO;
import puc.br.tpgerencia.models.interfaces.IMapper;

public class BookMapper implements IMapper {

    public Object _convertObject(Object object_convert) {

        BookDTO dto = (BookDTO) object_convert;

        return new Book(
                dto.launch_date(),
                dto.name(),
                dto.author(),
                dto.description(),
                dto.price()
        );
    }
}
