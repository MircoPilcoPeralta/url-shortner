import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable()
export class ApiService {
  private apiUrl = 'http://localhost:8085/shortener-jax-ws/rest/url';

  constructor(private http: HttpClient) {}

  buildGenerateShortLinkObservable(body: any): Observable<any> {
    const endpoint = `${this.apiUrl}/generate`;

    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });

    return this.http
      .post(endpoint, body, { headers })
      .pipe(catchError(this.handleError));
  }

  private handleError(error: any) {
    console.error('Ocurrió un error:', error);
    return [];
  }
}
