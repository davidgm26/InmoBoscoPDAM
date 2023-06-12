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
import { AuthComponent } from './shared/auth/auth.component';
import { LandingPageComponent } from './components/admin/landing-page/landing-page.component';
import { PropertyTableComponent } from './components/admin/property-table/property-table.component';
import { UserTableComponent } from './components/admin/user-table/user-table.component';
import { LandingAdminComponent } from './components/admin/landing-admin/landing-admin.component';
import { NavbarComponent } from './components/admin/navbar/navbar.component';
import { EditPropertyDialogComponent } from './components/admin/edit-property-dialog/edit-property-dialog.component';
import { UserConfirmDialogComponent } from './components/admin/user-confirm-dialog/user-confirm-dialog.component';
import { EditUserFromAdminDialogComponent } from './components/admin/edit-user-from-admin-dialog/edit-user-from-admin-dialog.component';
import { CreateUserComponent } from './components/admin/create-user/create-user.component';

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
    LandingAdminComponent
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
    ToastrModule.forRoot(),
  ],
  providers: [
    authInterceptorProviders
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
