import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemPageTopRigthComponent } from './item-page-top-rigth.component';

describe('ItemPageTopRigthComponent', () => {
  let component: ItemPageTopRigthComponent;
  let fixture: ComponentFixture<ItemPageTopRigthComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ItemPageTopRigthComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ItemPageTopRigthComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
