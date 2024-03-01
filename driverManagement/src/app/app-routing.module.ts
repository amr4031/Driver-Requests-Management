import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
// Import the components
import { RegisterDriverComponent } from './register-driver/register-driver.component';
import { DriversListComponent } from './drivers-list/drivers-list.component';
import { DriverDetailsComponent } from './driver-details/driver-details.component';

const routes: Routes = [
  { path: 'register-driver', component: RegisterDriverComponent },
  { path: 'drivers-list', component: DriversListComponent },
  { path: 'driver-details/:id', component: DriverDetailsComponent },
  { path: '', redirectTo: '/register-driver', pathMatch: 'full' }, // redirect to `RegisterDriverComponent` as a default
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
