import { Component, OnInit, ChangeDetectionStrategy, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { EventService } from '../event.service';
import { Event } from 'src/app/model/event.model';
import { Observable } from 'rxjs';
import { Store, select } from '@ngrx/store';
import { State } from 'src/app/reducers';
import { LoadEvent } from 'src/app/actions/event.actions';

@Component({
  selector: 'app-event-details',
  templateUrl: './event-details.component.html',
  styleUrls: ['./event-details.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class EventDetailsComponent implements OnInit, OnDestroy {

  private sub: any;
  private event$: Observable<Event> = this.store.pipe(select(state => state.eventState.selectedEvent));

  constructor(protected eventService: EventService, 
    protected route: ActivatedRoute,
    protected store: Store<State>) {}
  
  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      let id: string = params['id'];
      this.store.dispatch(new LoadEvent({id: id}));
    });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

}
