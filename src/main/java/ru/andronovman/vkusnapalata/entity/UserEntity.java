package ru.andronovman.vkusnapalata.entity;

import ru.andronovman.vkusnapalata.entity.converter.BooleanAttributeConverter;
import ru.andronovman.vkusnapalata.entity.enums.UserRole;

import javax.persistence.*;

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

    @Column(name = "is_active", nullable = false, length = 1)
    @Convert(converter = BooleanAttributeConverter.class)
    private Boolean     is_active = true;

    @Column(nullable = false, length = 25)
    @Enumerated(EnumType.STRING)
    private UserRole    role = UserRole.CLIENT;

    @ManyToOne
    @JoinColumn(name = "user_ref_id", referencedColumnName = "tel")
    private UserEntity  prevUser;

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

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserEntity getPrevUser() {
        return prevUser;
    }

    public void setPrevUser(UserEntity prevUser) {
        this.prevUser = prevUser;
    }

}
