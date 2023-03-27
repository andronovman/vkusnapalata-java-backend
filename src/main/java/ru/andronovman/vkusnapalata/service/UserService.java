package ru.andronovman.vkusnapalata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;
import ru.andronovman.vkusnapalata.controller.request.ChangeUserPassword;
import ru.andronovman.vkusnapalata.controller.request.ChangeUserTel;
import ru.andronovman.vkusnapalata.entity.UserEntity;
import ru.andronovman.vkusnapalata.entity.enums.UserRole;
import ru.andronovman.vkusnapalata.repository.UserRepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    /**
     * Возвращает список активных пользователей
     *
     * @return Список пользователей
     */
    public List<UserEntity> getActiveUsers() {
        List<UserEntity> list = new ArrayList<>();
        userRepo.findActiveUsers().iterator().forEachRemaining(list::add);
        return list;
    }

    /**
     * Возвращает список НЕактивных пользователей
     *
     * @return Список пользователей
     */
    public List<UserEntity> getInactiveUsers() {
        List<UserEntity> list = new ArrayList<>();
        userRepo.findInactiveUsers().iterator().forEachRemaining(list::add);
        return list;
    }

    public List<UserEntity> getClients() {
        List<UserEntity> list = new ArrayList<>();
        userRepo.findByRole(UserRole.CLIENT).iterator().forEachRemaining(list::add);
        return list;
    }

    public List<UserEntity> getManagers() {
        List<UserEntity> list = new ArrayList<>();
        userRepo.findByRole(UserRole.MANAGER).iterator().forEachRemaining(list::add);
        return list;
    }

    public List<UserEntity> getAdmins() {
        List<UserEntity> list = new ArrayList<>();
        userRepo.findByRole(UserRole.ADMIN).iterator().forEachRemaining(list::add);
        return list;
    }

    public UserEntity create(UserEntity entity) {
        if (!userRepo.findById(entity.getTel()).isPresent()) {
            entity.setPassword(DigestUtils.md5DigestAsHex(entity.getPassword().getBytes()).toUpperCase());
            return userRepo.save(entity);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Такой пользователь существует");
        }
    }

    public UserEntity update(UserEntity entity) {
        UserEntity user = userRepo.findById(entity.getTel()).orElse(null);
        if (user != null) {
            user.setName(entity.getName());
            user.setRole(entity.getRole());
            user.setIs_active(entity.getIs_active());
            return userRepo.save(user);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Такой пользователь НЕ существует");
        }
    }

    public HashMap delete(Long id) {
        userRepo.deleteById(id);
        return new HashMap<String, String>() {{
            put("status", "ok");
        }};
    }

    public HashMap changePassword(ChangeUserPassword cup) {
        UserEntity user = userRepo.findUserByPassword(cup.getTel(), DigestUtils.md5DigestAsHex(cup.getOldPassword().getBytes()).toUpperCase());
        if (user != null) {
            user.setPassword(DigestUtils.md5DigestAsHex(cup.getNewPassword().getBytes()).toUpperCase());
            userRepo.save(user);
            return new HashMap<String, String>() {{
                put("status", "ok");
            }};
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Пользователь НЕ найден");
        }
    }

    /**
     * Смена номера телефона у пользователя.
     * Фактически, текущий пользователь выключается и создается новый с ссылкой на текущего.
     *
     * @param cut - прежний номер телефона, новый номер телефона и пароль
     * @return
     */
    public HashMap changeTel(ChangeUserTel cut) {
        if (cut.getTel().equals(cut.getNewTel())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Новый идентификатор совпадает с текущим");
        }

        //Ищем в БД по текущему телефону
        UserEntity user = userRepo.findUserByPassword(cut.getTel(), DigestUtils.md5DigestAsHex(cut.getPassword().getBytes()).toUpperCase());
        if (user == null)
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Пользователь НЕ найден");
        //Проверяем в БД по новому телефону
        UserEntity newUser = userRepo.findUserByTel(cut.getNewTel());
        if (newUser != null)
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("Пользователь \"%s\" уже зарегистрирован", cut.getNewTel()));

        //Вырубаем текущего
        user.setIs_active(false);
        userRepo.save(user);

        //Создаем нового
        newUser = new UserEntity();
        newUser.setTel(cut.getNewTel());
        newUser.setPrevUser(user);
        newUser.setName(user.getName());
        newUser.setPassword(user.getPassword());
        newUser.setRole(user.getRole());
        userRepo.save(newUser);

        return new HashMap<String, String>() {{
            put("status", "ok");
        }};


    }

}
