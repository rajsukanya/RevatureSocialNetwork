import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'The Tea';

  private cookieValue: string;
  
  constructor(private CookieService: CookieService) {}

  public ngOnInit():void {
    this.CookieService.set('cookie-name', 'our cookie value');
    this.cookieValue = this.CookieService.get('cookie-name');
  }
}
