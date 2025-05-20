import { ChangeDetectionStrategy, Component } from '@angular/core';

@Component({
  selector: 'am-public-root',
  templateUrl: './public.component.html',
  styleUrls: ['./public.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PublicComponent {}
