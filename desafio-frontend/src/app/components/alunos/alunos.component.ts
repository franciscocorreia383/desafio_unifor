import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MatSidenavModule } from '@angular/material/sidenav';
import { CursoService } from '../../services/curso.service';
import { MatTable, MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import Aluno from '../../models/Aluno';
import { AlunoService } from '../../services/aluno.service';
import { AlunoDialogComponent } from '../../shared/aluno-dialog/aluno-dialog.component';
import { ConfirmDialogComponent } from '../../shared/confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-alunos',
  imports: [
    MatSidenavModule,
    MatTableModule,
    MatIconModule,
    MatButtonModule,
    MatDialogModule,
  ],
  templateUrl: './alunos.component.html',
  styleUrl: './alunos.component.css',
})
export class AlunosComponent {
  alunos: Aluno[] = [];
  displayedColumns: string[] = ['matricula', 'nome', 'actions'];

  constructor(
    public alunoServices: AlunoService,
    public dialog: MatDialog,
    private router: Router
  ) {
    this.alunoServices.listarAlunos().subscribe((data) => (this.alunos = data));
  }

  openDialog(aluno: Aluno | null) {
    const dialogRef = this.dialog.open(AlunoDialogComponent, {
      width: '250px',
      data:
        aluno != null
          ? aluno
          : {
              id: '',
              nome: '',
            },
    });

    dialogRef.afterClosed().subscribe((result) => {
      this.alunoServices.cadastrarAluno(result).subscribe((data) => {
        this.alunoServices
          .listarAlunos()
          .subscribe((data) => (this.alunos = data));
      });
    });
  }

  deletarAluno(aluno: Aluno) {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      data: {
        message: `Deseja realmente deletar o aluno ${aluno.nome}?`,
      },
    });
  
    dialogRef.afterClosed().subscribe((confirmado) => {
      if (confirmado) {
        this.alunoServices.deletarAluno(aluno.id).subscribe(() => {
          this.alunoServices.listarAlunos().subscribe((data) => (this.alunos = data));
        });
      }
    });
  }
}
