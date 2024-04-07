import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { BookPayload } from '../interfaces/payload-book.interface';

@Injectable({
  providedIn: 'root',
})
export class BookService {
  httpClient = inject(HttpClient);

  createBook(payload: BookPayload) {
    return this.httpClient.post<BookPayload>('/api/book', payload);
  }
}
