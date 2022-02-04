import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import {AppRoutingModule, routingComponents} from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './pages/login/login.component';
import { HomeComponent } from './pages/home/home.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {PersonService} from './services/person.service';
import {HttpClientModule} from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import {MatDividerModule} from '@angular/material/divider';
import { DetailsComponent } from './pages/home/details/details.component';
import {MatDialogModule} from '@angular/material/dialog';
import {MatSidenavModule} from '@angular/material/sidenav';
import { AddpersonComponent } from './pages/home/addperson/addperson.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import {MatTableModule} from '@angular/material/table';
import { DevicesComponent } from './pages/home/devices/devices.component';
import {MatSelectModule} from '@angular/material/select';
import { ClientComponent } from './pages/client/client.component';
import { DeviceDetailsComponent } from './pages/client/device-details/device-details.component';

@NgModule({
  declarations: [
    AppComponent,
    routingComponents,
    HomeComponent,
    DetailsComponent,
    AddpersonComponent,
    DevicesComponent,
    ClientComponent,
    DeviceDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatButtonModule,
    MatDividerModule,
    MatDialogModule,
    MatSidenavModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    ReactiveFormsModule,
    MatTableModule,
    MatSelectModule,
  ],
  entryComponents: [
    DetailsComponent,
    AddpersonComponent,
    DevicesComponent,
  ],
  providers: [PersonService],
  bootstrap: [AppComponent]
})
export class AppModule { }
