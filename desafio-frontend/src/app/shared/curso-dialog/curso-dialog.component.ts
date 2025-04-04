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
import { FormsModule } from '@angular/forms';
import Curso from '../../models/Curso';

@Component({
  selector: 'app-curso-dialog',
  imports: [
    MatIconModule,
    MatButtonModule,
    MatDialogModule,
    MatFormField,
    MatInputModule,
    FormsModule,
  ],
  templateUrl: './curso-dialog.component.html',
  styleUrl: './curso-dialog.component.css',
})
export class CursoDialogComponent {

  constructor(
    public dialogRef: MatDialogRef<CursoDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Curso
  ){}

  onCancel(){
    this.dialogRef.close()
  }

}
