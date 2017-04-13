package com.lozyukartem.controller;

import com.lozyukartem.dto.PostDto;
import com.lozyukartem.exception.ServiceException;
import com.lozyukartem.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<Collection<PostDto>> getPosts(@RequestParam String page, @RequestParam String size) {
        try {
            Collection<PostDto> dtoCollection = postService.getAll(page, size);
            return new ResponseEntity<Collection<PostDto>>(dtoCollection, HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<Collection<PostDto>>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable String id) {
        try {
            PostDto postDto = postService.get(id);
            return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<PostDto>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        try {
            PostDto resultDto = postService.add(postDto);
            return new ResponseEntity<PostDto>(resultDto, HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<PostDto>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto) {
        try {
            PostDto resultDto = postService.update(postDto);
            return new ResponseEntity<PostDto>(resultDto, HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<PostDto>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PostDto> deletePost(@PathVariable String id) {
        try {
            PostDto resultDto = postService.delete(id);
            return new ResponseEntity<PostDto>(resultDto, HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<PostDto>(HttpStatus.NOT_FOUND);
        }
    }
}
