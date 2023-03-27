package ru.andronovman.vkusnapalata.controller.request;

public class ChangeUserTel {
    private Long tel;
    private Long newTel;
    private String password;

    public ChangeUserTel() {
    }

    public Long getTel() {
        return tel;
    }

    public void setTel(Long tel) {
        this.tel = tel;
    }

    public Long getNewTel() {
        return newTel;
    }

    public void setNewTel(Long newTel) {
        this.newTel = newTel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
