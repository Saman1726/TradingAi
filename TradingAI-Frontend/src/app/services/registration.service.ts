import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment'; 

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {
  private readonly url = `${environment.apiUrl}/registration`;
  constructor(private readonly http: HttpClient) {}

  // Method to send registration data to the backend with token
  registerUser(data: any, token: string): Observable<any> {
    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`
    });
    return this.http.post(this.url, data, { headers });
  }
}
