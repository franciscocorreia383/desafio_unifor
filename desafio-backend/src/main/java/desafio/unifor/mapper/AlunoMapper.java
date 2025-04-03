package desafio.unifor.mapper;

import desafio.unifor.dto.AlunoCadastroDTO;
import desafio.unifor.dto.AlunoDTO;
import desafio.unifor.entity.AlunoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "cdi")
public interface AlunoMapper {

    AlunoMapper INSTANCE = Mappers.getMapper(AlunoMapper.class);
    AlunoDTO toDTO(AlunoEntity aluno);

    AlunoEntity toEntity(AlunoDTO dto);

    AlunoCadastroDTO toAlunoCadastroDTO(AlunoEntity aluno);

    AlunoEntity toCadastroEntity(AlunoCadastroDTO dto);
}
