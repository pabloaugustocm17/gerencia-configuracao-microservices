import { Component, OnInit, inject } from '@angular/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MAT_DATE_LOCALE } from '@angular/material/core';
import { provideNativeDateAdapter } from '@angular/material/core';
import {MatSnackBar} from '@angular/material/snack-bar';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { BookService } from '../../shared/services/book.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-book-registration',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    MatButtonModule,
  ],
  providers: [
    provideNativeDateAdapter(),
    { provide: MAT_DATE_LOCALE, useValue: 'pt-BR' },
  ],
  templateUrl: './book-registration.component.html',
  styleUrl: './book-registration.component.css',
})
export class BookRegistrationComponent implements OnInit {
  bookService = inject(BookService);
  form!: FormGroup;
  snackBarDuration = 3000;

  constructor(private _snackBar: MatSnackBar,  private router: Router) {}

  ngOnInit(): void {
    this.form = new FormGroup({
      name: new FormControl<string>('', {
        nonNullable: true,
        validators: Validators.required,
      }),
      author: new FormControl<string>('', {
        nonNullable: true,
        validators: Validators.required,
      }),
      launch_date: new FormControl<Date>(new Date(), {
        nonNullable: true,
        validators: Validators.required,
      }),
      description: new FormControl<string>('', {
        nonNullable: true,
        validators: Validators.required,
      }),
      price: new FormControl<number>(0, {
        nonNullable: true,
        validators: Validators.required,
      }),
    });
  }

  onSubmit() {
    try {
      if (!this.form.invalid) {
        this.bookService
        .createBook(this.form.value)
        .subscribe({
          next: (response: any) => this.openSnackBar(response.message, 'Fechar'),
          error: (error: any) => this.openSnackBar(error.error.message, 'Fechar'),
          complete: () => {
            this.form.reset();
            setTimeout(() => this.router.navigate(['/']), this.snackBarDuration);
          },
        });
      }
    } catch (error) {
      console.error(error);
    }
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, { duration: this.snackBarDuration });
  }
}
