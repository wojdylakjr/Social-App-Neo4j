package pl.wojdylak.graphApp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;

@Node
public class Post {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String content;

    @Relationship(type = "HAS")
    private Set<Comment> comments;

    @Relationship(type = "LIKE_POST", direction = Relationship.Direction.INCOMING)
    @JsonIgnoreProperties(value = {"posts", "follows", "likedPosts"}, allowSetters = true)
    private Set<User> likes;

    @Relationship(type = "ADD_POST", direction = Relationship.Direction.INCOMING)
    @JsonIgnoreProperties(value = {"posts", "follows", "likedPosts"}, allowSetters = true)
    private User author;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<User> getLikes() {
        return likes;
    }

    public void setLikes(Set<User> likes) {
        this.likes = likes;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Post)) {
            return false;
        }
        return id != null && id.equals(((Post) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
