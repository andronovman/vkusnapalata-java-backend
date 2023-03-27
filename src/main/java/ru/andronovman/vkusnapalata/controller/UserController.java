package ru.andronovman.vkusnapalata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.andronovman.vkusnapalata.controller.request.ChangeUserPassword;
import ru.andronovman.vkusnapalata.controller.request.ChangeUserTel;
import ru.andronovman.vkusnapalata.entity.UserEntity;
import ru.andronovman.vkusnapalata.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/active")
    public ResponseEntity getActiveUsers() {
        return ResponseEntity.ok(userService.getActiveUsers());
    }

    @GetMapping("/inactive")
    public ResponseEntity getInactiveUsers() {
        return ResponseEntity.ok(userService.getInactiveUsers());
    }

    @GetMapping("/clients")
    public ResponseEntity getClients() {
        return ResponseEntity.ok(userService.getClients());
    }

    @GetMapping("/managers")
    public ResponseEntity getManagers() {
        return ResponseEntity.ok(userService.getManagers());
    }

    @GetMapping("/admins")
    public ResponseEntity getAdmins() {
        return ResponseEntity.ok(userService.getAdmins());
    }

    @PostMapping
    public ResponseEntity registration(@RequestBody UserEntity entity) {
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

    @PutMapping("/changeTel")
    public ResponseEntity changeTel(@RequestBody ChangeUserTel request) {
        return ResponseEntity.ok(userService.changeTel(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.delete(id));
    }
}
