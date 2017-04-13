package com.lozyukartem.controller;

import com.lozyukartem.dto.UserDto;
import com.lozyukartem.exception.ServiceException;
import com.lozyukartem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Collection<UserDto>> getUsers(@RequestParam String page, @RequestParam String size) {
        try {
            Collection<UserDto> dtoCollection = userService.getAll(page, size);
            return new ResponseEntity<Collection<UserDto>>(dtoCollection, HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<Collection<UserDto>>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable String id) {
        try {
            UserDto userDto = userService.get(id);
            return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        try {
            UserDto resultDto = userService.add(userDto);
            return new ResponseEntity<UserDto>(resultDto, HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        try {
            UserDto resultDto = userService.update(userDto);
            return new ResponseEntity<UserDto>(resultDto, HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable String id) {
        try {
            UserDto resultDto = userService.delete(id);
            return new ResponseEntity<UserDto>(resultDto, HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
        }
    }
}
