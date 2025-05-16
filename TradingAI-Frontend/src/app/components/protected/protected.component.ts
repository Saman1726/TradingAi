import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegistrationService } from '../../services/registration.service';

@Component({
  selector: 'app-protected',
  templateUrl: './protected.component.html',
  styleUrls: ['./protected.component.scss']
})
export class ProtectedComponent {
  registrationForm: FormGroup;

  constructor(private fb: FormBuilder, private registrationService: RegistrationService) {
    this.registrationForm = this.fb.group({
      firstName: ['', [Validators.required]],
      lastName: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', [Validators.required, Validators.pattern(/^\d{10}$/)]],
      address: this.fb.group({
        street: ['', [Validators.required]],
        city: ['', [Validators.required]],
        state: ['', [Validators.required]],
        zip: ['', [Validators.required, Validators.pattern(/^\d{5}$/)]]
      })
    });
  }

  onSubmit(): void {
    if (this.registrationForm.valid) {
      this.registrationService.registerUser(this.registrationForm.value).subscribe({
        next: (response) => {
          console.log('Registration successful:', response);
          alert('Registration successful!');
        },
        error: (error) => {
          console.error('Error during registration:', error);
          alert('Registration failed. Please try again.');
        }
      });
    } else {
      console.log('Form is invalid');
    }
  }
}
