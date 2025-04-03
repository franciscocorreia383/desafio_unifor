package desafio.unifor.dto.alunoCurso;

import desafio.unifor.dto.aluno.AlunoDTO;
import desafio.unifor.dto.curso.CursoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
public class AlunoCursoDTO {
    private AlunoDTO aluno;

    private CursoDTO curso;
}
