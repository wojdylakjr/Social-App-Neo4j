package pl.wojdylak.graphApp.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import pl.wojdylak.graphApp.model.Post;
import pl.wojdylak.graphApp.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends Neo4jRepository<User, Long> {
@Query("MATCH (user:User)\n" +
        "MATCH (follow:User)\n" +
        "WHERE id(user)=$userId AND id(follow) = $followUserId\n" +
        "CREATE (user)-[:FOLLOW]->(follow)")
     void addFollow(Long userId, Long followUserId);
}