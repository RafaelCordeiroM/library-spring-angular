import { Component } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-home',
  imports: [FormsModule, CommonModule],
  standalone: true,
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {
  title:string = "Title testhg HOME";
  count:number = 0;

  textDisplay:string = "";
  textInputHome:string = "";

  onButtonClick(){
    this.count --;
  }
  updateText(){
    this.textDisplay = this.textInputHome
  }
}
