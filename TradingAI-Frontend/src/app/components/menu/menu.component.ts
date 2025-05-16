import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { OAuthService } from 'angular-oauth2-oidc';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent {
  @Input() isMenuOpen: boolean = false; // Receives the menu state from the parent
  @Output() toggleMenu = new EventEmitter<void>(); // Emits an event to toggle the menu

  constructor(private oauthService: OAuthService, private router: Router) {}

  // Toggles the menu open/close state
  onToggleMenu(): void {
    this.toggleMenu.emit();
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

  // Initiates the login flow
  login(): void {
    this.oauthService.initLoginFlow();
  }

  // Navigate to a specific route
  navigateTo(route: string): void {
    this.router.navigate([route]);
    this.onToggleMenu(); // Close the menu after navigation
  }

  // Navigate to an external URL
  navigateToExternal(url: string): void {
    window.open(url, '_blank'); // Open the URL in a new tab
    this.onToggleMenu(); // Close the menu after navigation
  }
}
