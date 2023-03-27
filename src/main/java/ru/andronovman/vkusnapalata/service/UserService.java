package ru.andronovman.vkusnapalata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;
import ru.andronovman.vkusnapalata.controller.request.ChangeUserPassword;
import ru.andronovman.vkusnapalata.entity.UserEntity;
import ru.andronovman.vkusnapalata.repository.UserRepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    public List<UserEntity> getAll() {
        List<UserEntity> list = new ArrayList<>();
        userRepo.findActiveUsers().iterator().forEachRemaining(list::add);
        return list;
    }
    public UserEntity create(UserEntity entity) {
        if(!userRepo.findById(entity.getTel()).isPresent()) {
            entity.setPassword(DigestUtils.md5DigestAsHex(entity.getPassword().getBytes()).toUpperCase());
            return userRepo.save(entity);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Такой пользователь существует");
        }
    }

    public UserEntity update(UserEntity entity) {
        UserEntity user = userRepo.findById(entity.getTel()).orElse(null);
        if(user != null) {
            entity.setPassword(user.getPassword());
            return userRepo.save(entity);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Такой пользователь НЕ существует");
        }
    }

    public HashMap changePassword(ChangeUserPassword cup) {
        UserEntity user = userRepo.findUserByPassword(cup.getTel(), DigestUtils.md5DigestAsHex(cup.getOldPassword().getBytes()).toUpperCase());
        if(user != null) {
            user.setPassword(DigestUtils.md5DigestAsHex(cup.getNewPassword().getBytes()).toUpperCase());
            userRepo.save(user);
            return new HashMap<String, String>(){{ put("status", "ok"); }};
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Пользователь НЕ найден");
        }
    }

    public HashMap delete(Long id) {
        userRepo.deleteById(id);
        return new HashMap<String, String>(){{ put("status", "ok"); }};
    }
}
