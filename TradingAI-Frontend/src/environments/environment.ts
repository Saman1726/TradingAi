export const environment = {
  production: false,
  apiBaseUrl: 'https://register-gharaboroun.com/api', // Base URL for the API
  apiUrl: '/api', // Base URL for the API
  oauth2: {
    scope: 'read write',
    tokenEndpoint: 'https://oauth2.googleapis.com/token',
    authorizationEndpoint: 'https://accounts.google.com/o/oauth2/auth',
    clientId: '154400877371-s68r5nv4vd1qllo5jqd0fm8ihqgfna17.apps.googleusercontent.com', 
    clientSecret: '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', // Replace with your actual client secret
  }
};