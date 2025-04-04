import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import Aluno from '../models/Aluno';

@Injectable({
  providedIn: 'root',
})
export class AlunoService {
  alunoAPI = 'http://localhost:8080/api/v1/alunos';

  constructor(private http: HttpClient) {}

  listarAlunos(): Observable<Aluno[]> {
    return this.http.get<Aluno[]>(this.alunoAPI);
  }

  buscarAlunoPorId(id: number): Observable<Aluno>{
    return this.http.get<Aluno>(`${this.alunoAPI}/${id}`)
  }

  cadastrarAluno(aluno: Aluno): Observable<Aluno>{
    return this.http.post<Aluno>(this.alunoAPI, aluno)
  }

  deletarAluno(idAluno: number): Observable<Aluno>{
    return this.http.delete<Aluno>(`${this.alunoAPI}/${idAluno}`)
  }

}
