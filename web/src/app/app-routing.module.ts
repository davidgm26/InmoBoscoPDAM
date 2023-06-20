import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminGuard } from './shared/guards/admin-guard.guard';
import { LandingAdminComponent } from './components/admin/landing-admin/landing-admin.component';
import { LandingPageComponent } from './components/admin/landing-page/landing-page.component';
import { PropertyTableComponent } from './components/admin/property-table/property-table.component';
import { UserTableComponent } from './components/admin/user-table/user-table.component';
import { LoginUserComponent } from './components/user/login-user/login-user.component';
import { LandingUserComponent } from './components/user/landing-user/landing-user.component';
import { AuthComponent } from './shared/components/auth/auth.component';
import { UserGuard } from './shared/guards/user.guard';
import { UserprofileComponent } from './components/user/userprofile/userprofile.component';
import { UserLandingPageComponent } from './components/user/user-landing-page/user-landing-page.component';
import { RegisterFormComponent } from './shared/components/register-form/register-form.component';
import { PropertyDetailComponent } from './shared/components/property-detail/property-detail.component';

const routes: Routes = [
  { path: 'admin-login', component: AuthComponent },
  { path: 'home', canActivate: [AdminGuard], component: LandingAdminComponent },
  {path: 'admin', component: LandingPageComponent,canActivate: [AdminGuard],
    children: [
      { path: 'properties', component: PropertyTableComponent },
      { path: 'users', component: UserTableComponent}
    ]
  },
  {path: 'index',component: LandingUserComponent, },
  {path:'property/:id',component:PropertyDetailComponent},
  {path: 'register', component: RegisterFormComponent},
  {path: 'user', component: UserLandingPageComponent,canActivate: [UserGuard],
  children:[
    {path: 'me',component:UserprofileComponent },
  ]
  },
  {path: 'index',component: LandingUserComponent},
  {path: 'login',component: LoginUserComponent},
  { path: '', redirectTo: 'index', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
