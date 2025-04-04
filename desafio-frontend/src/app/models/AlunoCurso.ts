import Aluno from './Aluno';
import Curso from './Curso';

export default interface AlunoCurso {
  aluno: Pick<Aluno, 'id'>;
  curso: Pick<Curso, 'id'>;
}
