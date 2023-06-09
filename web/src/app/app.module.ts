import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { MaterialImportsModule } from './modules/material-imports.module';
import { AuthComponent } from './components/auth/auth.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { NavbarComponent } from './shared/components/navbar/navbar.component';
import { PropertyTableComponent } from './components/property-table/property-table.component';
import { EditPropertyDialogComponent } from './shared/components/edit-property-dialog/edit-property-dialog.component';
import { AdminIndexComponent } from './components/admin-index/admin-index.component';
import { UserTableComponent } from './components/user-table/user-table.component';
import { ConfirmationDialogComponent } from './shared/components/confirmation-dialog/confirmation-dialog.component';
import { authInterceptorProviders } from './auth.interceptor';
import { UserConfirmDialogComponent } from './shared/components/user-confirm-dialog/user-confirm-dialog.component';
import { EditUserFromAdminDialogComponent } from './shared/components/edit-user-from-admin-dialog/edit-user-from-admin-dialog.component';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { UserTypeSelectionDialogComponent } from './shared/components/user-type-selection-dialog/user-type-selection-dialog.component';
import { CreateUserComponent } from './shared/components/create-user/create-user.component';

@NgModule({
  declarations: [
    AppComponent,
    AuthComponent,
    LandingPageComponent,
    NavbarComponent,
    PropertyTableComponent,
    EditPropertyDialogComponent,
    AdminIndexComponent,
    UserTableComponent,
    ConfirmationDialogComponent,
    UserConfirmDialogComponent,
    EditUserFromAdminDialogComponent,
    UserTypeSelectionDialogComponent,
    CreateUserComponent
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
