import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GenericListComponent } from '../../shared/components/generic-list/generic-list.component';
import { GenericFormComponent } from '../../shared/components/generic-form/generic-form.component';
import { BOOKS_CONFIG } from './config/books.config';

export const BOOKS_ROUTES: Routes = [
  { path: '', component: GenericListComponent, data: { config: BOOKS_CONFIG } },
  { path: 'new', component: GenericFormComponent, data: { config: BOOKS_CONFIG } },
  { path: ':id', component: GenericFormComponent, data: { config: BOOKS_CONFIG } }
];

@NgModule({
  imports: [RouterModule.forChild(BOOKS_ROUTES)],
  exports: [RouterModule]
})
export class BooksRoutingModule { }