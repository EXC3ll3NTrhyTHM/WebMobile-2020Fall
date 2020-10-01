import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-search-recipe',
  templateUrl: './search-recipe.component.html',
  styleUrls: ['./search-recipe.component.css']
})
export class SearchRecipeComponent implements OnInit {
  @ViewChild('recipe') recipes: ElementRef;
  @ViewChild('place') places: ElementRef;
  recipeValue: any;
  placeValue: any;
  venueList = [];
  recipeList = [];
  edamanAppId = 'aefdd70b';
  edamanAppKey = 'b584906ed8d637f1be0f4f811e241212';
  foursquareClientId = '0FHPOF51LCNLD3ASRLBSNLFN2HAPIXE2YZSS5AHT5T0VHJC4';
  foursquareClientSecret = 'CZT2TYAC3YBB2BPZ31JOHEAYJ2RJOTXL30OYVISPVCWK1A0U';

  currentLat: any;
  currentLong: any;
  geolocationPosition: any;

  constructor(private _http: HttpClient) {
  }

  ngOnInit() {

    window.navigator.geolocation.getCurrentPosition(
      position => {
        this.geolocationPosition = position;
        this.currentLat = position.coords.latitude;
        this.currentLong = position.coords.longitude;
      });
  }

  getVenues() {

    this.recipeValue = this.recipes.nativeElement.value;
    this.placeValue = this.places.nativeElement.value;

    if (this.recipeValue !== null) {
      /**
       * Write code to get recipe
       */
      this._http.jsonp(
        'https://api.edamam.com/search?q=' + this.recipeValue +
        '&app_id=' + this.edamanAppId +
        '&app_key=' + this.edamanAppKey +
        '&from=0&to=6',
        'callback')
        .subscribe((data: any) => {
          const tempArr = [];
          for (const x of data.hits) {
            tempArr.push(x.recipe);
          }
          this.recipeList = tempArr;
    });

    if (((this.placeValue != null && this.placeValue !== '') && (this.recipeValue != null && this.recipeValue !== '')) ||
      ((this.recipeValue != null && this.recipeValue !== '') && (this.geolocationPosition != null))) {
      /**
       * Write code to get place
       */
      let locationSearchFormat;
      if (this.placeValue != null && this.placeValue !== '') {
        locationSearchFormat = 'near';
      } else {
        locationSearchFormat = 'll';
        this.placeValue = this.currentLat + ',' + this.currentLong;
      }
      this._http.jsonp(
        'https://api.foursquare.com/v2/venues/explore?client_id=' +
        this.foursquareClientId + '&client_secret=' +
        this.foursquareClientSecret + '&v=20180323&limit=9&' +
        locationSearchFormat + '=' + this.placeValue +
        '&query=' + this.recipeValue,
        'callback')
        .subscribe((data: any) => {
          const tempArr = [];
          console.log(data.response.groups[0]);
          for (const x of data.response.groups[0].items) {
            tempArr.push(x.venue);
          }
          this.venueList = tempArr;
        });
    }
  }
}}
