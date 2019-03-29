import { Component, Input, OnInit } from '@angular/core';
import { BocadService } from '../services/bocad.service';

@Component({
  selector: 'app-bocad',
  templateUrl: './bocad.component.html',
  styleUrls: ['./bocad.component.scss']
})
export class BocadComponent implements OnInit {

  @Input() bocad: any;
  //bocad :any;

  constructor(private bocadService:BocadService) { }

  ngOnInit() {
  }

  getBocad(){
    //this.bocad = this.bocadService.getBocadFromServer(this.bocadId);
  }

}
