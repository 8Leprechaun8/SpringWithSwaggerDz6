package org.example.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "DTO пользователя на обновление")
public class UserDtoForUpdating {

    @Schema(description = "Id пользователя", example = "3d299682-5c24-4442-ad8a-732701a930d7")
    private UUID id;

    @Schema(description = "Логин пользователя", example = "Ivanov")
    private String username;

    @Schema(description = "Пароль пользователя", example = "123")
    private String password;

    @Schema(description = "Почта пользователя", example = "ivanov@mail.ru")
    private String email;

    @Schema(description = "Возраст пользователя", example = "30")
    private Integer age;

    public UserDtoForUpdating() {
    }

    public UserDtoForUpdating(UUID id, String username, String password, String email, Integer age) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Integer getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public void setId(UUID id) {
        this.id = id;
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
