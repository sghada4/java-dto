package com.example.demo.controllers;

import com.example.demo.config.JwtService;
import com.example.demo.dtos.PostDTO;
import com.example.demo.models.Post;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.PostService;
import com.example.demo.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @GetMapping("/all")
    public List<PostDTO> getAllPostsDTO(){
        return postService.getAllPostsDTO();
    }

    @PostMapping("/new")
    public PostDTO createPost(@RequestBody Post post, HttpServletRequest request){
        // Extract token from Authorization header
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            Long userId = jwtService.extractUserId(token);
            if (userRepository.findById(userId).isPresent()) {
                User user = userRepository.findById(userId).get();
                post.setPostedBy(user);
                return postService.createPost(post);
            }
            throw new RuntimeException("User not found");
        }
        throw new RuntimeException("Authorization header is missing or invalid");
    }

    @PutMapping("/edit/{id}")
    public PostDTO updatePost(@PathVariable("id") Long id, @RequestBody Post post){
        return postService.updatePost(id, post);
    }

    @GetMapping("/show/{id}")
    public PostDTO getOnePost(@PathVariable("id") Long id){
        return postService.getPostByIdDTO(id);
    }
}
