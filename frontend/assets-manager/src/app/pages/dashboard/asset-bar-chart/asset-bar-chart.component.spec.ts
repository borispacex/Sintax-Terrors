import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssetBarChartComponent } from './asset-bar-chart.component';

describe('AssetBarChartComponent', () => {
  let component: AssetBarChartComponent;
  let fixture: ComponentFixture<AssetBarChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AssetBarChartComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AssetBarChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
