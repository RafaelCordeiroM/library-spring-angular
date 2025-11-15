import { CrudConfig } from '../../../core/models/crud-config.interface';
import { AuthorService } from '../../../core/services/author.service';

export const AUTHORS_CONFIG: CrudConfig = {
  title: 'Authors',
  newLabel: 'Add Author',
  route: '/authors',
  displayField: 'name',
  service: AuthorService,

  columns: [
    { key: 'name', header: 'Name' },
    { key: 'nationality', header: 'Nationality' },
    { key: 'birthYear', header: 'Birth Year' }
  ],

  formFields: [
    { key: 'name', label: 'Name', type: 'text', required: true },
    { key: 'nationality', label: 'Nationality', type: 'text' },
    { key: 'birthYear', label: 'Birth Year', type: 'number' }
  ]
};