import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: '', 
        redirectTo: '/books', 
        pathMatch: 'full' 
    },
    {
        path: 'books',
        loadChildren: () => import('./features/books/books.module').then(m => m.BooksModule)
    },
    {
        path: 'authors',
        loadChildren: () => import('./features/authors/authors.module').then(m => m.AuthorsModule)
    },
        {
        path: 'categories',
        loadChildren: () => import('./features/categories/categories.module').then(m => m.CategoriesModule)
    }

];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: []
})
export class AppRoutingModule {}
