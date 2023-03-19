import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemPageTopLeftComponent } from './item-page-top-left.component';

describe('ItemPageTopLeftComponent', () => {
  let component: ItemPageTopLeftComponent;
  let fixture: ComponentFixture<ItemPageTopLeftComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ItemPageTopLeftComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ItemPageTopLeftComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
