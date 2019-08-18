package me.remind.rest.sandbox.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.remind.rest.sandbox.dto.ResultMessage;
import me.remind.rest.sandbox.dto.UserRegisterDTO;
import me.remind.rest.sandbox.dto.UserResponseDTO;
import me.remind.rest.sandbox.dto.UserUpdateDTO;
import me.remind.rest.sandbox.model.User;
import me.remind.rest.sandbox.repository.UserRepository;
import me.remind.rest.sandbox.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public ResultMessage getUser(UUID id) {
        Optional<User> optUser = userRepository.findById(id);

        return optUser
                .map(user -> new ResultMessage(ResultMessage.ResponseMessage.SUCCESS, user))
                .orElse(new ResultMessage(ResultMessage.ResponseMessage.NOT_FOUND));
    }

    @Override
    public ResultMessage getUsers() {
        List<User> users =userRepository.findAll();
        if(users.size() == 0){
            return new ResultMessage(ResultMessage.ResponseMessage.NO_RESULT);
        }

        return new ResultMessage(ResultMessage.ResponseMessage.SUCCESS,
                users.stream()
                    .map(UserResponseDTO::new)
                    .collect(Collectors.toList()));
    }

    @Override
    @Transactional
    public ResultMessage saveUser(UserRegisterDTO dto) {
        User user = userRepository.save(dto.toEntity());

        return new ResultMessage(ResultMessage.ResponseMessage.SUCCESS, user);
    }

    @Override
    @Transactional
    public ResultMessage updateUser(UserUpdateDTO dto) {
        Optional<User> optUser = userRepository.findById(dto.getId());

        if(optUser.isPresent()){
            User user = optUser.get();
            user.setFirstName(dto.getFirstName());
            user.setGithubProfileUrl(dto.getGithubProfileUrl());
            user.setPosition(dto.getPosition());
            user.setSurName(dto.getSurName());
            user.setGithubProfileUrl(dto.getGithubProfileUrl());

            User updatedUser = userRepository.save(user);

            return new ResultMessage(ResultMessage.ResponseMessage.SUCCESS, new UserResponseDTO(updatedUser));
        }

        return new ResultMessage(ResultMessage.ResponseMessage.NOT_FOUND);
    }

    @Override
    @Transactional
    public ResultMessage deleteUser(UUID id) {
        Optional<User> optUser = userRepository.findById(id);
        if(optUser.isPresent()){
            userRepository.delete(optUser.get());
            return new ResultMessage(ResultMessage.ResponseMessage.SUCCESS);
        }

        return new ResultMessage(ResultMessage.ResponseMessage.NOT_FOUND);
    }
}
