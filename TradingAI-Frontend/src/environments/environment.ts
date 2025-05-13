export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080/api', // Base URL for the API
  oauth2: {
    clientId: 'your-client-id',
    clientSecret: 'your-client-secret',
    redirectUri: 'http://localhost:4200/callback',
    scope: 'read write',
    tokenEndpoint: 'http://localhost:8080/oauth/token',
    authorizationEndpoint: 'http://localhost:8080/oauth/authorize',
  }
};