package ru.andronovman.vkusnapalata.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.andronovman.vkusnapalata.entity.UserEntity;

import java.util.ArrayList;

public interface UserRepo extends CrudRepository<UserEntity, Long> {
    @Query(value = "select * from users where is_active = 'Y'", nativeQuery = true)
    ArrayList<UserEntity> findActiveUsers();
    @Query(value = "select * from users where is_active = 'Y' AND tel = ?1 AND password = ?2", nativeQuery = true)
    UserEntity findUserByPassword(Long tel, String password);
}
