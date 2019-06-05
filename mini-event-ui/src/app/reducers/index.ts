import { ActionReducerMap, MetaReducer } from '@ngrx/store';
import { environment } from '../../environments/environment';
import { EventActions, EventActionTypes } from '../actions/event.actions';
import { Event } from '../model/event.model'; 

export interface EventState {
  selectedEvent: Event | null;
  events: Event[] | null;
}

const initialEventState: EventState = {
  selectedEvent: null,
  events: null
};

export interface State {
  eventState: EventState
}

export function eventReducer(state: EventState = initialEventState, action: EventActions): EventState {
  switch (action.type) {
    case EventActionTypes.LoadEventsSuccess: {
      return {
        ...state,
        events: action.payload
      };
    }
    case EventActionTypes.LoadEventSuccess: {
      return {
        ...state,
        selectedEvent: action.payload
      };
    }
    default:
      return state;
  }
}

export const reducers: ActionReducerMap<State> = {
  eventState: eventReducer
};


export const metaReducers: MetaReducer<State>[] = !environment.production ? [] : [];
