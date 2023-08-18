package pl.wojdylak.graphApp.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wojdylak.graphApp.model.Post;
import pl.wojdylak.graphApp.model.dto.LikesDto;
import pl.wojdylak.graphApp.model.dto.PostDto;
import pl.wojdylak.graphApp.repository.PostRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class PostController {
    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/posts")
    public List<Post> getAllPosts(){
        return this.postRepository.findAll();
    }

    @PostMapping("/posts")
    public List<Post> createPost(@RequestBody PostDto post){
         this.postRepository.addPost(post.getTitle(), post.getContent(), post.getAuthorId());
         return this.postRepository.findAll();
    }

    @PostMapping("/posts/like")
    public ResponseEntity<Post> likePost(@RequestBody LikesDto like){
        this.postRepository.likePost(like.getPostId(), like.getUserId());
        Optional<Post> postById = this.postRepository.findById(like.getPostId());
        if(postById.isPresent()){
            return  new ResponseEntity<>(postById.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

