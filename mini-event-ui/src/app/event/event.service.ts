import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { URL } from '../conf/url';
import { Event } from '../model/event.model';

@Injectable({
  providedIn: 'root'
})
export class EventService {

  constructor(protected httpClient: HttpClient) {}

  getEventDetails(id: string): Observable<Event> {
    return <Observable<Event>>this.httpClient.get(URL.EVENTS + "/" + id);
  }

  getEvents(start: Date, end: Date): Observable<Event[]> {
    return <Observable<Event[]>>this.httpClient.post(URL.EVENTS, {start: start, end: end});
  }

}
