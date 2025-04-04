import { Component, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { MatSidenavModule } from '@angular/material/sidenav';
import { CursoService } from '../../services/curso.service';
import { MatTable, MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';

import { CursoDialogComponent } from '../../shared/curso-dialog/curso-dialog.component';
import Curso from '../../models/Curso';
import { idText } from 'typescript';

@Component({
  selector: 'app-cursos',
  imports: [
    MatSidenavModule,
    MatTableModule,
    MatIconModule,
    MatButtonModule,
    MatDialogModule,
  ],
  templateUrl: './cursos.component.html',
  styleUrl: './cursos.component.css',
})
export class CursosComponent {
  @ViewChild(MatTable)
  table!: MatTable<any>

  cursos: Curso[] = [];
  displayedColumns: string[] = ['id', 'nome', 'actions'];

  constructor(public cursoServices: CursoService, public dialog: MatDialog, private router: Router) {
    this.cursoServices.listarCursos().subscribe((data) => (this.cursos = data));
  }

  verMatriculados(curso: Curso) {
    this.router.navigate(['/matriculas', curso.id])
  }

  openDialog(curso: Curso | null) {
    const dialogRef = this.dialog.open(CursoDialogComponent, {
      width: '250px',
      data:
        curso != null
          ? curso
          : {
              id: '',
              nome: '',
            },
    });

    dialogRef.afterClosed().subscribe((result) => {
      try {
        this.cursoServices.cadastrarCurso(result).subscribe( data => {
          this.cursoServices.listarCursos().subscribe((data) => (this.cursos = data));
        })
      } catch (error) {
        alert(error)
      }
    });
  }
}
