import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TopbarComponent } from './topbar/topbar.component';

import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { PostCardComponent } from './post-card/post-card.component';
import { PostsListComponent } from './posts-list/posts-list.component';
import { CommentComponent } from './comment/comment.component';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatIconModule } from '@angular/material/icon';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LikesListComponent } from './likes-list/likes-list.component';
import { UsersListComponent } from './users-list/users-list.component';
import { MatTableModule } from '@angular/material/table';
import { FollowedPostsListComponent } from './followed-posts-list/followed-posts-list.component';
import { CurrentUserListComponent } from './current-user-list/current-user-list.component';


@NgModule({
  declarations: [
    AppComponent,
    TopbarComponent,
    PostCardComponent,
    PostsListComponent,
    CommentComponent,
    LikesListComponent,
    UsersListComponent,
    FollowedPostsListComponent,
    CurrentUserListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatSelectModule,
    MatIconModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatTableModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
