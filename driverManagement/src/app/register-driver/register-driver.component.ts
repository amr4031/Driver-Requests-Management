import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DriverService } from '../driver.service';
import { Driver } from '../models/driver.model';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-register-driver',
  templateUrl: './register-driver.component.html',
  styleUrls: ['./register-driver.component.css']
})
export class RegisterDriverComponent implements OnInit {

  driverForm!: FormGroup;
  minDate = new Date(); 
  constructor(private fb: FormBuilder,
     private driverService: DriverService,
     private toastr: ToastrService
     ) { }

  ngOnInit(): void {
    this.driverForm = this.fb.group({
      driverName: ['', Validators.required],
      driverEmail: ['', [Validators.required, Validators.email]],
      driverMobile: ['', Validators.required],
      videoConferenceMeetingDate: ['', Validators.required]
      // Add other fields as necessary
    });
    this.minDate.setHours(0, 0, 0, 0);
  }

  registerDriver(): void {
    if (this.driverForm.valid) {
      const formValue = { ...this.driverForm.value };
      if (formValue.videoConferenceMeetingDate) {
        // If the backend expects an ISO 8601 string for the timestamp
        formValue.videoConferenceMeetingDate = new Date(formValue.videoConferenceMeetingDate).toISOString();
      }
      this.driverService.registerDriver(this.driverForm.value).subscribe({
        next: (driver) => {
          console.log(driver);
          this.toastr.success('Driver registered successfully!','Success', {
            timeOut: 3000,
          });
          // Redirect or perform other actions
        },
        error: (error) => {
          let errorMessage = 'There was an error processing your request'; // Default message
          if (error.error instanceof ErrorEvent) {
            // Client-side or network error occurred
            console.error('An error occurred:', error.error.message);
          } else {

            errorMessage = error.error.error || error.message || `Server returned code ${error.status}`;
            debugger
          }
          this.toastr.error(errorMessage, 'Error');
        }
      });
    }
    
  }
  private formatDate(date: Date): string {
    return new Date(date).toISOString().split('T')[0]; // Formats the date as 'YYYY-MM-DD'
  }
}
