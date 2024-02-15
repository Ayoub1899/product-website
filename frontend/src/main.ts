import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import {provideHttpClient} from '@angular/common/http';
import {FormsModule} from "@angular/forms";
import {provideRouter} from "@angular/router";
import {routes} from "./app/app.routes";



bootstrapApplication(AppComponent, {

  providers: [provideHttpClient(), FormsModule, provideRouter(routes)]
})
