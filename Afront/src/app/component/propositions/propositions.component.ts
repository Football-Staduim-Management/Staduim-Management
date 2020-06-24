import { Component, OnInit, ViewChild, NgZone, OnDestroy } from '@angular/core';
import { SearchService } from 'src/app/services/httpServices/search.service';
import { AgmMap, MapsAPILoader } from '@agm/core'
import { stadium } from 'src/app/Model/Stadium';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { Match } from 'src/app/Model/Match';
import { reserveMatch } from 'src/app/services/reduxService/actions/match.action';
import { matchReducer } from 'src/app/services/reduxService/reducers/match.reducers';
@Component({
  selector: 'app-propositions',
  templateUrl: './propositions.component.html',
  styleUrls: ['./propositions.component.css']
})
export class PropositionsComponent implements OnInit {

  latitude: number;
  longitude: number;
  zoom: number = 10;
  @ViewChild(AgmMap) map: AgmMap;
  address: String
  stadiums: stadium[]
  date: String
  time: String
  nbr: number
  loaded : boolean

  constructor(
    private mapsAPILoader: MapsAPILoader,
    private ngZone: NgZone,
    private searchService: SearchService,
    private router : Router,
    private store : Store< Match>
    ) {
    this.latitude = searchService.centre.alt;
    this.longitude = searchService.centre.lng;
    this.stadiums = searchService.propStadiums
    this.address = searchService.address
    this.date = searchService.date
    this.time = searchService.time
    this.nbr = this.stadiums.length
    this.findLocations(this.stadiums);
  }
  

  ngOnInit(): void {

  }
   getCurrentLocation(alt, lng, index) {
    
     this.mapsAPILoader.load().then(() => {

      let geocoder = new google.maps.Geocoder;
      let latlng = { lat: alt, lng: lng };
      let that = this;
      console.log(that==this)

      geocoder.geocode({ 'location': latlng },  (results) => {
        console.log(that==this)

        if (results[index]) {

          this.zoom = 11;
          this.stadiums[index].location = results[0].formatted_address;
          if(index===this.stadiums.length-1){
            this.loaded=true;
          }
        } else {
          console.log('No results found');
        }
      });
    });
  }
  
  findLocations(stadiums: stadium[]) {
    let i = 0

    stadiums.forEach((element) => {
      
      this.getCurrentLocation(element.position.alt, element.position.lng, i)
      i++;
    })
  }
  completeReservation(index){
    let match = new Match();
    match.date= this.date;
    match.startTime = this.time;
    match.stadium= this.stadiums[index];
    this.store.dispatch(reserveMatch({match}))
    this.router.navigate(['reservation']);
  }
}
