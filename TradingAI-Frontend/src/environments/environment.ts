export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080/api', // Base URL for the API
  oauth2: {
    scope: 'read write',
    tokenEndpoint: 'https://oauth2.googleapis.com/token',
    authorizationEndpoint: 'https://accounts.google.com/o/oauth2/auth',
  }
};