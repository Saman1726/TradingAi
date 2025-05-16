export const environment = {
  production: true,
  apiUrl: 'https://api.tradingai.com', // Production API URL
  oauth2: {
    redirectUri: 'http://localhost:8080/login/oauth2/code/google',
    scope: 'read write'
  }
};