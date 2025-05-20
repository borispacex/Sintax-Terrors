import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { PublicRoutingModule } from './routes/public-routing.module';
import { PublicComponent } from './public.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

@NgModule({
  imports: [CommonModule, PublicRoutingModule, FontAwesomeModule],
  declarations: [PublicComponent],
})
export class PublicModule {}
