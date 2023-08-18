import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { IUser } from '../models/user.model';
import { ApiService } from '../services/api.service';

@Component({
  selector: 'app-current-user-list',
  templateUrl: './current-user-list.component.html',
  styleUrls: ['./current-user-list.component.scss']
})
export class CurrentUserListComponent implements OnInit {


  currentUser!: IUser;
  allUsers!: IUser[];
  // followedUsers!: IUser[];
  displayColumns: string[] = ["id", "name", "action"]
  currentUserId = 0;
  addFollowForm!: FormGroup;
  constructor(private formBuilder: FormBuilder, private apiService: ApiService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.currentUserId = params['id'];
    });
    this.initCurrentUser();
    this.getAllUsers();
    this.addFollowForm = this.formBuilder.group({
      userId: [''],
      followId: ['']
    })
  }


  getAllUsers() {
    this.apiService.getAllUsers()
      .subscribe({
        next: (response) => {
          this.allUsers = response;
          console.log("Users succsefully");
        },
        error: () => {
          alert("Error while geting users")
        }
      })
  }

  initCurrentUser() {
    this.apiService.getUserById(this.currentUserId)
      .subscribe({
        next: (response) => {
          this.currentUser = response;
          console.log(this.currentUser)
          console.log("Current User succsefully");
        },
        error: () => {
          alert("Error while geting user")
        }
      })
  }
  setCurrentUser(userId: number) {
    this.router.navigate(['/users', userId, 'posts']);
  }

}
