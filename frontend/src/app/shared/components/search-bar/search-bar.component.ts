import { Component, Output, EventEmitter } from '@angular/core';
import { debounceTime, distinctUntilChanged } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input'; // ESSENCIAL!
import { MatIconModule } from '@angular/material/icon';   // para mat-icon
import { FormsModule } from '@angular/forms';             // para ngModel

@Component({
  selector: 'app-search-bar',
  standalone: true,
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.scss'],
  imports: [
    MatFormFieldModule,
    MatInputModule,    
    MatIconModule,      
    FormsModule         
  ]
})
export class SearchBarComponent {
  @Output() search = new EventEmitter<string>();

  // Usado com ngModel
  searchTerm = '';

  // Subject para debounce
  private searchSubject = new Subject<string>();

  constructor() {
    this.searchSubject.pipe(
      debounceTime(300),
      distinctUntilChanged()
    ).subscribe(term => {
      this.search.emit(term);
    });
  }

  // Chamado pelo (input)
  onInput(term: string) {
    this.searchSubject.next(term);
  }
}