import { IComment } from "./comment.model";
import { IUser } from "./user.model";

export interface IPost {
  id?: number;
  title?: string;
  content?: string;
  comments?: IComment[];
  likes?: IUser[];
  author?: IUser;

}
