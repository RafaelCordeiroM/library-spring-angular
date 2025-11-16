import { BookService } from '../../../core/services/book.service';
import { AuthorService } from '../../../core/services/author.service';
import { CategoryService } from '../../../core/services/category.service';
import { CrudConfig } from '../../../core/models/crud-config.interface';

export const BOOKS_CONFIG: CrudConfig = {
  title: 'Books',
  newLabel: 'Add Book',
  route: '/books',
  displayField: 'title',
  service: BookService,

  // === COLUMNS (LIST VIEW) ===
  columns: [
    { key: 'title', header: 'Title' },
    {
      key: 'authors',
      header: 'Authors',
      cell: (book: any) =>
        book.authors?.map((a: any) => a.name).join(', ') || '—'
    },
    {
      key: 'category',
      header: 'Category',
      cell: (book: any) => book.category?.name || '—'
    },
    { key: 'edition', header: 'Edition' },
    { key: 'publicationYear', header: 'Year' },
    { key: 'pages', header: 'Pages' },
    {
      key: 'availableCopies',
      header: 'Available',
      cell: (book: any) =>
        `${book.availableCopies ?? 0} / ${book.totalCopies ?? 0}`
    }
  ],

  // === FORM FIELDS (CREATE / EDIT) ===
  formFields: [
    {
      key: 'title',
      label: 'Title',
      type: 'text',
      required: true
    },
    {
      key: 'authors',
      label: 'Authors',
      type: 'select',
      required: true,
      multiple: true,
      optionsService: AuthorService,
      optionLabel: 'name',
      // Multiple selection will be handled in form (see note below)
    },
    {
      key: 'categoryId',
      label: 'Category',
      type: 'select',
      required: true,
      optionsService: CategoryService,
      optionLabel: 'name'
    },
    {
      key: 'edition',
      label: 'Edition',
      type: 'text'
    },
    {
      key: 'publicationYear',
      label: 'Publication Year',
      type: 'number'
    },
    {
      key: 'isbn',
      label: 'ISBN',
      type: 'text'
    },
    {
      key: 'pages',
      label: 'Pages',
      type: 'number'
    },
    {
      key: 'coverImageUrl',
      label: 'Cover Image URL',
      type: 'text'
    },
    {
      key: 'totalCopies',
      label: 'Total Copies',
      type: 'number'
    },
    {
      key: 'availableCopies',
      label: 'Available Copies',
      type: 'number'
    }
  ]
};