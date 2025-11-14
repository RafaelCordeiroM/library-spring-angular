// src/app/core/interceptors/error.interceptor.ts
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { ToastrService } from 'ngx-toastr';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
  constructor(private toastr: ToastrService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      catchError(err => {
        let message = 'Erro desconhecido';
        if (err.status === 404) message = 'Recurso não encontrado';
        if (err.status === 500) message = 'Erro no servidor';
        if (err.error?.message) message = err.error.message;

        this.toastr.error(message);
        return throwError(() => err);
      })
    );
  }
}