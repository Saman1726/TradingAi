import { Component } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { RegistrationService } from "../../services/registration.service";
import { OAuthService } from "angular-oauth2-oidc";

@Component({
  selector: "app-protected",
  templateUrl: "./protected.component.html",
  styleUrls: ["./protected.component.scss"],
})
export class ProtectedComponent {
  registrationForm: FormGroup;

  constructor(
    private readonly fb: FormBuilder,
    private readonly registrationService: RegistrationService,
    private readonly oauthService: OAuthService // Inject OAuthService
  ) {
    this.registrationForm = this.fb.group({
      firstName: ["", [Validators.required]],
      lastName: ["", [Validators.required]],
      email: ["", [Validators.required, Validators.email]],
      phone: ["", [Validators.required]],
      street: ["", [Validators.required]],
      city: ["", [Validators.required]],
      state: ["", [Validators.required]],
      zip: ["", [Validators.required]],
    });
  }

  onSubmit(): void {
    if (this.registrationForm.valid) {
      // Check if OAuth2 is used and get the token accordingly
      if (this.oauthService.hasValidAccessToken()) {
        let token = this.oauthService.getIdToken();

        this.registrationService
          .registerUser(this.registrationForm.value, token)
          .subscribe({
            next: (response) => {
              console.log("Registration successful:", response);
              alert("Registration successful!");
            },
            error: (error) => {
              console.error("Error during registration:", error);
              alert("Registration failed. Please try again.");
            },
          });
      } else {
        console.error("No valid access token found.");
        alert("You are not authenticated. Please log in.");
      }
    } else {
      console.log("Form is invalid");
    }
  }
}
