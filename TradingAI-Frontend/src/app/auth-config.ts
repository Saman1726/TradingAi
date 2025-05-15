import { AuthConfig } from 'angular-oauth2-oidc';

export const authConfig: AuthConfig = {
  issuer: 'https://accounts.google.com',
  redirectUri: window.location.origin, // Redirect URI after login
  clientId: '154400877371-s68r5nv4vd1qllo5jqd0fm8ihqgfna17.apps.googleusercontent.com', // Replace with your Google Client ID
  scope: 'openid profile email', // Scopes for user information
  strictDiscoveryDocumentValidation: false,
};