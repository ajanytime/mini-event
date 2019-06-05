import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { eventRoutes } from './event/event-routing.module';

const routes: Routes = [
  ...eventRoutes,
  { path: '', redirectTo: 'events', pathMatch: 'full'},
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
