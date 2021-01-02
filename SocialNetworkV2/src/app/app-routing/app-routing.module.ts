import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from '../login/login.component';
import { RegisterComponent } from '../register/register.component';
import { LandingPageComponent } from '../landing-page/landing-page.component';
import { UserDetailsComponent } from '../user-details/user-details.component';
import { CreateUserComponent } from '../create-user/create-user.component';
import { UserListComponent} from '../user-list/user-list.component';
import { UpdateUserComponent } from '../update-user/update-user.component';
import { HomepageComponent } from '../homepage/homepage.component';
import { ProfileComponent } from '../profile/profile.component';
import { OtherUserComponent } from '../other-user/other-user.component';

const routes: Routes = [{path: '',pathMatch: 'full', redirectTo: 'landing-page'}, 
                        {path: 'landing-page', component: LandingPageComponent},
                        {path: 'landing-page/login', component: LoginComponent},
                        {path: 'landing-page/users', component: UserListComponent},
                        {path: 'landing-page/add', component: CreateUserComponent},
                        {path: 'landing-page/update/:id', component: UpdateUserComponent},
                        {path: 'landing-page/details/:id', component: UserDetailsComponent},
                        {path: 'landing-page/homepage', component: HomepageComponent},
                        {path: 'landing-page/posts', component: HomepageComponent},
                        {path: 'landing-page/profile', component: ProfileComponent},
                        {path: 'landing-page/logout', component: LandingPageComponent},
                        {path: 'landing-page/otherProfile/:id', component: OtherUserComponent}               
                      ];

@NgModule({

  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
    
})
export class AppRoutingModule { }
