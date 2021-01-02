import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { ActivatedRoute, Router} from '@angular/router';
import { HttpService } from '../http.service';
import { CookieService } from 'ngx-cookie-service';
import { UploadService } from '../upload.service';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {
  user:User;
  id:string;
  selectedFile: File;

  constructor(private uploadService: UploadService, private route: ActivatedRoute, private router: Router, private httpService: HttpService, private cookieService:CookieService) { }

  ngOnInit() {
    this.user = new User();
    //this.id = this.route.snapshot.params['id'];
    console.log(this.cookieService.get('cookie'));
    this.cookieService.get('cookie');
    this.id = this.cookieService.get('cookie');
    this.httpService.getUser(this.id).subscribe(
      data => {
        console.log(data)
        this.user = data;
        console.log(this.user.userId)
      },
      error => console.log(error));
  }

   updateUser(){
    this.httpService.updateUser(this.user).subscribe(
      data => {
        console.log(data);
        if(this.user.profilepicture != null ){
        this.user = new User();
        
        this.gotoList();
        }
      },
      error => console.log(error));
   }

   onFileSelected(event){
    console.log(event);
    this.selectedFile = event.target.files[0];
  }

   upload(){
    this.uploadService.upload(this.selectedFile).subscribe(data => {
      this.user.profilepicture = JSON.stringify(data.body);
      console.log("LOOK AT THIS FOR URL: " + this.user.profilepicture);
      this.updateUser();
    }, error => console.log(error));
    
  }
  getMethod(){
    this.upload();
  }

  onSubmit(){
    this.getMethod();
  }

  gotoList(){
    this.ngOnInit();
  }

}
