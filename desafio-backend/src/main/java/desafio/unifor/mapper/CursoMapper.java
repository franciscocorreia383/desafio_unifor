package desafio.unifor.mapper;

import desafio.unifor.dto.curso.CursoDTO;
import desafio.unifor.entity.CursoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "cdi")
public interface CursoMapper {
    CursoMapper INSTANCE = Mappers.getMapper(CursoMapper.class);

    CursoDTO toDTO(CursoEntity curso);

    CursoEntity toEntity(CursoDTO dto);

}
