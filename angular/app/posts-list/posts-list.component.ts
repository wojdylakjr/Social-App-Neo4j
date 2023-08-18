import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { IPost } from '../models/post.model';
import { ApiService } from '../services/api.service';

@Component({
  selector: 'app-posts-list',
  templateUrl: './posts-list.component.html',
  styleUrls: ['./posts-list.component.scss']
})
export class PostsListComponent implements OnInit {
  posts: IPost[] = [];
  currentUserId = 0;

  addPostForm!: FormGroup;
  constructor(private apiService: ApiService, private formBuilder: FormBuilder, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.getPosts();
    this.route.params.subscribe(params => {
      console.log('The id of this route is: ', params['id']);
      console.log(params)
      this.currentUserId = params['id'];
    });
    this.addPostForm = this.formBuilder.group({
      content: [''],
      title: [''],
      authorId: ['']
    })
  }

  getPosts() {
    this.apiService.getAllPosts()
      .subscribe({
        next: (response) => {
          this.posts = response;
          console.log("Posts read succsefully");
        },
        error: () => {
          console.log("Error while geting posts")
        }
      })
  }

  addPost() {
    this.addPostForm.value["authorId"] = this.currentUserId;
    console.log(this.addPostForm.value);
    this.apiService.addPost(this.addPostForm.value)
      .subscribe({
        next: (response) => {
          console.log("Posts read succsefully");
          this.posts = response;
        },
        error: () => {
          console.log("Error while geting posts")
        }
      })
  }

}
