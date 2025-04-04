import { Component, Inject } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import {
  MAT_DIALOG_DATA,
  MatDialogModule,
  MatDialogRef,
} from '@angular/material/dialog';
import { MatFormField } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Observable, startWith, map } from 'rxjs';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { CommonModule } from '@angular/common';

import Aluno from '../../models/Aluno';
import { AlunoService } from '../../services/aluno.service';

@Component({
  selector: 'app-aluno-curso-dialog',
  imports: [
    MatIconModule,
    MatButtonModule,
    MatDialogModule,
    MatFormField,
    MatInputModule,
    FormsModule,
    ReactiveFormsModule,
    MatAutocompleteModule,
    CommonModule,
  ],
  templateUrl: './aluno-curso-dialog.component.html',
  styleUrl: './aluno-curso-dialog.component.css',
})
export class AlunoCursoDialogComponent {
  alunos: Aluno[] = [];

  alunoCtrl = new FormControl(''); // Controlador do input
  alunosFiltrados!: Observable<Aluno[]>; // Lista filtrada
  alunoSelecionadoId: number | null = null; // ID do aluno selecionado

  constructor(
    public dialogRef: MatDialogRef<AlunoCursoDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public alunoServices: AlunoService
  ) {
    this.alunoServices.listarAlunos().subscribe((data) => {
      this.alunos = data;

      this.alunosFiltrados = this.alunoCtrl.valueChanges.pipe(
        startWith(''),
        map(value => this._filtrarAlunos(value || ''))
      );
    })
  }

  private _filtrarAlunos(value: string): Aluno[] {
    const filtro = value.toLowerCase();
    return this.alunos.filter(aluno =>
      `${aluno.matricula} - ${aluno.nome}`.toLowerCase().includes(filtro)
    );
  }

  onAlunoSelecionado(event: any): void {
    const aluno = this.alunos.find(a => `${a.matricula} - ${a.nome}` === event.option.value);
    this.alunoSelecionadoId = aluno ? aluno.id : null;
    console.log('Aluno Selecionado ID:', this.alunoSelecionadoId);
  }

  confirmarSelecao(): void {
    this.dialogRef.close(this.alunoSelecionadoId); // Retorna o objeto aluno
  }

  onCancel() {
    this.dialogRef.close();
  }
}
