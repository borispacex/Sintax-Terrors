import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DashboardRoutingModule } from './routes/dashboard-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DashboardComponent } from './dashboard.component';
import { InputModule } from '../../modules/shared/input/input.module';
import { AutocompleteDropdownModule } from '../../modules/shared/autocomplete-dropdown/autocomplete-dropdown.module';
import { DataTableModule } from '../../modules/shared/data-table/data-table.module';
import { ChartsModule } from 'ng2-charts';
import { EmployeePieChartComponent } from './employee-pie-chart/employee-pie-chart.component';
import { AssetBarChartComponent } from './asset-bar-chart/asset-bar-chart.component';
import { EmployeeFacade } from '../employee/facades/employee.facade';
import { AssetFacade } from '../asset/facades/asset.facade';

@NgModule({
  declarations: [
    DashboardComponent,
    EmployeePieChartComponent,
    AssetBarChartComponent,
  ],
  imports: [
    CommonModule,
    DashboardRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    InputModule,
    AutocompleteDropdownModule,
    DataTableModule,
    ChartsModule,
  ],
  providers: [EmployeeFacade, AssetFacade],
})
export class DashboardModule {}
