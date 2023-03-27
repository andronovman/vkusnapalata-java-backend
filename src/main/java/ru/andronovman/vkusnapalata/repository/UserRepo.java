package ru.andronovman.vkusnapalata.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.andronovman.vkusnapalata.entity.UserEntity;
import ru.andronovman.vkusnapalata.entity.enums.UserRole;

import java.util.ArrayList;

public interface UserRepo extends CrudRepository<UserEntity, Long> {
    @Query(value = "select * from users where is_active = 'Y'", nativeQuery = true)
    ArrayList<UserEntity> findActiveUsers();
    @Query(value = "select * from users where is_active = 'N'", nativeQuery = true)
    ArrayList<UserEntity> findInactiveUsers();
    @Query(value = "select * from users where is_active = 'Y' AND tel = ?1 AND password = ?2", nativeQuery = true)
    UserEntity findUserByPassword(Long tel, String password);
    @Query(value = "select * from users where is_active = 'Y' AND tel = ?1", nativeQuery = true)
    UserEntity findUserByTel(Long tel);
    ArrayList<UserEntity> findByRole(UserRole role);
}
