import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { IComment } from '../models/comment.model';
import { ApiService } from '../services/api.service';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.scss']
})
export class CommentComponent implements OnInit {

  @Input() comment!: IComment;
  responseComment!: IComment;
  isCommentLiked = false;
  showLikesList = false
  currentUserId = 0;
  constructor(private apiService: ApiService, private route: ActivatedRoute, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    // this.route.paramMap.subscribe(params => {
    //   console.log(params.get('id'));
    // });
    // console.log("URL")
    // console.log(this.route)
    this.route.params.subscribe(params => {
      console.log('The id of this route is: ', params['id']);
      console.log(params)
      this.currentUserId = params['id'];
    });
  }
  toogleLikesList() {
    this.showLikesList = !this.showLikesList;
  }

  openLikesList() {
    this.showLikesList = true;
  }


  hideLikesList() {
    this.showLikesList = false;
  }

  addLikeToComment() {
    let addForm = this.formBuilder.group({
      commentId: [this.comment.id],
      userId: [this.currentUserId],
    });
    console.log(addForm.value);
    this.isCommentLiked = true;
    this.apiService.addLikeToComment(addForm.value)
      .subscribe({
        next: (response) => {
          console.log("Posts read succsefully");
          this.comment = response;
        },
        error: () => {
          console.log("Error while geting posts")
        }
      })
  }

}

