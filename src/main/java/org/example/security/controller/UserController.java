package org.example.security.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.security.dto.UserDtoForSaving;
import org.example.security.dto.UserDtoForUpdating;
import org.example.security.dto.UserDtoResponse;
import org.example.security.entity.UserDetailsAdditional;
import org.example.security.exception.UserFoundException;
import org.example.security.exception.UserNotFoundException;
import org.example.security.mapper.UserMapper;
import org.example.security.service.UserDetailsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Tag(name = "Пользователи", description = "Управление пользователями")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserDetailsManager userDetailsManager;

    private final UserMapper userMapper;

    @Autowired
    public UserController(UserDetailsManager userDetailsManager, UserMapper userMapper) {
        this.userDetailsManager = userDetailsManager;
        this.userMapper = userMapper;
    }

    @Operation(summary = "Регистрация в системе как пользователь (без аутентификации)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешная регистрация пользователя"),
            @ApiResponse(responseCode = "500", description = "Неудача - пользователь с похожим логином уже существует в системе")
    })
    @PostMapping("/signup")
    public void signUp(@RequestBody UserDtoForSaving userDto) throws UserFoundException {
        UserDetailsAdditional userDetails = userMapper.userDtoForSavingToUserDetailsAdditional(userDto);
        userDetailsManager.createUser(userDetails);
    }

    @Operation(summary = "Чтение пользователя по id (с аутентификацией, только для ADMIN)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное получение пользователя по id"),
            @ApiResponse(responseCode = "500", description = "Неудача - пользователь с похожим id уже существует в системе")
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/read/{id}")
    public UserDtoResponse readUser(@Parameter(description = "id", example = "3d299682-5c24-4442-ad8a-732701a930d7")
                                        @PathVariable(name = "id") UUID id) throws UserNotFoundException {
        UserDtoResponse response = userDetailsManager.readUser(id);
        response.add(linkTo(methodOn(UserController.class).readUser(id)).withSelfRel());
        response.add(linkTo(methodOn(UserController.class).readAllUsers()).withRel("all-users"));
        return response;
    }

    @Operation(summary = "Обновление пользователя (с аутентификацией, только для ADMIN)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное обновление пользователя"),
            @ApiResponse(responseCode = "500", description = "Неудача - пользователя не существует в системе")
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update")
    public void updateUser(@RequestBody UserDtoForUpdating userDto) throws UserNotFoundException {
        UserDetailsAdditional userDetails = userMapper.userDtoForUpdatingToUserDetailsAdditional(userDto);
        userDetailsManager.updateUser(userDetails);
    }

    @Operation(summary = "Удаление пользователя по id (с аутентификацией, только для ADMIN)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное удаление пользователя по id"),
            @ApiResponse(responseCode = "500", description = "Неудача - пользователя с похожим id не существует в системе")
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@Parameter(description = "id", example = "3d299682-5c24-4442-ad8a-732701a930d7")
                               @PathVariable(name = "id") UUID id) throws UserNotFoundException {
        userDetailsManager.deleteUser(id);
    }

    @Operation(summary = "Чтение всех пользователей (с аутентификацией, только для ADMIN)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное получение списка всех пользователей")
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/readAll")
    public List<UserDtoResponse> readAllUsers() throws UserNotFoundException {
        List<UserDtoResponse> responseList = userDetailsManager.findAll();
        for (UserDtoResponse response : responseList) {
            response.add(linkTo(methodOn(UserController.class).readUser(response.getId())).withSelfRel());
            response.add(linkTo(methodOn(UserController.class).readAllUsers()).withRel("all-users"));
        }
        return responseList;
    }
}
