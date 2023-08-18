import { IPost } from "./post.model";

export interface IUser {
    id?: number;
    name?: string;
    post?: IPost[];
    follows?: IUser[];

}
