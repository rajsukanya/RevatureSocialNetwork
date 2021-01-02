import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  constructor(private cookieService:CookieService, private router:Router) { }

  ngOnInit(): void {
  }

  logout() {
    this.cookieService.delete('cookie');
    this.router.navigate(['/landing-page']);
  }

}
