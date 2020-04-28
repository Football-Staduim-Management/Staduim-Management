import { Component, OnInit, NgZone, ElementRef, ViewChild, AfterViewInit, AfterViewChecked, OnChanges } from '@angular/core';
import { MapsAPILoader, MouseEvent, AgmCircle, AgmMarker, LatLngLiteral } from '@agm/core';
import { SearchInfos } from 'src/app/Model/SearchInfos';
import { SearchService } from 'src/app/services/search.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Route } from '@angular/compiler/src/core';
@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit, AfterViewInit {

  latitude: number = 33.8439408;
  longitude: number = 9.400138;
  circleRadius: number = 5000;
  zoom: number = 6;
  private geoCoder;
  time: boolean = false
  zone: boolean = true
  date: boolean = false
  searchZone: boolean = false
  dateString: String = "Aujourd'hui"
  timeString: String = "23:59"
  months: String[] = [
    "Janv.",
    "Fev.",
    "Mar.",
    "Avr.",
    "Mai.",
    "Jun.",
    "Juil.",
    "Aout.",
    "Sept.",
    "Oct.",
    "Nov.",
    "Dec.",
  ]
  address: string;
  @ViewChild("search") searchElementRef: ElementRef;
  @ViewChild(AgmCircle) circleZone : AgmCircle;
  @ViewChild(AgmMarker) marker : AgmMarker;
  autocomplete: any
  searchInf : SearchInfos = new SearchInfos();

  constructor(
    private mapsAPILoader: MapsAPILoader,
    private ngZone: NgZone,
    private searchService : SearchService,
    private route : Router
    ) {
  }


  ngAfterViewInit(): void {
    this.onShowSearchZone();
  }


  ngOnInit(): void {

  }

  onShowDate() {
    this.circleZone.getCenter().then((res)=>{
      this.searchInf.zoneCenter.lng = res.lng()
      this.searchInf.zoneCenter.alt = res.lat()
    })
    this.searchInf.zoneRaduis=this.circleZone.radius
    this.time = false
    this.zone = false
    this.date = true
  }

  onShowTime(event) {
    this.time = true
    this.zone = false
    this.date = false
    this.dateString = event.day + " " + this.months[event.month - 1] + " " + event.year
  }

  onShowZone() {
    this.time = false
    this.zone = true
    this.date = false
    this.onShowSearchZone();
  }

  onShowSearchZone() {
    this.mapsAPILoader.load().then(() => {

      this.geoCoder = new google.maps.Geocoder;

      this.autocomplete = new google.maps.places.Autocomplete(this.searchElementRef.nativeElement);
      this.autocomplete.addListener("place_changed", () => {
        this.ngZone.run(() => {
          //get the place result
          let place: google.maps.places.PlaceResult = this.autocomplete.getPlace();
          this.address = place.formatted_address
          //verify result
          if (place.geometry === undefined || place.geometry === null) {
            return;
          }

          //set latitude, longitude and zoom
          this.latitude = place.geometry.location.lat();
          this.longitude = place.geometry.location.lng();
          this.zoom = 12;
        });
      });
    });
  }

  markerDragEnd($event: MouseEvent) {
    console.log($event);
    this.latitude = $event.coords.lat;
    this.longitude = $event.coords.lng;
    this.getAddress(this.latitude, this.longitude);
  }

  getAddress(latitude, longitude) {
    this.geoCoder.geocode({ 'location': { lat: latitude, lng: longitude } }, (results, status) => {
      console.log(results);
      console.log(status);
      if (status === 'OK') {
        if (results[0]) {
          this.zoom = 12;
          this.address = results[0].formatted_address;
        } else {
          window.alert('No results found');
        }
      } else {
        window.alert('Geocoder failed due to: ' + status);
      }

    });
  }

   search(){
    this.searchInf.date=this.dateString
    this.searchInf.time=this.timeString
    this.searchService.centre = this.searchInf.zoneCenter
    this.searchService.address=this.address
    this.searchService.date = this.dateString
    this.searchService.time= this.timeString
    this.searchService.searchStadiums(this.searchInf).subscribe((res)=>{
      this.searchService.propStadiums = res
      this.route.navigateByUrl("/propositions")
    },error=>{console.log(error)})
  }

  onChangeCenter(event){
    console.log(event)
  }
  

}
