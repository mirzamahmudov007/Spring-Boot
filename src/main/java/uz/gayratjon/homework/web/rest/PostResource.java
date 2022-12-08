package uz.gayratjon.homework.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.gayratjon.homework.model.Post;
import uz.gayratjon.homework.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostResource {
    private final PostService postService;

    public PostResource(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public ResponseEntity getAll(){
        Object result = postService.findAll();
        return ResponseEntity.ok(result);
    }
    @PostMapping("/posts")
    public ResponseEntity create(@RequestBody Post post){
        Post result = postService.save(post);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity update(@PathVariable Long id,
                                 @RequestBody Post post){
        Post result = postService.update(id, post);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/posts/params")
    public ResponseEntity getAllByParam(@RequestParam Long postId/*,
                                        @RequestParam Long id*/){
        List<Post> result = postService.findAllByQueryParam(postId/*, id*/);
        return ResponseEntity.ok(result);
    }
/*
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity delete(@RequestParam Long postId){
        postService.delete(postId);
        return ResponseEntity.ok("Deleted");
    }
*/
}
