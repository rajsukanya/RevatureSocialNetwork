import { HttpClient } from '@angular/common/http';
import { Injectable, Inject } from '@angular/core';
import { Observable } from 'rxjs';
import { Post } from './post';
import { DOCUMENT } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class PostService {
  private baseUrl = 'http://localhost:5555/theteaPost';
  //post:Post = new Post();

  constructor(private http:HttpClient, @Inject(DOCUMENT) document) { }

  getPost(id:string):Observable<any>{
    return this.http.get(`${this.baseUrl}/posts/${id}`);
  }

  createPost(post:Object, userId:string):Observable<any>{
    console.log(post);
    console.log("in create post");
    return this.http.post(`${this.baseUrl}/addPost/${userId}`, post, {withCredentials: true});
  }

  updatePost(id:string, value:any):Observable<Object>{
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deletePost(id:string):Observable<any>{
    return this.http.delete(`${this.baseUrl}/${id}`, {responseType:'text'});
  }

  getPostsList():Observable<any>{
    console.log("in post list");
    return this.http.get(`${this.baseUrl}/posts`);
  }

  createLike(like:Object, postId:string):Observable<Object>{
    console.log(like);
    console.log("in create like");
    return this.http.post(`${this.baseUrl}/likes/${postId}`, like);
  }

  getLikes(id:string):Observable<any>{
    return this.http.get(`${this.baseUrl}/likes/${id}`);
  }

//   s3upload():Observable<any> {
//     console.log('in s3upload');
//     let pic =(<HTMLInputElement>document.getElementById("imgUp")).value;
//     console.log(pic);
//     let url = 'https://thetea.s3.us-east-2.amazonaws.com';
//     if(this.testImage(url)){
//         return null;
//     }
//     console.log(url);
//     document.getElementById('rando').innerHTML='<img src='+url+ ' />'
//     return this.http.post(`${url}/${pic}`); //error
   
//     }
//     testImage(URL:any):any {
//         var tester=new Image();
//         //tester.onload=imageFound;
//         tester.onerror=this.imageNotFound;
//         tester.src=URL;
//     }
    
//     imageFound():void {
//         alert('That image is found and loaded');
//     }
    
//     imageNotFound():boolean {
//         alert('That image was not found.');
//         return true;
//     } 
}
