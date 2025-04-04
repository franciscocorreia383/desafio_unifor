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
import Aluno from '../../models/Aluno';

@Component({
  selector: 'app-aluno-dialog',
  imports: [
    MatIconModule,
    MatButtonModule,
    MatDialogModule,
    MatFormField,
    MatInputModule,
    FormsModule,
  ],
  templateUrl: './aluno-dialog.component.html',
  styleUrl: './aluno-dialog.component.css',
})
export class AlunoDialogComponent {
  constructor(
    public dialogRef: MatDialogRef<AlunoDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Aluno
  ) {}

  onCancel(){
    this.dialogRef.close()
  }
}
