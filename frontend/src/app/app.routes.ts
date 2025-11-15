import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

export const routes: Routes = [
    { path: '', redirectTo: '/books', pathMatch: 'full' },
  {
    path: 'books',
    loadComponent: () => import('./features/books/books.component').then(m => m.BooksComponent)
    // loadChildren: () => import('./features/books/books.module').then(m => m.BooksModule)
  },
//   {
//     path: 'authors',
//     loadChildren: () => import('./features/authors/authors-routing.module').then(m => m.AUTHORS_ROUTES)
//   },
//   {
//     path: 'categories',
//     loadChildren: () => import('./features/categories/categories-routing.module').then(m => m.CATEGORIES_ROUTES)
//   }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {}
