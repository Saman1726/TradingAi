import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { AuthGuard } from './auth.guard';
import { OAuthService } from 'angular-oauth2-oidc';
import { Router } from '@angular/router';

describe('AuthGuard', () => {
  let guard: AuthGuard;
  let router: Router;

  // Create a mock OAuthService
  const mockOAuthService = {
    hasValidAccessToken: jasmine.createSpy('hasValidAccessToken').and.returnValue(true)
  };

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule], // Import RouterTestingModule for routing-related dependencies
      providers: [
        AuthGuard,
        { provide: OAuthService, useValue: mockOAuthService } // Provide the mock service
      ]
    });
    guard = TestBed.inject(AuthGuard);
    router = TestBed.inject(Router);
    spyOn(router, 'navigate'); // Spy on the router's navigate method
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });

  it('should return true if the user is logged in', () => {
    mockOAuthService.hasValidAccessToken.and.returnValue(true); // Mock user is logged in
    expect(guard.canActivate()).toBeTrue();
    expect(mockOAuthService.hasValidAccessToken).toHaveBeenCalled();
  });

  it('should return false and navigate to /login if the user is not logged in', () => {
    mockOAuthService.hasValidAccessToken.and.returnValue(false); // Mock user is not logged in
    expect(guard.canActivate()).toBeFalse();
    expect(router.navigate).toHaveBeenCalledWith(['/login']);
  });
});
