package pl.wojdylak.graphApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wojdylak.graphApp.model.Post;
import pl.wojdylak.graphApp.model.User;
import pl.wojdylak.graphApp.model.dto.FollowDto;
import pl.wojdylak.graphApp.model.dto.LikesDto;
import pl.wojdylak.graphApp.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> userById =this.userRepository.findById(id);
        if(userById.isPresent()){
            return  new ResponseEntity<>(userById.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/{id}/users")
    public List<User> getAllUsersWithoutCurrentUser(@PathVariable Long id) {
        Optional<User> byId = this.userRepository.findById(id);
        List<User> all = this.userRepository.findAll();
        List<User> collect = all.stream()
                .filter(user -> !user.getId().equals(id))
                .collect(Collectors.toList());
     collect.removeAll(byId.get().getFollows());
     return collect;
    }

    @GetMapping("/users/{id}/posts")
    public Set<Post> getUserPosts(@PathVariable Long id) {
        return this.userRepository.findById(id).get().getPosts();
    }



    @GetMapping("/users/{id}/follows")
    public Set<User> getUserFollows(@PathVariable Long id) {
        return this.userRepository.findById(id).get().getFollows();
    }

    @PostMapping("/users/follows")
    public ResponseEntity<User> followUser(@RequestBody FollowDto follow){
        this.userRepository.addFollow(follow.getUserId(), follow.getFollowId());
        Optional<User> userById = this.userRepository.findById(follow.getUserId());
        if(userById.isPresent()){
            return  new ResponseEntity<>(userById.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/users/{id}/follows/posts")
    public List<Post> getUserFollowedPosts(@PathVariable Long id) {
        Optional<User> userById =this.userRepository.findById(id);
        return userById.map(value -> value.getFollows()
                .stream()
                .flatMap(user -> user.getPosts().stream())
                .collect(Collectors.toList())).orElse(null);
    }
}
