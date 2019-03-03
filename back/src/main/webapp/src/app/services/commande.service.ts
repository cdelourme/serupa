import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class CommandeService{

    listeCommandes = [];

    constructor(private httpClient: HttpClient){

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
}