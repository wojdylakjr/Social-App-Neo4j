import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FollowedPostsListComponent } from './followed-posts-list/followed-posts-list.component';
import { PostsListComponent } from './posts-list/posts-list.component';
import { UsersListComponent } from './users-list/users-list.component';

const routes: Routes = [

  { path: 'users/:id', component: UsersListComponent },
  { path: 'users/:id/follows/posts', component: FollowedPostsListComponent },
  { path: 'users/:id/posts', component: PostsListComponent }
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
