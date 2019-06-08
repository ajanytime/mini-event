import { ActionReducerMap, MetaReducer } from '@ngrx/store';
import { environment } from '../../environments/environment';
import { EventActions, EventActionTypes } from '../actions/event.actions';
import { Event } from '../model/event.model'; 
import { PageActions, PageActionTypes } from '../actions/page.actions';

export interface EventState {
  selectedEvent: Event | null;
  events: Event[] | null;
}

export interface PageState {
  isMainPage: boolean | null;
}

const initialEventState: EventState = {
  selectedEvent: null,
  events: null,
};

const initialPageState: PageState = {
  isMainPage: true
};

export interface State {
  eventState: EventState,
  pageState: PageState
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

export function pageReducer(state: PageState = initialPageState, action: PageActions): PageState {
  switch (action.type) {
    case PageActionTypes.ToggleIsMain: {
      return {
        isMainPage: action.payload
      };
    }
    default:
      return state;
  }
}

export const reducers: ActionReducerMap<State> = {
  eventState: eventReducer,
  pageState: pageReducer
};

export const metaReducers: MetaReducer<State>[] = !environment.production ? [] : [];
