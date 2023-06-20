import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { MaterialImportsModule } from './modules/material-imports.module';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ConfirmationDialogComponent } from './components/admin/confirmation-dialog/confirmation-dialog.component';
import { authInterceptorProviders } from './auth.interceptor';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { LandingPageComponent } from './components/admin/landing-page/landing-page.component';
import { PropertyTableComponent } from './components/admin/property-table/property-table.component';
import { UserTableComponent } from './components/admin/user-table/user-table.component';
import { LandingAdminComponent } from './components/admin/landing-admin/landing-admin.component';
import { NavbarComponent } from './components/admin/navbar/navbar.component';
import { EditPropertyDialogComponent } from './components/admin/edit-property-dialog/edit-property-dialog.component';
import { UserConfirmDialogComponent } from './components/admin/user-confirm-dialog/user-confirm-dialog.component';
import { EditUserFromAdminDialogComponent } from './components/admin/edit-user-from-admin-dialog/edit-user-from-admin-dialog.component';
import { CreateUserComponent } from './components/admin/create-user/create-user.component';
import { InfiniteScrollModule } from 'ngx-infinite-scroll';
import { LoginUserComponent } from './components/user/login-user/login-user.component';
import { LandingUserComponent } from './components/user/landing-user/landing-user.component';
import { PropertyCardComponent } from './components/user/property-card/property-card.component';
import { AuthComponent } from './shared/components/auth/auth.component';
import { UsernavbarComponent } from './components/user/usernavbar/usernavbar.component';
import { UserLandingPageComponent } from './components/user/user-landing-page/user-landing-page.component';
import { ImgPipe } from './shared/pipes/img.pipe';
import { RegisterFormComponent } from './shared/components/register-form/register-form.component';
import { PropertyDetailComponent } from './shared/components/property-detail/property-detail.component';
import { UserProfileComponent } from './components/user/user-profile/user-profile.component';
import { ImgProfilePipe } from './shared/pipes/img-profile.pipe';
import { DialougUploadImageComponent } from './shared/components/dialoug-upload-image/dialoug-upload-image.component';
import { FavouritePropertyCardComponent } from './components/user/favourite-property-card/favourite-property-card.component';


@NgModule({
  declarations: [
    AppComponent,
    AuthComponent,
    LandingPageComponent,
    NavbarComponent,
    PropertyTableComponent,
    EditPropertyDialogComponent,
    UserTableComponent,
    ConfirmationDialogComponent,
    UserConfirmDialogComponent,
    EditUserFromAdminDialogComponent,
    CreateUserComponent,
    LandingAdminComponent,
    LoginUserComponent,
    LandingUserComponent,
    PropertyCardComponent,
    UsernavbarComponent,
    UserLandingPageComponent,
    ImgPipe,
    RegisterFormComponent,
    PropertyDetailComponent,
    UserProfileComponent,
    ImgProfilePipe,
    DialougUploadImageComponent,
    FavouritePropertyCardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialImportsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatDatepickerModule,
    MatNativeDateModule,
    InfiniteScrollModule,
    ToastrModule.forRoot(),
  ],
  providers: [
    authInterceptorProviders
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
