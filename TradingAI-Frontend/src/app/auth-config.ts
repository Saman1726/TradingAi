import { AuthConfig } from 'angular-oauth2-oidc';

export const authConfig: AuthConfig = {
  issuer: 'https://accounts.google.com',
  redirectUri: window.location.origin, // Redirect URI after login
  clientId: 'your_google_client_id', // Replace with your Google Client ID
  scope: 'openid profile email', // Scopes for user information
  strictDiscoveryDocumentValidation: false,
};