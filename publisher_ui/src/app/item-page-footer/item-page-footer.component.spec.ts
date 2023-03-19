import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemPageFooterComponent } from './item-page-footer.component';

describe('ItemPageFooterComponent', () => {
  let component: ItemPageFooterComponent;
  let fixture: ComponentFixture<ItemPageFooterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ItemPageFooterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ItemPageFooterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
