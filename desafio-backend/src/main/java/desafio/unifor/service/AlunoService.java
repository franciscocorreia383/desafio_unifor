package desafio.unifor.service;


import desafio.unifor.dto.aluno.AlunoCadastroDTO;
import desafio.unifor.dto.aluno.AlunoDTO;
import desafio.unifor.entity.AlunoEntity;
import desafio.unifor.mapper.AlunoMapper;
import desafio.unifor.repository.AlunoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class AlunoService {

    @Inject
    AlunoRepository alunoRepository;

    @Inject
    private AlunoMapper alunoMapper;

    public List<AlunoDTO> buscarAlunos() {
        List<AlunoDTO> alunos = new ArrayList<>();

        alunoRepository.findAll().stream().forEach(aluno -> {
            alunos.add(alunoMapper.toDTO(aluno));
        });
        System.out.println(alunos);
        return alunos;
    }

    @Transactional
    public void criarAluno(AlunoCadastroDTO aluno) {
        alunoRepository.persistAndFlush(alunoMapper.toCadastroEntity(aluno));
    }

    @Transactional
    public void excluirAluno(long idAluno) {
        AlunoEntity aluno = alunoRepository.findById(idAluno);
        if (aluno == null){
            throw new NotFoundException("Aluno não existe na base de dados");
        }
        alunoRepository.delete(aluno);
    }
}
