import { Action } from '@ngrx/store';
import { SearchData } from '../model/search-data.model';
import { Event } from '../model/event.model';

export enum EventActionTypes {
  LoadEvents = '[Events] Load Events',
  LoadEventsSuccess = '[Events] Load Events Success',
  LoadEvent = '[Events] Load Single Event',
  LoadEventSuccess = '[Events] Load Single Event Success'
}

export class EventAction implements Action {
  type: string;
  payload: {
    searchData: SearchData
  };
}

export class LoadEventsSuccess implements Action {
  readonly type = EventActionTypes.LoadEventsSuccess;
  
  constructor(readonly payload: Event[]) {}
}

export class LoadEvents implements Action {
  readonly type = EventActionTypes.LoadEvents;

  constructor(readonly payload: SearchData) {}
}

export class LoadEvent implements Action {
  readonly type = EventActionTypes.LoadEvent;

  constructor(readonly payload: {id: string}) {}
}

export class LoadEventSuccess implements Action {
  readonly type = EventActionTypes.LoadEventSuccess;
  
  constructor(readonly payload: Event) {}
}

export type EventActions = LoadEvents | LoadEventsSuccess | LoadEvent | LoadEventSuccess;
