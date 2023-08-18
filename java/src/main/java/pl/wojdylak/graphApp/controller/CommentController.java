package pl.wojdylak.graphApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wojdylak.graphApp.model.Comment;
import pl.wojdylak.graphApp.model.Post;
import pl.wojdylak.graphApp.model.dto.CommentDto;
import pl.wojdylak.graphApp.model.dto.LikesDto;
import pl.wojdylak.graphApp.repository.CommentRepository;
import pl.wojdylak.graphApp.repository.PostRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CommentController {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentController(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @PostMapping("/comments")
    public ResponseEntity<Post> createComment(@RequestBody CommentDto comment) {
        this.commentRepository.addComment(comment.getContent(), comment.getPostId(), comment.getAuthorId());
        Optional<Post> postById = this.postRepository.findById(comment.getPostId());
        if(postById.isPresent()){
            return  new ResponseEntity<>(postById.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/comments/like")
    public ResponseEntity<Comment> likeComment(@RequestBody LikesDto like){
        Optional<Comment> comment = this.commentRepository.likeComment(like.getCommentId(), like.getUserId());
        Optional<Comment> commentById = this.commentRepository.findById(like.getCommentId());
        if(commentById.isPresent()){
            return  new ResponseEntity<>(commentById.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<Comment> getAllPosts(@PathVariable Long id){
        Optional<Comment> comment = this.commentRepository.findById(id);
        if(comment.isPresent()){
            return  new ResponseEntity<>(comment.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
