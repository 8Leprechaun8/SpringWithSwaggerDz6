package org.example.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.example.security.entity.UserRole;
import org.springframework.hateoas.RepresentationModel;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Schema(description = "DTO пользователя в ответе")
public class UserDtoResponse extends RepresentationModel<UserDtoResponse> {

    @Schema(description = "Id пользователя", example = "3d299682-5c24-4442-ad8a-732701a930d7")
    private UUID id;

    @Schema(description = "Логин пользователя", example = "Ivanov")
    private String username;

    @Schema(description = "Почта пользователя", example = "ivanov@mail.ru")
    private String email;

    @Schema(description = "Возраст пользователя", example = "30")
    private Integer age;

    @Schema(description = "Дата и время создания пользователя", example = "2026-06-16T05:26:15.123456789Z")
    private Instant createdAt;

    @Schema(description = "Роли пользователя")
    private Set<UserRole> userRoleSet;

    public UserDtoResponse() {
    }

    public UserDtoResponse(
            UUID id,
            String username,
            String email,
            Integer age,
            Instant createdAt,
            Set<UserRole> userRoleSet
    ) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.age = age;
        this.createdAt = createdAt;
        this.userRoleSet = userRoleSet;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Set<UserRole> getUserRoleSet() {
        return userRoleSet;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setUserRoleSet(Set<UserRole> userRoleSet) {
        this.userRoleSet = userRoleSet;
    }
}
