import { IUser } from "./user.model";

export interface IComment {
    id?: number;
    content?: string;
    author?: IUser;
    likes?: IUser[];
}
