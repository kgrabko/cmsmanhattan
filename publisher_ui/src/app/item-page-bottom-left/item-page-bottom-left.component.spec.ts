import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemPageBottomLeftComponent } from './item-page-bottom-left.component';

describe('ItemPageBottomLeftComponent', () => {
  let component: ItemPageBottomLeftComponent;
  let fixture: ComponentFixture<ItemPageBottomLeftComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ItemPageBottomLeftComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ItemPageBottomLeftComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
