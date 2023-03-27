package ru.andronovman.vkusnapalata.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @Column(name = "tel")
    private Long        tel;

    @Column(nullable = false, length = 65)
    private String      name;

    @Column(nullable = false, length = 45)
    private String      password;

    @Column(nullable = false, length = 1)
    private Character   is_active = 'Y';

    public UserEntity() {
    }

    public Long getTel() {
        return tel;
    }

    public void setTel(Long tel) {
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Character getIs_active() {
        return is_active;
    }

    public void setIs_active(Character is_active) {
        this.is_active = is_active;
    }
}
