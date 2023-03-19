import { Component, Input, ViewChild, ElementRef } from "@angular/core";
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css']
})
export class FileUploadComponent  {
  @Input()
  mode: any
  @Input()
  names: any
  @Input()
  url: any
  @Input()
  method: any
  @Input()
  multiple: boolean = true;
  @Input()
  disabled: any
  @Input()
  accept: any
  @Input()
  maxFileSize: any
  @Input()
  auto = true
  @Input()
  withCredentials: any
  @Input()
  invalidFileSizeMessageSummary: any
  @Input()
  invalidFileSizeMessageDetail: any
  @Input()
  invalidFileTypeMessageSummary: any
  @Input()
  invalidFileTypeMessageDetail: any
  @Input()
  previewWidth: any
  @Input()
  chooseLabel = 'Choose header image'
  @Input()
  uploadLabel = 'Upload'
  @Input()
  cancelLabel = 'Cance'
  @Input()
  customUpload: any
  @Input()
  showUploadButton: any
  @Input()
  showCancelButton: any


  @Input()
  dataUriPrefix: any
  @Input()
  deleteButtonLabel: any
  @Input()
  deleteButtonIcon = 'close'
  @Input()
  showUploadInfo: any

  /**
   *
   */


  @ViewChild('fileUpload')
  fileUpload!: ElementRef;

  inputFileName!: string;

  @Input()
  inputFileUrl!: string;

  @Input()
  files: File[] = []

  constructor(private sanitizer: DomSanitizer ) {
  }

  onClick(event: any) {
    if (this.fileUpload)
      this.fileUpload.nativeElement.click()

  }

  onInput(event: any) {

  }

  onFileSelected(event: any) {
    let files = event.dataTransfer ? event.dataTransfer.files : event.target.files;
    console.log('event::::::', event)
    for (let i = 0; i < files.length; i++) {
      let file = files[i];

      //if(!this.isFileSelected(file)){
      if (this.validate(file)) {
        //      if(this.isImage(file)) {
        file.objectURL = this.sanitizer.bypassSecurityTrustUrl((window.URL.createObjectURL(files[i])));
        this.url = file.objectURL ;
        //      }
        if (!this.isMultiple()) {
          this.files = []
        }
        this.files.push(files[i]);
        //  }
      }
      //}
    }
  }

  removeFile(event: any, file: File) {
    let ix
    if (this.files && -1 !== (ix = this.files.indexOf(file))) {
      this.files.splice(ix, 1)
      this.clearInputElement()
    }
  }

  validate(file: File) {
    for (const f of this.files) {
      if (f.name === file.name
        && f.lastModified === file.lastModified
        && f.size === f.size
        && f.type === f.type
      ) {
        return false
      }
    }
    return true
  }

  clearInputElement() {
    this.fileUpload.nativeElement.value = ''
  }


  isMultiple(): boolean {
    return this.multiple
  }

}
