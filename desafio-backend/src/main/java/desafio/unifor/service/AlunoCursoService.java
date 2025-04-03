package desafio.unifor.service;

import desafio.unifor.dto.aluno.AlunoDTO;
import desafio.unifor.dto.alunoCurso.AlunoCursoDTO;
import desafio.unifor.entity.AlunoCursoEntity;
import desafio.unifor.entity.AlunoEntity;
import desafio.unifor.entity.CursoEntity;
import desafio.unifor.mapper.AlunoCursoMapper;
import desafio.unifor.mapper.AlunoMapper;
import desafio.unifor.mapper.CursoMapper;
import desafio.unifor.repository.AlunoCursoRepository;
import desafio.unifor.repository.AlunoRepository;
import desafio.unifor.repository.CursoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class AlunoCursoService {

    @Inject
    AlunoCursoRepository alunoCursoRepository;

    @Inject
    AlunoRepository alunoRepository;

    @Inject
    CursoRepository cursoRepository;

    @Inject
    AlunoCursoMapper alunoCursoMapper;

    @Inject
    AlunoMapper alunoMapper;

    @Inject
    CursoMapper cursoMapper;

    @Transactional
    public void adicionaAlunoAoCurso(AlunoCursoDTO alunoCursoDTO) {
        AlunoEntity aluno = alunoRepository.findById(alunoCursoDTO.getCurso().getId());
        CursoEntity curso = cursoRepository.findById(alunoCursoDTO.getCurso().getId());
        validaAluno(aluno);
        validaCurso(curso);

        AlunoCursoEntity matricula = new AlunoCursoEntity();
        matricula.setCurso(curso);
        matricula.setAluno(aluno);

        alunoCursoRepository.persist(matricula);
    }

    public List<AlunoDTO> buscarAlunosPorCurso(long cursoId) {
        AlunoEntity aluno = alunoRepository.findById(cursoId);
        validaAluno(aluno);

        List<AlunoDTO> alunosCadastrados = new ArrayList<>();
        alunoCursoRepository.buscarAlunoCursos(cursoId).stream().forEach(alunoCurso -> {
           alunosCadastrados.add(alunoMapper.toDTO(alunoCurso));
        });

        return alunosCadastrados;
    }

    private void validaAluno(AlunoEntity aluno) {
        if (aluno == null){
            throw new NotFoundException("Aluno não encontrado!");
        }
    }

    private void validaCurso(CursoEntity curso) {
        if (curso == null){
            throw new NotFoundException("Curso não encontrado!");
        }
    }


}
