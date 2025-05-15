export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080/api', // Base URL for the API
  oauth2: {
    clientId: '154400877371-s68r5nv4vd1qllo5jqd0fm8ihqgfna17.apps.googleusercontent.com',
    clientSecret: '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',
    redirectUri: 'http://localhost:8080/login/oauth2/code/google',
    scope: 'read write',
    tokenEndpoint: 'https://oauth2.googleapis.com/token',
    authorizationEndpoint: 'https://accounts.google.com/o/oauth2/auth',
  }
};