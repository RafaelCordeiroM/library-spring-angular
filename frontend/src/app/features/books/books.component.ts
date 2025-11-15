import { Component } from "@angular/core";
import { GenericListComponent } from "../../shared/components/generic-list/generic-list.component";
import { BOOKS_CONFIG } from "./config/books.config";
import { CRUD_CONFIG } from "../../core/models/crud-config.interface";

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.scss'],
  imports: [GenericListComponent],
    providers: [
        { provide: CRUD_CONFIG, useValue: BOOKS_CONFIG }
    ]
})
export class BooksComponent {}