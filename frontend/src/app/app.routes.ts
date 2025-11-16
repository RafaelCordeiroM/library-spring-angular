import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BooksComponent } from './features/books/books.component';

export const routes: Routes = [
  { path: '', redirectTo: '/books', pathMatch: 'full' },

  {
    path: 'books',
    loadComponent: () => import('./features/books/books.component')
      .then(m => m.BooksComponent),

    children: [
      {
        path: '',
        component: BooksComponent,
        data: { mode: 'list' }
      },
      {
        path: 'new',
        component: BooksComponent,
        data: { mode: 'form', isNew: true }
      },
      {
        path: ':id',
        component: BooksComponent,
        data: { mode: 'form' }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}