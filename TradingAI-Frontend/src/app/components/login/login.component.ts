import { Component } from '@angular/core';
import { OAuthService } from 'angular-oauth2-oidc';
import { AuthService } from '../../services/auth.service';
import { authConfig } from '../../auth-config';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  user: any = null;

  constructor(
    private oauthService: OAuthService,
    private authService: AuthService
  ) {
    // Configure the OAuthService with the authConfig
    this.oauthService.configure(authConfig);
    this.oauthService.loadDiscoveryDocumentAndTryLogin();

    // Listen for successful login and fetch user
    this.oauthService.events.subscribe(e => {
      if (e.type === 'token_received') {
        this.fetchUser();
      }
    });
  }

  login() {
    // Initiates the login flow
    this.oauthService.initLoginFlow();
  }

  logout() {
    // Logs out the user
    this.oauthService.logOut();
  }

  get isLoggedIn(): boolean {
    // Checks if the user is logged in
    return this.oauthService.hasValidAccessToken();
  }

  get userName(): string | null {
    // Retrieves the user's name from the ID token claims
    const claims = this.oauthService.getIdentityClaims();
    return claims ? claims['name'] : null;
  }

  fetchUser() {
    const token = this.oauthService.getIdToken();
    if (token) {
      this.authService.loginWithGoogle(token).subscribe({
        next: (user) => this.user = user,
        error: (err) => console.error('Failed to fetch user:', err)
      });
    }
  }
}
