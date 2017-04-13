package com.lozyukartem.controller;

import com.lozyukartem.dto.CommentDto;
import com.lozyukartem.exception.ServiceException;
import com.lozyukartem.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseEntity<Collection<CommentDto>> getComments(@RequestParam String page, @RequestParam String size) {
        try {
            Collection<CommentDto> dtoCollection = commentService.getAll(page, size);
            return new ResponseEntity<Collection<CommentDto>>(dtoCollection, HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<Collection<CommentDto>>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getComment(@PathVariable String id) {
        try {
            CommentDto commentDto = commentService.get(id);
            return new ResponseEntity<CommentDto>(commentDto, HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<CommentDto>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto) {
        try {
            CommentDto resultDto = commentService.add(commentDto);
            return new ResponseEntity<CommentDto>(resultDto, HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<CommentDto>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto) {
        try {
            CommentDto resultDto = commentService.update(commentDto);
            return new ResponseEntity<CommentDto>(resultDto, HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<CommentDto>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommentDto> deleteComment(@PathVariable String id) {
        try {
            CommentDto resultDto = commentService.delete(id);
            return new ResponseEntity<CommentDto>(resultDto, HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<CommentDto>(HttpStatus.NOT_FOUND);
        }
    }
}
