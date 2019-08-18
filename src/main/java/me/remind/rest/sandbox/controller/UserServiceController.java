package me.remind.rest.sandbox.controller;

import lombok.extern.slf4j.Slf4j;
import me.remind.rest.sandbox.dto.ResultMessage;
import me.remind.rest.sandbox.dto.UserRegisterDTO;
import me.remind.rest.sandbox.dto.UserUpdateDTO;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserServiceController {
    @GetMapping
    public ResultMessage getUsers(){
        return null;
    }

    @GetMapping(value = "/{id}")
    public ResultMessage getUser(@PathVariable UUID id){
        return null;
    }

    @PostMapping
    public ResultMessage saveUser(@RequestBody UserRegisterDTO dto){
        return null;
    }

    @PutMapping(value = "/{id}")
    public ResultMessage updateUser(@PathVariable UUID id, @RequestBody UserUpdateDTO dto){
        dto.setId(id);
        return null;
    }

    @DeleteMapping(value = "/{id}")
    public ResultMessage deleteUser(@PathVariable UUID id){
        return null;
    }
}
