import { Action } from '@ngrx/store';

export enum PageActionTypes {
  ToggleIsMain = '[ToggleIsMain] toggle between main page and details page'
}

export class PageAction implements Action {
  type: string;
  payload: boolean;
}

export class ToggleIsMainPage implements Action {
  readonly type = PageActionTypes.ToggleIsMain;
  
  constructor(readonly payload: boolean) {}
}

export type PageActions = ToggleIsMainPage;
