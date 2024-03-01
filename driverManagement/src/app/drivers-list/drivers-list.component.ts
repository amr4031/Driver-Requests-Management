// drivers-list.component.ts
import { Component, OnInit } from '@angular/core';
import { DriverService } from '../driver.service';
import { Driver } from '../models/driver.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-drivers-list',
  templateUrl: './drivers-list.component.html',
  styleUrls: ['./drivers-list.component.css']
})
export class DriversListComponent implements OnInit {
  drivers: Driver[] = [];

  constructor(private driverService: DriverService,private router: Router) { }

  ngOnInit(): void {
    this.fetchDrivers();
  }

  fetchDrivers(): void {
    this.driverService.getAllDrivers().subscribe({
      next: (data) => this.drivers = data,
      error: (err) => console.error(err)
    });
  }

  getDriverById(id?: number): void {
    if (id !== undefined) {
      // Implement fetching the driver by ID
      console.log('Fetch driver details for ID:', id);
      this.router.navigate(['/driver-details', id]);
    } else {
      console.error('Driver ID is undefined');
    }
  }
}
