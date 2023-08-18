import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { IUser } from '../models/user.model';
import { ApiService } from '../services/api.service';

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.scss']
})
export class UsersListComponent implements OnInit {

  currentUser!: IUser;
  allUsers!: IUser[];
  // followedUsers!: IUser[];
  displayColumns: string[] = ["id", "name", "action"]
  currentUserId = 0;
  addFollowForm!: FormGroup;
  constructor(private formBuilder: FormBuilder, private apiService: ApiService, private route: ActivatedRoute) { }

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
    this.apiService.getAllUsersWithoutCurrentUser(this.currentUserId)
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
  addComment(userId: number) {
    this.addFollowForm.value["userId"] = this.currentUser.id;
    this.addFollowForm.value["followId"] = userId;
    console.log(this.addFollowForm.value);
    this.apiService.addFollow(this.addFollowForm.value)
      .subscribe({
        next: (response) => {
          console.log("Posts read succsefully");
          this.currentUser = response;
          this.getAllUsers();
        },
        error: () => {
          console.log("Error while geting posts")
        }
      })
  }


}
