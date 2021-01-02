import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { HttpService } from '../http.service';
import { User } from '../user';
import { PostService } from '../post.service';
import { Observable } from 'rxjs';
import { Post } from '../post';
import { UploadService } from '../upload.service';
import { EventEmitterService} from '../event-emitter.service';
import { ActivatedRoute, Params, Router} from '@angular/router';



@Component({
  selector: 'app-other-user',
  templateUrl: './other-user.component.html',
  styleUrls: ['./other-user.component.css']
})
export class OtherUserComponent implements OnInit {
  user: User = new User();
  posts: Observable<Post[]>;
  selectedFile: File;
  id2:string;

  constructor(private _route: ActivatedRoute,
    private _router: Router,
 private uploadService: UploadService, 
 private cookieService:CookieService, 
 private httpService:HttpService,  private eventEmitterService: EventEmitterService, private postService:PostService) { }

  ngOnInit(): void{
    //console.log("this is the id in ngonInit: " + this.id2);
    //this.otherUserProfile(this.id2);
    this._route.params.subscribe(params => {
      this.otherUserProfile(params['id']);
    });
  }

    // this.route.params.forEach((params: Params) => {

    //   this.otherUserProfile(this.id2)
    
  
  otherUserProfile(id:string){
    //let id2 = id;
    //alert("you are viewing a profile");
    console.log("in other user profile. Id is: " + id);
    this.httpService.getUser(id).subscribe(
      data => {
        console.log(data);
        this.user = data;
        
      }
    ),
    error => console.log(error);
    
    this.postService.getPost(id).subscribe(
      data => {
        console.log(data);
        this.posts = data;
        
      },
      error => console.log(error));

  }

  // temp(id:string){
  //   console.log('in temp');
  //   this.id2 = id;
  //   this.otherUserProfile(this.id2);

  // }

}