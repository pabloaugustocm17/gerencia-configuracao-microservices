import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'book-registration',
    loadComponent: () =>
      import('./pages/book-registration/book-registration.component').then(
        (m) => m.BookRegistrationComponent
      ),
  },
  {
    path: 'book-view',
    loadComponent: () =>
      import('./features/book-view/book-view.component').then(
        (m) => m.BookViewComponent
      ),
  },
];
