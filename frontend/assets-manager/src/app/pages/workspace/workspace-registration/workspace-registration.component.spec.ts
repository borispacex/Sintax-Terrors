import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkspaceRegistrationComponent } from './workspace-registration.component';

describe('WorkspaceRegistrationComponent', () => {
  let component: WorkspaceRegistrationComponent;
  let fixture: ComponentFixture<WorkspaceRegistrationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [WorkspaceRegistrationComponent],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkspaceRegistrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
