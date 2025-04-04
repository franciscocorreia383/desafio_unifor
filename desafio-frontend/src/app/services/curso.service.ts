import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import Curso from '../models/Curso';
import Aluno from '../models/Aluno';
import AlunoCurso from '../models/AlunoCurso';

@Injectable({
  providedIn: 'root'
})

export class CursoService {

  cursoAPI = 'http://localhost:8080/api/v1/cursos'
  alunoCursoAPI = 'http://localhost:8080/api/v1/alunos-cursos'

  constructor(private http: HttpClient) { }

  listarCursos(): Observable<Curso[]>{
    return this.http.get<Curso[]>(this.cursoAPI)
  }

  cadastrarCurso(curso: Curso): Observable<Curso>{
    return this.http.post<Curso>(this.cursoAPI, curso)
  }

  buscarCursoPorId(id: number): Observable<Curso>{
    return this.http.get<Curso>(`${this.cursoAPI}/${id}`)
  }

  buscarAlunosPorCurso(cursoId: number): Observable<Aluno[]>{
    return this.http.get<Aluno[]>(`${this.alunoCursoAPI}/${cursoId}`)
  }

  matriculaAlunoCurso(aluno: Aluno, curso: Curso): Observable<AlunoCurso> {
    const alunoCurso: AlunoCurso = {
      aluno: { id: aluno.id },
      curso: { id: curso.id },
    };
  
    return this.http.post<AlunoCurso>(this.alunoCursoAPI, alunoCurso);
  }

  deletaMatriculaAlunoCurso(alunoId: number, cursoId: number): Observable<void> {
    return this.http.delete<void>(`${this.alunoCursoAPI}/aluno/${alunoId}/curso/${cursoId}`);
  }
  

}
