// src/app/features/authors/authors.routes.ts
import { Routes } from '@angular/router';
import { GenericListComponent } from '../../shared/components/generic-list/generic-list.component';
import { GenericFormComponent } from '../../shared/components/generic-form/generic-form.component';
import { AUTHORS_CONFIG } from './config/authors.config';

export const AUTHORS_ROUTES: Routes = [
  { path: '', component: GenericListComponent, data: { config: AUTHORS_CONFIG } },
  { path: 'new', component: GenericFormComponent, data: { config: AUTHORS_CONFIG } },
  { path: ':id', component: GenericFormComponent, data: { config: AUTHORS_CONFIG } }
];