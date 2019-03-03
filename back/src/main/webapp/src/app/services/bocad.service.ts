import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class BocadService{

    constructor( private httpClient: HttpClient){

    }

    getBocadFromServer(id: number){
        this.httpClient
        .get<any[]>('http://localhost:8080/bocad/' + id)
        .subscribe(
          (response) => {
            return response;
          },
          (error) => {
            console.log('Erreur du get !' + error)
          }
        )
    }
}