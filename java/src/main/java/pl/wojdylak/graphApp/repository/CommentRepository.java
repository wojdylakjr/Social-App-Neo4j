package pl.wojdylak.graphApp.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import pl.wojdylak.graphApp.model.Comment;

import java.util.Optional;

public interface CommentRepository extends Neo4jRepository<Comment, Long> {
    @Query("CREATE (comment:Comment {content:$content})\n" +
            "WITH comment\n" +
            "MATCH (user:User)\n" +
            "MATCH (post:Post)\n" +
            "WHERE id(user)=$authorId AND id(post) = $postId\n" +
            "CREATE (user)-[:ADD_COMMENT]->(comment)\n" +
            "CREATE (post)-[:HAS]->(comment)")
    void addComment(String content, Long postId, Long authorId);

    @Query("MATCH (user:User)\n" +
            "MATCH (comment:Comment)\n" +
            "WHERE id(user)=$userId AND id(comment) = $commentId\n" +
            "CREATE (user)-[:LIKE_COMMENT]->(comment)\n" +
            "RETURN comment")
    Optional<Comment> likeComment(Long commentId, Long userId);
}