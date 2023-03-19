import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { FileUploadService } from '../file-upload.service';
import { PublishItemPageService } from '../publish-item-page.service';

@Component({
  selector: 'app-item-page-bottom-left',
  templateUrl: './item-page-bottom-left.component.html',
  styleUrls: ['./item-page-bottom-left.component.css']
})
export class ItemPageBottomLeftComponent implements OnInit {

  constructor(private _formBuilder: FormBuilder ,private publishItemPageService: PublishItemPageService, fileUploadService: FileUploadService ,   private _http: HttpClient) { }
  data: any; // map this var to wen form fields

  ngOnInit(): void {
      this.publishItemPageService.getPageItem().subscribe((response) => {
      this.data = response;
      console.log("data: " + response);
    });
  }

}
