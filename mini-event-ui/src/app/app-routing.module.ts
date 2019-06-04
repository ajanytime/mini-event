import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PageNotFoundComponent } from './page-not-found.component';
import { EventRoutingModule } from './event/event-routing.module';

const routes: Routes = [
  { path: '',
    redirectTo: '/events',
    pathMatch: 'full'
  },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes), EventRoutingModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
