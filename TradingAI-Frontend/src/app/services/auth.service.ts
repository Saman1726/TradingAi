import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { environment } from "../../environments/environment";

@Injectable({
  providedIn: "root",
})
export class AuthService {
  private readonly userUrl = `${environment.apiUrl}/public/login`;

  constructor(private readonly http: HttpClient) {}

  getUser(token: string): Observable<any> {
    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`,
    });
    return this.http.get(this.userUrl, { headers });
  }

  loginWithGoogle(idToken: string): Observable<any> {
    const headers = new HttpHeaders({
      Authorization: `Bearer ${idToken}`,
    });
    return this.http.post(this.userUrl, {}, { headers });
  }
}
