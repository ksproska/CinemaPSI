import {Component, Input, OnInit} from '@angular/core';
import {NgOptimizedImage} from "@angular/common";

@Component({
  selector: 'app-movie-info',
  standalone: true,
  imports: [
    NgOptimizedImage
  ],
  templateUrl: './movie-info.component.html',
  styleUrl: './movie-info.component.css',
})
export class MovieInfoComponent{
  @Input() description!: string
  @Input() title!: string
  @Input() image!: string

}
