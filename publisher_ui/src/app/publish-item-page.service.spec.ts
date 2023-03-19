import { TestBed } from '@angular/core/testing';

import { PublishItemPageService } from './publish-item-page.service';

describe('PublishItemPageService', () => {
  let service: PublishItemPageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PublishItemPageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
