import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthComponent } from './components/auth/auth.component';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { AuthGuard } from './shared/guards/auth-guard.guard';
import { UserTableComponent } from './components/user-table/user-table.component';
import { PropertyTableComponent } from './components/property-table/property-table.component';
import { LandingAdminComponent } from './components/landing-admin/landing-admin.component';

const routes: Routes = [
  { path: 'login', component: AuthComponent },
  { path: 'home', canActivate: [AuthGuard], component: LandingAdminComponent },
  {path: 'admin', component: LandingPageComponent,canActivate: [AuthGuard],
    children: [
      { path: 'property', component: PropertyTableComponent },
      { path: 'user', component: UserTableComponent }
    ]
  },
  { path: '', redirectTo: 'login', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
