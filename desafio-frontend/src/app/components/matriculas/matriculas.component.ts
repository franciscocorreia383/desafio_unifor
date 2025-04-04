import { Component } from '@angular/core';
import { MatSidenavModule } from '@angular/material/sidenav';
import { CursoService } from '../../services/curso.service';
import { MatTable, MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { AlunoCursoDialogComponent } from '../../shared/aluno-curso-dialog/aluno-curso-dialog.component';

import Curso from '../../models/Curso';
import Aluno from '../../models/Aluno';
import { AlunoService } from '../../services/aluno.service';
import AlunoCurso from '../../models/AlunoCurso';
import { ConfirmDialogComponent } from '../../shared/confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-matriculas',
  imports: [
    MatSidenavModule,
    MatTableModule,
    MatIconModule,
    MatButtonModule,
    MatDialogModule,
  ],
  templateUrl: './matriculas.component.html',
  styleUrl: './matriculas.component.css',
})
export class MatriculasComponent {
  curso: Curso = {} as Curso;
  alunos: Aluno[] = [];
  displayedColumns: string[] = ['matricula', 'nome', 'actions'];

  constructor(
    public cursoServices: CursoService,
    public alunoServices: AlunoService,
    private route: ActivatedRoute,
    public dialog: MatDialog
  ) {
    const idCurso: number = Number(this.route.snapshot.paramMap.get('id'));
    this.cursoServices
      .buscarCursoPorId(idCurso)
      .subscribe((data) => (this.curso = data));
    this.cursoServices
      .buscarAlunosPorCurso(idCurso)
      .subscribe((data) => (this.alunos = data));
  }

  openDialog(aluno: Aluno | null) {
    const dialogRef = this.dialog.open(AlunoCursoDialogComponent, {
      width: '250px',
      data:
        aluno != null
          ? aluno
          : {
              id: '',
              nome: '',
              matricula: '',
            },
    });

    dialogRef.afterClosed().subscribe((result) => {
      this.alunoServices.buscarAlunoPorId(result).subscribe((data) => {
        this.cursoServices
          .matriculaAlunoCurso(data, this.curso)
          .subscribe((data) => {
            this.cursoServices
              .buscarAlunosPorCurso(this.curso.id)
              .subscribe((data) => (this.alunos = data));
          });
      });
    });
  }

  removerAluno(aluno: Aluno) {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      data: {
        message: `Deseja realmente deletar o aluno ${aluno.nome}?`,
      },
    });

    dialogRef.afterClosed().subscribe((confirmado) => {
     try {
      if (confirmado) {
        this.cursoServices.deletaMatriculaAlunoCurso(aluno.id, this.curso.id).subscribe(() => {
          this.cursoServices
          .buscarAlunosPorCurso(this.curso.id)
          .subscribe((data) => (this.alunos = data));
        });
      }
     } catch (error) {
      console.log(error);
      
     }
    });
  }
}
