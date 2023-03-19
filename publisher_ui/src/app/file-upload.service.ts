
/*
This is class made to  upload images and files to web service for publishing
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
export class FileUploadService {

  constructor(private http: HttpClient  ) { }

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


public uploadFile(jsonObj: any ): Observable<any>
{
 // Take url for item post from Swagger API
  return this.http.post(('/API/Productlist.jsp'),JSON.stringify(jsonObj),this.httpOptions);
}

}
