import { Author } from "./author.model";
import { Category } from "./category.model";


export interface Book {
  id?: number;
  title: string;
  publicationYear?: number;
  isbn?: string;
  authors?: Array<Author>;
  category?: Category
  edition?: string;
  pages?: number;
  coverImageUrl?: string;
  totalCopies?: number;
  availableCopies?: number;
}