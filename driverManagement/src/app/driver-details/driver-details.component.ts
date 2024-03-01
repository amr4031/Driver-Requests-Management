import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DriverService } from '../driver.service'; // Adjust path as necessary
import { Driver } from '../models/driver.model'; // Adjust path as necessary
@Component({
  selector: 'app-driver-details',
  templateUrl: './driver-details.component.html',
  styleUrls: ['./driver-details.component.css']
})
export class DriverDetailsComponent {
  driver: Driver | undefined;

  

  constructor(
    private route: ActivatedRoute,
    private driverService: DriverService,
    private router: Router
  ) { }



  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.driverService.getDriverById(+id).subscribe({
        next: (driver) => this.driver = driver,
        error: (err) => console.error(err),
      });
    }
  }

  updateDriver(): void {
    if (this.driver) {
      this.driverService.updateDriver(this.driver).subscribe({
        next: () => this.router.navigate(['/drivers-list']),
        error: (err) => console.error(err),
      });
    }
  }

}
