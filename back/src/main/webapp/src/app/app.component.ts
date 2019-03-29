import { Component, Injectable, OnInit } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { CommandeService } from './services/commande.service';

@Injectable()
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {

  title = 'webapp';
  maCommande = [];
  listeCommandes = [];
  selectedId = "";

  constructor(private commandeService: CommandeService){
  }

  getListeCommandes(){
    this.commandeService.getListeCommandeFromServer();
    if(this.commandeService.listeCommandes){
      this.listeCommandes = this.commandeService.listeCommandes;
    }
  }

  ngOnInit(): void {
    this.getListeCommandes();
  }
}

