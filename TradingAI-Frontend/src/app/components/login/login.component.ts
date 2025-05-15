import { Component } from '@angular/core';
import { OAuthService } from 'angular-oauth2-oidc';
import { authConfig } from '../../auth-config';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  constructor(private oauthService: OAuthService) {
    // Configure the OAuthService with the authConfig
    this.oauthService.configure(authConfig);
    this.oauthService.loadDiscoveryDocumentAndTryLogin();
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
}
