import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './shared/guards/auth-guard.guard';
import { LandingAdminComponent } from './components/admin/landing-admin/landing-admin.component';
import { AuthComponent } from './shared/auth/auth.component';
import { LandingPageComponent } from './components/admin/landing-page/landing-page.component';
import { PropertyTableComponent } from './components/admin/property-table/property-table.component';
import { UserTableComponent } from './components/admin/user-table/user-table.component';

const routes: Routes = [
  { path: 'login', component: AuthComponent },
  { path: 'home', canActivate: [AuthGuard], component: LandingAdminComponent },
  {path: 'admin', component: LandingPageComponent,canActivate: [AuthGuard],
    children: [
      { path: 'property', component: PropertyTableComponent },
      { path: 'user', component: UserTableComponent}
    ]
  },
  { path: '', redirectTo: 'login', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
