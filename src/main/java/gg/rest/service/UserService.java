package gg.rest.service;

import gg.rest.dto.ResultMessage;
import gg.rest.dto.UserDTO;

import java.util.UUID;

public interface UserService {
    ResultMessage getUser(UUID id);

    ResultMessage getUsers();

    ResultMessage saveUser(UserDTO dto);

    ResultMessage updateUser(UserDTO dto);

    ResultMessage deleteUser(UUID id);
}
