/*
This is class made to get and post data to web service for publish
product .
*/
import { Injectable } from '@angular/core';
import { HttpHeaders,HttpClient,HttpParams } from '@angular/common/http'
import { Observable }  from 'rxjs';
import { ActivatedRoute } from '@angular/router'
import { map  , switchMap} from 'rxjs/operators'
//import { bindNodeCallback } from 'rxjs/observable/bindNodeCallback';
//import xml2js from'xml2js';  // just for XML post

@Injectable({
  providedIn: 'root'
})
export class PublishItemPageService {


  constructor(private http: HttpClient  ) { }

 /* post product */
  public getPageItem(): Observable<any>
  {
     return this.http.get('/API/Productlist.jsp',
      {
        headers: new HttpHeaders()
          .set('Content-Type', 'text/xml')
          .append('Access-Control-Allow-Methods', 'GET')
          .append('Access-Control-Allow-Origin', '*')
          .append('Access-Control-Allow-Headers', "Access-Control-Allow-Headers, Access-Control-Allow-Origin, Access-Control-Request-Method"),
        responseType: 'text'
      })
  }

  /* Extented header defenition */
  private httpOptionsExt = {
    headers: new HttpHeaders({
      'Content-Type': 'appication/xml',
      'sessionID':'sesstionId',
      'userName' : 'user'
    })
  } ;

  /* Header defenition */
  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'appication/xml'
    })
  } ;


  public savePageItem(jsonObj: any ): Observable<any>
  {
   // Take url for item post from Swagger API
    return this.http.post(('/API/Productlist.jsp'),JSON.stringify(jsonObj),this.httpOptions);
  }

}
