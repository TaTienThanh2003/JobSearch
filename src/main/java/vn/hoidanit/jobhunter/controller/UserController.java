package vn.hoidanit.jobhunter.controller;

import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.service.error.IdInvalidException;
import vn.hoidanit.jobhunter.service.error.UserService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class UserController {
    private final UserService userService;// final k gán lại dc=> k ghi đề

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")

    public ResponseEntity<User> CreateUser(@RequestBody User postManUser) {
        User newuser = this.userService.handleCreateUser(postManUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(newuser);
    }

    @PutMapping("users/{id}")
    public ResponseEntity<User> putMethodName(@RequestBody User user) {
        this.userService.updateUser(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserId(@PathVariable("id") long id) {
        User getid = this.userService.getUser(id);
        return ResponseEntity.ok(getid);
    }

    @GetMapping("/users")
    public List<User> getAllUser() {
        return this.userService.getAllUser();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> Delete(@PathVariable("id") long id) throws IdInvalidException {
        if (id > 1500) {
            throw new IdInvalidException("id không lớn hơn 1500");
        }
        this.userService.handleDelete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
// req.body-> post
// req.param->get