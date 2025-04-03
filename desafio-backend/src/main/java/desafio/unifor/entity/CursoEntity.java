package desafio.unifor.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "curso", schema = "public")
public class CursoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "curso")
    private List<AlunoCursoEntity> alunosCursos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<AlunoCursoEntity> getAlunosCursos() {
        return alunosCursos;
    }

    public void addAlunosCursos(AlunoCursoEntity alunoCurso) {
        this.alunosCursos.add(alunoCurso);
    }

    @Override
    public String toString() {
        return "CursoEntity{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", alunosCursos=" + alunosCursos +
                '}';
    }
}
