# TradingAI Frontend

## Overview
TradingAI-Frontend is an Angular application designed to provide a user-friendly interface for interacting with the TradingAI backend services. This project utilizes TypeScript, OAuth2 for authentication, and RESTful services for data communication.

## Features
- Responsive Navbar for navigation
- Authentication service for user login and management
- Environment-specific configurations for development and production
- Docker support for easy deployment

## Getting Started

### Prerequisites
- Node.js (version 14 or later)
- Angular CLI (version 12 or later)
- Docker (for containerization)

### Installation
1. Clone the repository:
   ```
   git clone <repository-url>
   cd TradingAI-Frontend
   ```

2. Install the dependencies:
   ```
   npm install
   ```

### Running the Application
To run the application in development mode, use the following command:
```
ng serve
```
Navigate to `http://localhost:4200/` in your browser.

### Building for Production
To build the application for production, run:
```
ng build --prod
```
The output will be stored in the `dist/` directory.

### Docker
To build and run the application using Docker, use the following commands:
```
docker build -t tradingai-frontend .
docker run -p 80:80 tradingai-frontend
```

### Contributing
Contributions are welcome! Please open an issue or submit a pull request for any enhancements or bug fixes.

### License
This project is licensed under the MIT License. See the LICENSE file for details.