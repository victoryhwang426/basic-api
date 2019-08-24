package gg.rest.controller;

import gg.rest.dto.ResultMessage;
import gg.rest.service.UserService;
import lombok.extern.slf4j.Slf4j;
import gg.rest.dto.UserRegisterDTO;
import gg.rest.dto.UserUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserServiceController {
    private UserService userService;

    @Autowired
    public UserServiceController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResultMessage getUsers(){
        return userService.getUsers();
    }

    @GetMapping(value = "/{id}")
    public ResultMessage getUser(@PathVariable UUID id){
        return userService.getUser(id);
    }

    @PostMapping
    public ResultMessage saveUser(@RequestBody UserRegisterDTO dto){
        return userService.saveUser(dto);
    }

    @PutMapping(value = "/{id}")
    public ResultMessage updateUser(@PathVariable UUID id, @RequestBody UserUpdateDTO dto){
        dto.setId(id);
        return userService.updateUser(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResultMessage deleteUser(@PathVariable UUID id){
        return userService.deleteUser(id);
    }
}
