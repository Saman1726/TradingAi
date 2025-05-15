export const environment = {
  production: true,
  apiUrl: 'https://api.tradingai.com', // Production API URL
  oauth2: {
    clientId: '154400877371-s68r5nv4vd1qllo5jqd0fm8ihqgfna17.apps.googleusercontent.com',
    clientSecret: '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',
    redirectUri: 'http://localhost:8080/login/oauth2/code/google',
    scope: 'read write'
  }
};