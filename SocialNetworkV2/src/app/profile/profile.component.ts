import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { HttpService } from '../http.service';
import { User } from '../user';
import { PostService } from '../post.service';
import { Observable } from 'rxjs';
import { Post } from '../post';
import { UploadService } from '../upload.service';
import { EventEmitterService} from '../event-emitter.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  user: User = new User();
  posts: Observable<Post[]>;
  selectedFile: File;

  constructor(private uploadService: UploadService, private cookieService:CookieService, private httpService:HttpService, private postService:PostService) { }

  ngOnInit(): void {
    console.log(this.cookieService.get('cookie'));
    this.cookieService.get('cookie');
    let id = this.cookieService.get('cookie');
    this.httpService.getUser(id).subscribe(
      data => {
        console.log(data);
        this.user = data;
      }
    );
    this.postService.getPost(id).subscribe(
      data => {
        console.log(data);
        this.posts = data;
      }
    );
  }



}
