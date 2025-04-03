package desafio.unifor.mapper;

import desafio.unifor.dto.alunoCurso.AlunoCursoDTO;
import desafio.unifor.entity.AlunoCursoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "cdi")
public interface AlunoCursoMapper {

    AlunoCursoMapper INSTANCE = Mappers.getMapper(AlunoCursoMapper.class);

    AlunoCursoDTO toDTO(AlunoCursoEntity alunoCurso);

    AlunoCursoEntity toEntity(AlunoCursoDTO dto);

}
