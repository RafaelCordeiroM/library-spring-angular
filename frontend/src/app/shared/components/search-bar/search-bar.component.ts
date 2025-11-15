import { Component, Output, EventEmitter } from '@angular/core';
import { debounceTime, distinctUntilChanged } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { MatFormField, MatLabel } from "@angular/material/form-field";
import { MatIcon } from "@angular/material/icon";

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.scss'],
  imports: [MatFormField, MatLabel, MatIcon]
})
export class SearchBarComponent {
  @Output() search = new EventEmitter<string>();
  onSearch$ = new Subject<string>();

  constructor() {
    this.onSearch$.pipe(
      debounceTime(300),
      distinctUntilChanged()
    ).subscribe(term => this.search.emit(term));
  }
}