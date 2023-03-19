import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemPageCenterComponent } from './item-page-center.component';

describe('ItemPageCenterComponent', () => {
  let component: ItemPageCenterComponent;
  let fixture: ComponentFixture<ItemPageCenterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ItemPageCenterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ItemPageCenterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
