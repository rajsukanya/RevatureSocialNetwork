import { Component, OnInit } from '@angular/core';
import { PostService } from '../post.service';
import { ActivatedRoute,Router } from '@angular/router';
import { Post } from '../post';
import { Observable } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';
import { Likes } from '../likes';
import { UploadService } from '../upload.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  posts: Observable<Post[]>
  post: Post = new Post();
  like: Likes = new Likes();
  id:string;
  selectedFile: File;

  constructor(private uploadService: UploadService, private postService: PostService, private route: ActivatedRoute, private router: Router, private cookieService: CookieService) { }

  ngOnInit(){
    console.log(this.cookieService.get('cookie'));
    this.cookieService.get('cookie');
    this.id = this.cookieService.get('cookie');
    this.reloadData();
  }

  reloadData(){
    this.posts = this.postService.getPostsList();
    this.uploadService.getAllImages().subscribe(data => {
      console.log(data);
      this.gotoList();
    })
    console.log(this.posts);
    console.log("in reload data");
  }

  onFileSelected(event){
    console.log(event);
    this.selectedFile = event.target.files[0];
  }

  save(){
    console.log(this.post);
    this.postService.createPost(this.post, this.id).subscribe(
      data => {
        console.log(data);
        this.ngOnInit();
        this.gotoList();
        
      },
      error => console.log(error));  
  }

  upload(){
    this.uploadService.upload(this.selectedFile).subscribe(data => {
      this.post.image= JSON.stringify(data.body);
      console.log("LOOK AT THIS FOR URL: " + this.post.image);
      this.save();
    }, error => console.log(error));
    
  }

  getMethod(){
    this.upload();
  }

  onSubmit(){
    console.log('in onSubmit');
    
    this.getMethod();
    
  }

  gotoList(){
    console.log('in gotoList');
    this.router.navigate(['/landing-page/posts']);
  }

  createLike(id:string){
    this.postService.createLike(this.like, id).subscribe(
      data => {
        console.log(data);
        this.reloadData();
      },
      error => console.log(error));
  }



  deletePost(id:string){
    this.postService.deletePost(id).subscribe(
      data => {
        console.log(data);
        this.reloadData();
      },
      error => console.log(error));
  }

  postDetails(id:string){
    this.router.navigate(['/landing-page/details', id]);
  }

  updatePost(id:string){
    this.router.navigate(['/landing-page/update', id]);
  }

}

