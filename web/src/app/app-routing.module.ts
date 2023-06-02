import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthComponent } from './components/auth/auth.component';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { AuthGuard } from './shared/guards/auth-guard.guard';

const routes: Routes = [
  {path:"login", component:AuthComponent},
  {path: "home",canActivate:[AuthGuard] ,component: LandingPageComponent},
  {path:"", redirectTo:"login", pathMatch:"full"}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
