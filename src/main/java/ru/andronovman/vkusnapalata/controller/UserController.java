package ru.andronovman.vkusnapalata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.andronovman.vkusnapalata.controller.request.ChangeUserPassword;
import ru.andronovman.vkusnapalata.entity.UserEntity;
import ru.andronovman.vkusnapalata.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity getUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody UserEntity entity) {
        return ResponseEntity.ok(userService.create(entity));
    }

    @PutMapping
    public ResponseEntity updateUser(@RequestBody UserEntity entity) {
        return ResponseEntity.ok(userService.update(entity));
    }

    @PutMapping("/changePassword")
    public ResponseEntity changePassword(@RequestBody ChangeUserPassword request) {
        return ResponseEntity.ok(userService.changePassword(request));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.delete(id));
    }
}
