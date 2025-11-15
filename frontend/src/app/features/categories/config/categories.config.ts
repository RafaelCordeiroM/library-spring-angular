import { CrudConfig } from '../../../core/models/crud-config.interface';
import { CategoryService } from '../../../core/services/category.service';

export const CATEGORIES_CONFIG: CrudConfig = {
  title: 'Categories',
  newLabel: 'Add Category',
  route: '/categories',
  displayField: 'name',
  service: CategoryService,

  columns: [
    { key: 'name', header: 'Name' },
    { key: 'description', header: 'Description' }
  ],

  formFields: [
    { key: 'name', label: 'Name', type: 'text', required: true },
    { key: 'description', label: 'Description', type: 'text' }
  ]
};