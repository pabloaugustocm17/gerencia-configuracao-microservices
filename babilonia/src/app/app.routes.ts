import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'book-registration',
    loadComponent: () =>
      import('./features/book-registration/book-registration.component').then(
        (m) => m.BookRegistrationComponent
      ),
  },
  {
    path: 'home',
    loadComponent: () =>
      import('./features/home/home.component').then(
        (m) => m.HomeComponent
      ),
  },
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full' 
  },
];