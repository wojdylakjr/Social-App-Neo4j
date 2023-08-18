package pl.wojdylak.graphApp.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import pl.wojdylak.graphApp.model.Post;

public interface PostRepository extends Neo4jRepository<Post, Long> {

    @Query("CREATE (post:Post {title: $title, content:$content})\n" +
            "WITH post\n" +
            "MATCH (user:User)\n" +
            "WHERE id(user)=$userId\n" +
            "CREATE (user)-[:ADD_POST]->(post)")
    void addPost(String title, String content, Long userId);

    @Query("MATCH (user:User)\n" +
           "MATCH (post:Post)\n" +
            "WHERE id(user)=$userId AND id(post) = $postId\n" +
            "CREATE (user)-[:LIKE_POST]->(post)")
    void likePost(Long postId, Long userId);
}