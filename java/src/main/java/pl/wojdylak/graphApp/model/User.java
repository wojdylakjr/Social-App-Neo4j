package pl.wojdylak.graphApp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;

@Node
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Relationship(type = "ADD_POST")
    private Set<Post> posts;

    @Relationship(type = "FOLLOW")
    @JsonIgnoreProperties(value = {"follows"}, allowSetters = true)
    private Set<User> follows;

    @Relationship(type = "LIKE_POST")
    private Set<Post> likedPosts;

//    @Relationship(type = "LIKE_COMMENT")
//    private Set<Comment> likedComments;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<User> getFollows() {
        return follows;
    }

    public void setFollows(Set<User> follows) {
        this.follows = follows;
    }

    public Set<Post> getLikedPosts() {
        return likedPosts;
    }

    public void setLikedPosts(Set<Post> likedPosts) {
        this.likedPosts = likedPosts;
    }

//    public Set<Comment> getLikedComments() {
//        return likedComments;
//    }
//
//    public void setLikedComments(Set<Comment> likedComments) {
//        this.likedComments = likedComments;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        return id != null && id.equals(((User) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
