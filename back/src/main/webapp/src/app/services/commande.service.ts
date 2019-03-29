import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable()
export class CommandeService{

    listeCommandes = [];
    commandeSubject = new Subject<any>();
    private commande : any;

    constructor(private httpClient: HttpClient){

    }

    emitCommandeSubject(){
      this.commandeSubject.next(this.commande);
    }

    getListeCommandeFromServer(){
        this.httpClient
        .get<any[]>('http://localhost:8080/listecommande')
        .subscribe(
          (response) => {
            this.listeCommandes = response;
          },
          (error) => {
            console.log('Erreur du get !' + error)
          }
        )
    }

    getCommandeFromServer(numero:String){
      this.httpClient
      .get<any>('http://localhost:8080/commande/numero/' + numero)
      .subscribe(
        (response) => {
          this.commande = response;
          this.emitCommandeSubject();
          console.log('emit commande  : ' + numero)
        },
        (error) => {
          console.log('Erreur du get commande !' + error)
        }
      )
  }
}