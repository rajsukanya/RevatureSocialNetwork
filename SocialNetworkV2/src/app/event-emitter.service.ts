import { Injectable, EventEmitter } from '@angular/core';
import { Subscription } from 'rxjs/internal/Subscription';
import { OtherUserComponent } from './other-user/other-user.component';


@Injectable({
  providedIn: 'root'
})
export class EventEmitterService {
  otherUserProfile = new EventEmitter();    
  subsVar: Subscription;  
  constructor() { }

  goToProfileInOtherUser(id:string){
    this.otherUserProfile.emit(id);
    console.log("in event emitter service. Id is: " + id);
  }
}
