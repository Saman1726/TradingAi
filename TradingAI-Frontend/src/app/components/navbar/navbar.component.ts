import { Component } from '@angular/core';
import { OAuthService } from 'angular-oauth2-oidc';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {
  isMenuOpen: boolean = false; // Tracks the state of the menu

  constructor(private oauthService: OAuthService) {}

  // Toggles the menu open/close state
  toggleMenu(): void {
    this.isMenuOpen = !this.isMenuOpen;
  }

  // Checks if the user is logged in
  get isLoggedIn(): boolean {
    return this.oauthService.hasValidAccessToken();
  }

  // Retrieves the user's name from the ID token claims
  get userName(): string | null {
    const claims = this.oauthService.getIdentityClaims();
    return claims ? claims['name'] : null;
  }

  // Logs the user out
  logout(): void {
    this.oauthService.logOut();
  }
}