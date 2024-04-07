import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'book-register',
    loadComponent: () =>
      import('./features/book-registration/book-registration.component').then(
        (m) => m.BookRegistrationComponent
      ),
  },
];
