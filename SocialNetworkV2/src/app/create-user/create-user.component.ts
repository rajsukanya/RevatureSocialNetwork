import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { User } from '../user';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {
  user: User = new User();
  submitted = false;

  constructor(private httpService:HttpService, private router:Router, private cookieService: CookieService) { }

  ngOnInit(){
  }

  newUser():void{
    this.submitted=false;
  }

  save(){
    this.user.profilepicture = "https://thetea.s3.us-east-2.amazonaws.com/Profile-ICon.png";
    this.httpService.createUser(this.user).subscribe(
      data => {
        console.log(data);
        this.user = data;
        console.log(this.user);
        this.cookieService.set('cookie', `${this.user.userId}`)
        console.log(this.cookieService.get('cookie'));
        this.gotoList();
      },
      error => console.log(error));
  }

  onSubmit(){
    console.log('in onSubmit');
    this.submitted = true;
    this.save();
  }

  gotoList(){
    this.router.navigate(['/landing-page/homepage']);
  }

}
