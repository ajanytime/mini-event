import { Injectable } from '@angular/core';
import { Actions, Effect, ofType } from '@ngrx/effects';
import { Store } from '@ngrx/store';
import { of } from 'rxjs';
import { map, switchMap } from 'rxjs/operators';
import { EventActionTypes, LoadEvent, LoadEvents, LoadEventsSuccess, LoadEventSuccess } from '../actions/event.actions';
import { EventService } from '../event/event.service';
import { Event } from '../model/event.model';
import { State } from '../reducers';
@Injectable()
export class EventEffects {

  @Effect()
  getEvents$ = this.actions$.pipe(
    ofType<LoadEvents>(EventActionTypes.LoadEvents),
    map(action => action.payload),
    switchMap((action) => this.eventService.getEvents(action.start, action.end)),
    switchMap((events: Event[]) => of(new LoadEventsSuccess(events)))
  );

  @Effect()
  getEvent$ = this.actions$.pipe(
    ofType<LoadEvent>(EventActionTypes.LoadEvent),
    map(action => action.payload.id),
    switchMap((action) => this.eventService.getEventDetails(action)),
    switchMap((event: Event) => of(new LoadEventSuccess(event)))
  );

  constructor(protected actions$: Actions,
    protected store: Store<State>,
    protected eventService: EventService) { }

}
