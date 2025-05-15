import { ComponentFixture, TestBed } from '@angular/core/testing';
import { LoginComponent } from './login.component';
import { OAuthService } from 'angular-oauth2-oidc';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  const mockOAuthService = {
    configure: jasmine.createSpy('configure'), // Mock configure method
    loadDiscoveryDocumentAndTryLogin: jasmine.createSpy('loadDiscoveryDocumentAndTryLogin'), // Mock this method
    initLoginFlow: jasmine.createSpy('initLoginFlow'),
    logOut: jasmine.createSpy('logOut'),
    hasValidAccessToken: jasmine.createSpy('hasValidAccessToken').and.returnValue(true),
    getIdentityClaims: jasmine.createSpy('getIdentityClaims').and.returnValue({ name: 'Test User' })
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LoginComponent],
      providers: [
        { provide: OAuthService, useValue: mockOAuthService }
      ]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
