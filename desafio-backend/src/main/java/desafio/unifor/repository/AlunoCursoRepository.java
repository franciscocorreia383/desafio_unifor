package desafio.unifor.repository;

import desafio.unifor.entity.AlunoCursoEntity;
import desafio.unifor.entity.AlunoEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.Query;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class AlunoCursoRepository implements PanacheRepository<AlunoCursoEntity> {
    public List<AlunoEntity> buscarAlunoCursos(long cursoId){
        return find("curso.id", cursoId).stream()
                .map(alunoCurso -> alunoCurso.getAluno())
                .collect(Collectors.toList());
    }
}
