import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  constructor(private router: Router) {}

  navigateToListaDeLivros() {
    this.router.navigate(['/book-view']);
  }

  navigateToCriarUmLivro() {
    this.router.navigate(['/book-registration']);
  }
}
