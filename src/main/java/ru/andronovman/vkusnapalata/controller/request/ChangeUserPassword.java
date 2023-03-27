package ru.andronovman.vkusnapalata.controller.request;

public class ChangeUserPassword {
    private Long tel;
    private String oldPassword;
    private String newPassword;

    public ChangeUserPassword() {
    }

    public Long getTel() {
        return tel;
    }

    public void setTel(Long tel) {
        this.tel = tel;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
