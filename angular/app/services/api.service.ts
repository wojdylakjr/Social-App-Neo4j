import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { HttpClientModule } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) {
  }

  getAllUsers() {
    return this.http.get<any>("http://localhost:8080/api/users");
  }

  getAllUsersWithoutCurrentUser(userId: number) {
    return this.http.get<any>("http://localhost:8080/api/" + userId + "/users");
  }

  getUserById(userId: number) {
    return this.http.get<any>("http://localhost:8080/api/users/" + userId);
  }
  getAllPosts() {
    return this.http.get<any>("http://localhost:8080/api/posts");
  }

  getAllFollowedPosts(userId: number) {
    return this.http.get<any>("http://localhost:8080/api/users/" + userId + "/follows/posts");
  }

  addComment(data: any) {
    return this.http.post<any>("http://localhost:8080/api/comments", data);
  }

  addFollow(data: any) {
    return this.http.post<any>("http://localhost:8080/api/users/follows", data);
  }

  getCommentById(id: number) {
    return this.http.get<any>("http://localhost:8080/api/comments/" + id);
  }


  addLikeToComment(data: any) {
    return this.http.post<any>("http://localhost:8080/api/comments/like", data);
  }


  addLikeToPost(data: any) {
    return this.http.post<any>("http://localhost:8080/api/posts/like", data);
  }

  addPost(data: any) {
    return this.http.post<any>("http://localhost:8080/api/posts", data);
  }
}
