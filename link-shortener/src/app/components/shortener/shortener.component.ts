import { Component } from '@angular/core';
import { ApiService } from './shortener.service';
import { Observable } from 'rxjs';
import { HttpClientModule } from '@angular/common/http';
@Component({
  selector: 'app-shortener',
  standalone: true,
  imports: [HttpClientModule],
  templateUrl: './shortener.component.html',
  styleUrl: './shortener.component.scss',
  providers: [ApiService],
})
export class ShortenerComponent {
  constructor(private apiService: ApiService) {}

  results: string[] = [];

  generateShortedUrl(url: string) {
    const generateShortedLinkObservable: Observable<any> =
      this.apiService.buildGenerateShortLinkObservable({ url });

    generateShortedLinkObservable.subscribe(
      (response) => {
        this.results.push(response.data);
      },
      (error) => {
        console.error('Hubo un error al hacer la solicitud POST:', error);
      }
    );
  }
}
