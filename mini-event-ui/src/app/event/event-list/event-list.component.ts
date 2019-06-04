import { Component, OnInit, ChangeDetectionStrategy, ChangeDetectorRef } from '@angular/core';
import { EventService } from '../event.service';
import { Event } from '../../model/event.model';

@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  styleUrls: ['./event-list.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class EventListComponent implements OnInit {

  constructor(protected eventService: EventService, protected changeDetectorRef: ChangeDetectorRef) {}

  ngOnInit() {
    this.eventService.getEvents().subscribe((events: Event[]) => {
      console.log(events);
    });
  }

}
