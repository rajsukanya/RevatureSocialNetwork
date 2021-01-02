import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HttpService {
  private baseUrl = 'http://localhost:5555/thetea';
  constructor(private http:HttpClient) { }

  getUser(id:string):Observable<any>{
    return this.http.get(`${this.baseUrl}/users/${id}`);
  }

  createUser(user:Object):Observable<any>{
    console.log(user);
    return this.http.post(`${this.baseUrl}/add`, user);
  }

  updateUser(value:any):Observable<Object>{
    return this.http.post(`${this.baseUrl}/${value.userId}`, value);
  }

  deleteUser(id:string):Observable<any>{
    return this.http.delete(`${this.baseUrl}/${id}`, {responseType:'text'});
  }

  getUsersList():Observable<any>{
    return this.http.get(`${this.baseUrl}/users`);
  }

  getLogin(username:string, password:string):Observable<any>{
    console.log("in service login " + username + password);
    return this.http.post(`${this.baseUrl}/login`, {username, password}, {withCredentials: true });
  }

  getUserByName(name:string):Observable<any>{
    return this.http.get(`${this.baseUrl}/search/?name=${name}`);
  }
}
