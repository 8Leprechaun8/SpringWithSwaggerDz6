package org.example.security.dto;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO пользователя на сохранение")
public class UserDtoForSaving {

    @Schema(description = "Логин пользователя", example = "Ivanov")
    private String username;

    @Schema(description = "Пароль пользователя", example = "123")
    private String password;

    @Schema(description = "Почта пользователя", example = "ivanov@mail.ru")
    private String email;

    @Schema(description = "Возраст пользователя", example = "30")
    private Integer age;

    public UserDtoForSaving() {
    }

    public UserDtoForSaving(String username, String password, String email, Integer age) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
