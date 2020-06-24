import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { PlatformLocation, Time } from '@angular/common' 
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { map } from 'rxjs/operators';
import { Match } from 'src/app/Model/Match';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {

  reservationForm = new FormGroup(
    {
      player1 : new FormControl(''),
      player2 : new FormControl(''),
      player3 : new FormControl(''),
      message : new FormControl('')
  }
  )
  match : Match;
  starteTime : Date  = new Date();
  endTime : String
  constructor(location: PlatformLocation, 
              router: Router, 
              private store : Store<{match : any}>) { 
                store.pipe().pipe(map(res=> res.match )).subscribe(res => 
                  {
                    this.match=res.match;
                    let times : string[] = this.match.startTime.split(":");
                    this.starteTime.setHours(parseInt(times[0]));
                    this.starteTime.setMinutes(parseInt(times[0]));
                    let date : string[] = this.match.date.split("-");
                    this.starteTime.setFullYear(parseInt(date[2]),parseInt(date[1]),parseInt(date[0]))
                    let timeStamp = Date.parse(this.starteTime.toString())+(5400*1000);
                    times = new Date(timeStamp).toTimeString().split(" ")[0].split(":");
                    this.endTime = times[0]+":"+times[1];
                  })
              location.onPopState(() => {
                var r = confirm("You pressed a Back button! Are you sure?!");
                if (r == true) {
                  router.navigateByUrl("/propositions");
                }
              });
  }

  ngOnInit(): void {  }

  payer(){
    let reservation = {
      match :  this.match,
      infos : this.reservationForm.getRawValue()
    }
    
  }
}
