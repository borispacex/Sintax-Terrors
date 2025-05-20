import { ChangeDetectionStrategy, Component } from '@angular/core';

@Component({
  selector: 'am-public-footer',
  templateUrl: './public-footer.component.html',
  styleUrls: ['./public-footer.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PublicFooterComponent {}
