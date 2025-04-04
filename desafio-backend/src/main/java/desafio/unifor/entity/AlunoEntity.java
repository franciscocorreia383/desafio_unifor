package desafio.unifor.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "aluno", schema = "public")
public class AlunoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String matricula;

    @OneToMany(mappedBy = "aluno")
    private List<AlunoCursoEntity> alunosCursos;

    @PrePersist
    public void gerarMatricula() {
        this.matricula = "MAT" + System.currentTimeMillis();
    }

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

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public List<AlunoCursoEntity> getAlunosCursos() {
        return alunosCursos;
    }

    public void addAlunosCursos(AlunoCursoEntity alunoCurso) {
        this.alunosCursos.add(alunoCurso);
    }

    @Override
    public String toString() {
        return "AlunoEntity{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", matricula='" + matricula + '\'' +
                '}';
    }
}
