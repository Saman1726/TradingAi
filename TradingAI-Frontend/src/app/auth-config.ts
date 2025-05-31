import { AuthConfig } from 'angular-oauth2-oidc';

export const authConfig: AuthConfig = {
  issuer: 'https://accounts.google.com',
  redirectUri: 'https://register-gharaboroun.com/login', // Redirect URI after login
  clientId: '154400877371-s68r5nv4vd1qllo5jqd0fm8ihqgfna17.apps.googleusercontent.com', // Replace with your Google Client ID
  scope: 'openid profile email', // Scopes for user information
  strictDiscoveryDocumentValidation: false,
};