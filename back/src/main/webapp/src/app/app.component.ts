import { Component, Injectable, OnInit } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { CommandeService } from './services/commande.service';
import { FormControl } from '@angular/forms';

@Injectable()
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {

  title = 'webapp';
  maCommande : any;
  listeCommandes = [];
  selectedId = "";
  selectedType = "";
  palette = "";
  paletteString = new FormControl('');

  constructor(private commandeService: CommandeService){
  }

  //retourne l'ensemble des numéros de commandes présents sur le serveur pour alimenter la combo
  getListeCommandes(){
    this.commandeService.getListeCommandeFromServer();
    if(this.commandeService.listeCommandes){
      this.listeCommandes = this.commandeService.listeCommandes;
    }
  }

  ngOnInit(): void {
    this.getListeCommandes();
  }

  submitPalette(){
    // lister les elementBocadExpeditions modifiés
    // les envoyer au serveur avec le numéro de palette
    var envoi = {"palette":{},"listeElements":[]};
    envoi.palette = this.paletteString;
    envoi.listeElements = this.getElementsChecked();
    console.log("envoi palette: " + envoi);
  }

  // recherche des eléments bocad expéditions aui ont été cochés
  getElementsChecked():any[]{
    var tab = new Array<any>();
    console.log("maCommande : " + this.maCommande);
    this.maCommande.listBocad.forEach(bocad => {
      bocad.elementExpeditions.forEach(element => {
        if ( element.palette == "$NEWPALETTE$" ){
          tab.push(element);
        }
      });
    });
    return tab;
  }

    // récupération de l'output du composant Commande
  getOutputCommande(maCom : any){
    this.maCommande = maCom;
  }
}

