import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { HttpClientModule} from '@angular/common/http';
import { BocadComponent } from './bocad/bocad.component';
import { BocadService } from './services/bocad.service'
import { CommandeService } from './services/commande.service';
import { FormsModule } from '@angular/forms';
import { CommandeComponent } from './commande/commande.component'

// import { Observable } from 'rxjs';

@NgModule({
  declarations: [
    AppComponent,
    BocadComponent,
    CommandeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule//,
    //Observable
  ],
  providers: [
    BocadService,
    CommandeService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
