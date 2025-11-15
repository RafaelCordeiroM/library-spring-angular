// src/app/features/categories/categories.routes.ts
import { Routes } from '@angular/router';
import { GenericListComponent } from '../../shared/components/generic-list/generic-list.component';
import { GenericFormComponent } from '../../shared/components/generic-form/generic-form.component';
import { CATEGORIES_CONFIG } from './config/categories.config';

export const CATEGORIES_ROUTES: Routes = [
  { path: '', component: GenericListComponent, data: { config: CATEGORIES_CONFIG } },
  { path: 'new', component: GenericFormComponent, data: { config: CATEGORIES_CONFIG } },
  { path: ':id', component: GenericFormComponent, data: { config: CATEGORIES_CONFIG } }
];