import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { IPost } from '../models/post.model';
import { ApiService } from '../services/api.service';

@Component({
  selector: 'app-post-card',
  templateUrl: './post-card.component.html',
  styleUrls: ['./post-card.component.scss']
})
export class PostCardComponent implements OnInit {

  @Input() post!: IPost;
  isPostLiked = false;
  showLikesList = false;
  currentUserId = 0;

  addCommentForm!: FormGroup;
  constructor(private formBuilder: FormBuilder, private route: ActivatedRoute, private apiService: ApiService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      console.log('The id of this route is: ', params['id']);
      console.log(params)
      this.currentUserId = params['id'];
    });
    this.addCommentForm = this.formBuilder.group({
      content: [''],
      postId: [''],
      authorId: ['']
    })
  }

  addComment() {
    this.addCommentForm.value["postId"] = this.post.id;
    this.addCommentForm.value["authorId"] = this.currentUserId;
    console.log(this.addCommentForm.value);
    this.apiService.addComment(this.addCommentForm.value)
      .subscribe({
        next: (response) => {
          console.log("Posts read succsefully");
          this.post = response;
          console.log(this.post);
        },
        error: () => {
          console.log("Error while geting posts")
        }
      })
  }

  openLikesList() {
    this.showLikesList = true;
  }

  hideLikesList() {
    this.showLikesList = false;
  }

  toogleLikesList() {
    this.showLikesList = !this.showLikesList;
  }

  likePost() {
    let addForm = this.formBuilder.group({
      postId: [this.post.id],
      userId: [this.currentUserId],
    });
    console.log(addForm.value);
    this.isPostLiked = true;
    this.apiService.addLikeToPost(addForm.value)
      .subscribe({
        next: (response) => {
          console.log("Posts read succsefully");
          this.post = response;
        },
        error: () => {
          console.log("Error while geting posts")
        }
      })
  }
}
