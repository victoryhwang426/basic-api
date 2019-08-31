package gg.rest.service;

import gg.rest.dto.ResultMessage;
import gg.rest.dto.UserRegisterDTO;

import java.util.UUID;

public interface UserService {
    ResultMessage getUser(UUID id);

    ResultMessage getUsers();

    ResultMessage saveUser(UserRegisterDTO dto);

    ResultMessage updateUser(UserRegisterDTO dto);

    ResultMessage deleteUser(UUID id);
}
