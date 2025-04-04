package desafio.unifor.repository;

import desafio.unifor.entity.AlunoCursoEntity;
import desafio.unifor.entity.AlunoEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class AlunoCursoRepository implements PanacheRepository<AlunoCursoEntity> {
    public List<AlunoEntity> buscarAlunoCursos(long cursoId){
        return find("curso.id", cursoId).stream()
                .map(alunoCurso -> alunoCurso.getAluno())
                .collect(Collectors.toList());
    }

    public boolean validaAlunoCurso(Long alunoId, Long cursoId) {
        return count("aluno.id = ?1 and curso.id = ?2", alunoId, cursoId) > 0;
    }

    public boolean deletarAlunoCurso(Long alunoId, Long cursoId) {
        return delete("aluno.id = ?1 and curso.id = ?2", alunoId, cursoId) > 0;
    }
}
