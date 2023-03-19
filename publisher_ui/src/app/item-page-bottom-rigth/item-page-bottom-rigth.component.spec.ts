import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemPageBottomRigthComponent } from './item-page-bottom-rigth.component';

describe('ItemPageBottomRigthComponent', () => {
  let component: ItemPageBottomRigthComponent;
  let fixture: ComponentFixture<ItemPageBottomRigthComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ItemPageBottomRigthComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ItemPageBottomRigthComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
