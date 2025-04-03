package desafio.unifor.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "alunos_cursos", schema = "public")
public class AlunoCursoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private AlunoEntity aluno;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private CursoEntity curso;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CursoEntity getCurso() {
        return curso;
    }

    public void setCurso(CursoEntity curso) {
        this.curso = curso;
    }

    public AlunoEntity getAluno() {
        return aluno;
    }

    public void setAluno(AlunoEntity aluno) {
        this.aluno = aluno;
    }

    @Override
    public String toString() {
        return "AlunosCursosEntity{" +
                "id=" + id +
                ", aluno=" + aluno +
                ", curso=" + curso +
                '}';
    }
}
