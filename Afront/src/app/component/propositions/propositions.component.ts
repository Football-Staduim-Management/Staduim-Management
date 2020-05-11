import { Component, OnInit, ViewChild, NgZone, OnDestroy } from '@angular/core';
import { SearchService } from 'src/app/services/httpServices/search.service';
import { AgmMap, MapsAPILoader } from '@agm/core'
import { stadium } from 'src/app/Model/Stadium';
@Component({
  selector: 'app-propositions',
  templateUrl: './propositions.component.html',
  styleUrls: ['./propositions.component.css']
})
export class PropositionsComponent implements OnInit, OnDestroy {

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
    private searchService: SearchService) {
    this.latitude = searchService.centre.alt;
    this.longitude = searchService.centre.lng;
    this.stadiums = searchService.propStadiums
    this.address = searchService.address
    this.date = searchService.date
    this.time = searchService.time
    this.nbr = this.stadiums.length
    this.findLocations(this.stadiums);
  }
  ngOnDestroy(): void {
    this.searchService.propStadiums=undefined
  }

  ngOnInit(): void {

  }
   getCurrentLocation(alt, lng, index) {
    
     this.mapsAPILoader.load().then(() => {

      let geocoder = new google.maps.Geocoder;
      let latlng = { lat: alt, lng: lng };
      let that = this;

      geocoder.geocode({ 'location': latlng }, function (results) {
        if (results[index]) {
          that.zoom = 11;
          that.stadiums[index].location = results[0].formatted_address;
          if(index===that.stadiums.length-1){
            that.loaded=true;
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
    console.log(stadiums)
  }
}
