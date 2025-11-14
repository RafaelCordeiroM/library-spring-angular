import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Book } from '../models/book.model';
import { environment } from '../../../environments/environment';

@Injectable({ providedIn: 'root' })
export class BookService {
  private apiUrl = `${environment.apiUrl}/books`;

  constructor(private http: HttpClient) {}

  getAll(): Observable<Book[]> {
    return this.http.get<Book[]>(this.apiUrl);
  }

  getById(id: number): Observable<Book> {
    return this.http.get<Book>(`${this.apiUrl}/${id}`);
  }

  create(Book: Book): Observable<Book> {
    return this.http.post<Book>(this.apiUrl, Book);
  }

  update(id: number, Book: Partial<Book>): Observable<Book> {
    return this.http.put<Book>(`${this.apiUrl}/${id}`, Book);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  search(term: string): Observable<Book[]> {
    return this.http.get<Book[]>(`${this.apiUrl}?q=${encodeURIComponent(term)}`);
  }
}