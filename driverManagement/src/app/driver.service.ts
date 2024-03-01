import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Driver } from './models/driver.model';// You need to define this model based on your backend entity

@Injectable({
  providedIn: 'root'
})
export class DriverService {

  private baseUrl = 'http://localhost:8080/drivers'; // Adjust this URL to your backend's URL
  private apiUrl = 'http://localhost:8080/drivers'; // Adjust based on your actual backend URL


  constructor(private http: HttpClient) { }

  getAllDrivers(): Observable<Driver[]> {
    return this.http.get<Driver[]>(this.apiUrl);
  }

  getDriverById(id: number): Observable<Driver> {
    return this.http.get<Driver>(`${this.apiUrl}/${id}`);
  }
  updateDriver(driver: Driver): Observable<Driver> {
    return this.http.put<Driver>(this.apiUrl, driver);
  }

  registerDriver(driver: Driver): Observable<Driver> {
    return this.http.post<Driver>(`${this.baseUrl}`, driver);
  }
}
