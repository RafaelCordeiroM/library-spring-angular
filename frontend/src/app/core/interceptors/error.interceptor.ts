// error.interceptor.ts
import {
  HttpRequest,
  HttpHandlerFn,
  HttpEvent,
  HttpErrorResponse
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { inject } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

// ← Agora é uma FUNÇÃO, não classe!
export function errorInterceptor(req: HttpRequest<unknown>, next: HttpHandlerFn): Observable<HttpEvent<unknown>> {
  const toastr = inject(ToastrService); // ← Injeta aqui (funciona em Angular 14+)

  return next(req).pipe(
    catchError((err: HttpErrorResponse) => {
      let message = 'Ocorreu um erro inesperado';

      if (err.status === 404) message = 'Recurso não encontrado';
      else if (err.status === 500) message = 'Erro interno do servidor';
      else if (err.error?.message) message = err.error.message;
      else if (err.message) message = err.message;

      toastr.error(message, 'Erro');
      return throwError(() => err);
    })
  );
}