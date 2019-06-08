import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';
import {Location} from '@angular/common';
import { Store, select } from '@ngrx/store';
import { State } from './reducers';
import { Observable } from 'rxjs/internal/Observable';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class AppComponent implements OnInit {
  title = 'EVENT APP';
  
  private isMainPage$: Observable<boolean> = this.store.pipe(select(state => state.pageState.isMainPage));

  constructor(protected location: Location, protected store: Store<State>) {}

  ngOnInit() {
  }

  back() {
    this.location.back();
  }

}
