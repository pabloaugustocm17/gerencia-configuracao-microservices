import { Component, OnInit, inject } from '@angular/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MAT_DATE_LOCALE } from '@angular/material/core';
import { provideNativeDateAdapter } from '@angular/material/core';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { BookService } from '../../shared/services/book.service';

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
        const { name, author, description, launch_date, price } = this.form.value;
        this.bookService
          .createBook({
            name,
            author,
            description,
            launch_date,
            price,
          })
          .subscribe(() => console.log(this.form.value));
        ;
      }
    } catch (error) {
      console.error(error);
    }
  }
}
