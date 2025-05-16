import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {
  private apiUrl = 'http://localhost:3000/api/registration'; // Replace with your backend API URL

  constructor(private http: HttpClient) {}

  // Method to send registration data to the backend
  registerUser(data: any): Observable<any> {
    return this.http.post(this.apiUrl, data);
  }
}
