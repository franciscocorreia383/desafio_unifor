package desafio.unifor.service;

import desafio.unifor.dto.curso.CursoDTO;
import desafio.unifor.entity.CursoEntity;
import desafio.unifor.mapper.CursoMapper;
import desafio.unifor.repository.CursoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CursoService {

    @Inject
    private CursoRepository cursoRepository;

    @Inject
    private CursoMapper cursoMapper;

    @Transactional
    public void criarCurso(CursoDTO cursoDTO) {
        cursoRepository.persist(cursoMapper.toEntity(cursoDTO));
    }

    public List<CursoDTO> listarCursos() {
        List<CursoDTO> cursos = new ArrayList<>();

        cursoRepository.findAll().stream().forEach(curso -> {
            cursos.add(cursoMapper.toDTO(curso));
        });

        return cursos;
    }

    public CursoDTO buscarCursoPorId(long id) {
        CursoEntity curso = cursoRepository.findById(id);

        if (curso == null) {
            throw new NotFoundException("Curso não cadastrado!");
        }

        return cursoMapper.toDTO(curso);
    }
}
