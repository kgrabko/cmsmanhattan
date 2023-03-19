import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { FileUploadService } from '../file-upload.service';
import { PublishItemPageService } from '../publish-item-page.service';

@Component({
  selector: 'app-item-page-footer',
  templateUrl: './item-page-footer.component.html',
  styleUrls: ['./item-page-footer.component.css']
})
export class ItemPageFooterComponent implements OnInit {

  constructor(private _formBuilder: FormBuilder ,private publishItemPageService: PublishItemPageService, fileUploadService: FileUploadService ,   private _http: HttpClient) { }
  data: any; // map this var to wen form fields

  ngOnInit(): void {
      this.publishItemPageService.getPageItem().subscribe((response) => {
      this.data = response;
      console.log("data: " + response);
    });
  }

}
