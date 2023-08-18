import { Component, Input, OnInit } from '@angular/core';
import { IUser } from '../models/user.model';

@Component({
  selector: 'app-likes-list',
  templateUrl: './likes-list.component.html',
  styleUrls: ['./likes-list.component.scss']
})
export class LikesListComponent implements OnInit {


  @Input() likes!: IUser[];


  constructor() { }

  ngOnInit(): void {
  }

}
