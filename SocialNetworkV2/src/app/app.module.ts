import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { ProfileComponent } from './profile/profile.component';
import { HomepageComponent } from './homepage/homepage.component';
import { SettingsComponent } from './settings/settings.component';
import { AppRoutingModule } from './app-routing/app-routing.module';
import { CreateUserComponent } from './create-user/create-user.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { UserListComponent } from './user-list/user-list.component';
import { HttpClientModule } from '@angular/common/http';
import { HttpService } from './http.service';
import { UpdateUserComponent } from './update-user/update-user.component';
import { CookieService } from 'ngx-cookie-service';
import { PostDetailsComponent } from './post-details/post-details.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { OtherUserComponent } from './other-user/other-user.component';
import { EventEmitter } from 'events';
import { EventEmitterService } from './event-emitter.service';


@NgModule({
  declarations: [
    AppComponent,
    LandingPageComponent,
    RegisterComponent,
    LoginComponent,
    ProfileComponent,
    HomepageComponent,
    SettingsComponent,
    CreateUserComponent,
    UserDetailsComponent,
    UserListComponent,
    UpdateUserComponent,
    PostDetailsComponent,
    NavBarComponent,
    OtherUserComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    HttpService,
    CookieService,
    EventEmitterService,
    OtherUserComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
