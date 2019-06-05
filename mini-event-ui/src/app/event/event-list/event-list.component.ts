import { ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { select, Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { LoadEvents } from 'src/app/actions/event.actions';
import { State } from 'src/app/reducers';
import { Event } from '../../model/event.model';
import { EventService } from '../event.service';

@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  styleUrls: ['./event-list.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class EventListComponent implements OnInit {

  private searchForm: FormGroup = new FormGroup({
    dateRange: new FormControl(''),
  });

  private events$: Observable<Event[]> = this.store.pipe(select(state => state.eventState.events));

  constructor(protected eventService: EventService,
    protected changeDetectorRef: ChangeDetectorRef,
    protected store: Store<State>) { }

  ngOnInit() {
    this.store.dispatch(new LoadEvents({ start: null, end: null }));
  }

  reset() {
    this.searchForm.value.dateRange = null;
    this.changeDetectorRef.markForCheck();
  }

  onSubmit() {
    let dateRange: any = this.searchForm.value.dateRange;
    const start: any = !!dateRange ? dateRange[0] : null;
    const end: any = !!dateRange ? dateRange[1] : null;
    this.store.dispatch(new LoadEvents({ start: start, end: end }));
  }

}
