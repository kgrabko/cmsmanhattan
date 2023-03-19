import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { FileUploadService } from '../file-upload.service';
import { PublishItemPageService } from '../publish-item-page.service';

@Component({
  selector: 'app-item-page-bottom-rigth',
  templateUrl: './item-page-bottom-rigth.component.html',
  styleUrls: ['./item-page-bottom-rigth.component.css']
})
export class ItemPageBottomRigthComponent implements OnInit {

  constructor(private _formBuilder: FormBuilder ,private publishItemPageService: PublishItemPageService, fileUploadService: FileUploadService ,   private _http: HttpClient) { }
  data: any; // map this var to wen form fields

  ngOnInit(): void {
      this.publishItemPageService.getPageItem().subscribe((response) => {
      this.data = response;
      console.log("data: " + response);
    });
  }

}
