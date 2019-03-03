import { Component, Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { CommandeService } from './services/commande.service';

@Injectable()
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent {
  title = 'webapp';
  maCommande = [];
  listeCommandes = [];
  selectedId = "";

  constructor(private commandeService: CommandeService){

  }

  getListeCommandes(){
    this.commandeService.getListeCommandeFromServer();
    this.listeCommandes = this.commandeService.listeCommandes;
  }
}

