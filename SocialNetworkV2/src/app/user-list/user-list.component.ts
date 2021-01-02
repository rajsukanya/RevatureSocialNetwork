import { Component, OnInit } from '@angular/core';
import { UserDetailsComponent} from '../user-details/user-details.component';
import { HttpService } from '../http.service';
import { User } from '../user';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';
import { EventEmitterService} from '../event-emitter.service';
import { OtherUserComponent } from '../other-user/other-user.component';


@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  users: Observable<User[]>
  name: string;
  user: User = new User();

  constructor(private otherUserComponent: OtherUserComponent, private httpService: HttpService, private router:Router, private cookieService:CookieService, private eventEmitterService: EventEmitterService) { }

  ngOnInit(){
    this.reloadData();
    // this.eventEmitterService.otherUserProfile.subscribe((id:string) => {    
    //   this.goToProfile(id);    
    // });
  }

  reloadData(){
    this.users = this.httpService.getUsersList();
  }

  deleteUser(id:string){
    this.httpService.deleteUser(id).subscribe(
      data => {
        console.log(data);
        this.reloadData();
      },
      error => console.log(error));
  }

  userDetails(id:string){
    this.router.navigate(['/landing-page/details', id]);
  }

  updateUser(id:string){
    this.router.navigate(['/landing-page/update', id]);
  }

  searchUser(): void{
    this.httpService.getUserByName(this.name).subscribe(
      user => {
        this.users = user;
        console.log('User: ' + user);
        this.user = user;
        var array = user;
        const object = Object.assign({}, ...array);
        console.log(object); //object
        console.log(this.users); //array
        console.log(object.userId);
        // this.cookieService.set('cookie', `${object.userId}`);
        // this.id = Number(this.cookieService.get('cookie'));
        // console.log(this.id);
        // console.log(this.cookieService.get('cookie'));
      },
      error => {console.log(error);
      })
  }

  onSubmit(){
    console.log('in user-list onSubmit');
    this.searchUser();
  }

  goToProfile(id:string){
    //this.eventEmitterService.goToProfileInOtherUser(id);
    console.log("this is the id: " + id); 
    
    //this.otherUserComponent.otherUserProfile(id);
    //this.otherUserComponent.temp(id);
    this.router.navigate(['/landing-page/otherProfile']);
  }

}
