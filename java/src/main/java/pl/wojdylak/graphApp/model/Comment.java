package pl.wojdylak.graphApp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;

@Node
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    private String content;

    @Relationship(type = "ADD_COMMENT", direction = Relationship.Direction.INCOMING)
    @JsonIgnoreProperties(value = {"posts", "follows", "likedPosts"}, allowSetters = true)
    private User author;

    @Relationship(type = "LIKE_COMMENT", direction = Relationship.Direction.INCOMING)
    @JsonIgnoreProperties(value = {"posts", "follows", "likedPosts"}, allowSetters = true)
    private Set<User> likes;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Set<User> getLikes() {
        return likes;
    }

    public void setLikes(Set<User> likes) {
        this.likes = likes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Comment)) {
            return false;
        }
        return id != null && id.equals(((Comment) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
