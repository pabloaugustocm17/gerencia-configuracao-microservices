import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'book-registration',
    loadComponent: () =>
      import('./features/book-registration/book-registration.component').then(
        (m) => m.BookRegistrationComponent
      ),
  },
];
