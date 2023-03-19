import { Component, Input, ViewChild, ElementRef , OnInit } from "@angular/core";
import { FormBuilder, Validators } from '@angular/forms';
import { PublishItemPageService } from '../publish-item-page.service';
//import xml2js from 'xml2js';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FileUploadService } from "../file-upload.service";

@Component({
  selector: 'app-item-page-center',
  templateUrl: './item-page-center.component.html',
  styleUrls: ['./item-page-center.component.css']
})
export class ItemPageCenterComponent implements OnInit {

  panelOpenState = false;

  descriptionFormGroup = this._formBuilder.group({
    nameCtrl: ['', Validators.required],
    shortNameCtrl: ['', Validators.required],
    descriptionCtrl: ['', Validators.required],
  });
  secondFormGroup = this._formBuilder.group({
    secondCtrl: ['', Validators.required],
  });
  thirdFormGroup = this._formBuilder.group({
    thirdCtrl: ['', Validators.required],
  });
  imagesFormGroup = this._formBuilder.group({
    imageNameCtrl: ['', Validators.required],
    imageIdCtrl: ['', Validators.required],
    bigimageNameCtrl: ['', Validators.required],
    bigimageIdCtrl: ['', Validators.required],
  });
  isLinear = false;


  constructor(private _formBuilder: FormBuilder ,private publishItemPageService: PublishItemPageService, fileUploadService: FileUploadService ,   private _http: HttpClient) { }
  data: any; // map this var to wen form fields

  ngOnInit(): void {
      this.publishItemPageService.getPageItem().subscribe((response) => {
      this.data = response;
      console.log("data: " + response);
    });
  }



  @ViewChild('fileUpload')
  fileUpload!: ElementRef;

  onClick(event: any) {
    if (this.fileUpload)
      this.fileUpload.nativeElement.click()

  }

}
