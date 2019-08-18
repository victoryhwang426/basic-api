package me.remind.rest.sandbox.service;

import me.remind.rest.sandbox.dto.ResultMessage;
import me.remind.rest.sandbox.dto.UserRegisterDTO;
import me.remind.rest.sandbox.dto.UserUpdateDTO;

import java.util.UUID;

public interface UserService {
    ResultMessage getUser(UUID id);

    ResultMessage getUsers();

    ResultMessage saveUser(UserRegisterDTO dto);

    ResultMessage updateUser(UserUpdateDTO dto);

    ResultMessage deleteUser(UUID id);
}
