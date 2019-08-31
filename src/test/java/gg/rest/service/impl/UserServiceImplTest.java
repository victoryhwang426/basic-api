package gg.rest.service.impl;

import gg.rest.model.User;
import gg.rest.repository.UserRepository;
import gg.rest.service.UserService;
import gg.rest.dto.ResultMessage;
import gg.rest.dto.UserDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setup(){
        userService = new UserServiceImpl(userRepository);
    }

    private UserDTO userDTO = UserDTO.builder()
            .firstName("Seungri")
            .surName("Hwang")
            .position("Java developer")
            .build();

    @Test
    public void getUser_should_return_user_response_dto(){
        UUID id = UUID.randomUUID();
        when(userRepository.findById(id)).thenReturn(Optional.of(userDTO.toEntity()));

        ResultMessage resultMessage = userService.getUser(id);

        verify(userRepository).findById(id);
        verifyNoMoreInteractions(userRepository);

        assertNotNull(resultMessage.getContent());
        assertEquals(ResultMessage.ResponseMessage.SUCCESS, resultMessage.getResponseMessage().SUCCESS);
    }

    @Test
    public void getUser_should_return_empty_when_no_found(){
        UUID id = UUID.randomUUID();
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        ResultMessage resultMessage = userService.getUser(id);

        verify(userRepository).findById(id);
        verifyNoMoreInteractions(userRepository);

        assertNull(resultMessage.getContent());
        assertEquals(ResultMessage.ResponseMessage.NOT_FOUND, resultMessage.getResponseMessage().NOT_FOUND);
    }

    @Test
    public void getUsers_should_return_list_of_user_response_dto(){
        List<User> users = new ArrayList<>();
        users.add(userDTO.toEntity());

        when(userRepository.findAll()).thenReturn(users);

        ResultMessage resultMessage = userService.getUsers();

        verify(userRepository).findAll();

        assertNotNull(resultMessage.getContent());
        assertEquals(ResultMessage.ResponseMessage.SUCCESS, resultMessage.getResponseMessage().SUCCESS);
    }

    @Test
    public void getUsers_should_return_empty_dto_when_no_result(){
        List<User> users = new ArrayList<>();

        when(userRepository.findAll()).thenReturn(users);

        ResultMessage resultMessage = userService.getUsers();

        verify(userRepository).findAll();

        assertNull(resultMessage.getContent());
        assertEquals(ResultMessage.ResponseMessage.NO_RESULT, resultMessage.getResponseMessage().NO_RESULT);
    }

    @Test
    public void saveUser_should_call_save(){
        when(userRepository.save(any(User.class))).thenReturn(userDTO.toEntity());

        ResultMessage resultMessage = userService.saveUser(userDTO);

        verify(userRepository).save(any(User.class));

        assertNotNull(resultMessage.getContent());
        assertEquals(ResultMessage.ResponseMessage.SUCCESS, resultMessage.getResponseMessage().SUCCESS);
    }

    @Test
    public void updateUser_should_call_save(){
        userDTO.setId(UUID.randomUUID());
        User user = userDTO.toEntity();

        when(userRepository.findById(userDTO.getId())).thenReturn(Optional.of(user));
        user.setFirstName("Victor");

        when(userRepository.save(user)).thenReturn(user);

        ResultMessage resultMessage = userService.updateUser(userDTO);

        verify(userRepository).save(user);

        assertNotNull(resultMessage.getContent());
        assertEquals(ResultMessage.ResponseMessage.SUCCESS, resultMessage.getResponseMessage().SUCCESS);
    }

    @Test
    public void updateUser_should_return_empty_when_no_found(){
        userDTO.setId(UUID.randomUUID());
        when(userRepository.findById(userDTO.getId())).thenReturn(Optional.empty());

        ResultMessage resultMessage = userService.updateUser(userDTO);

        verify(userRepository).findById(userDTO.getId());
        verifyNoMoreInteractions(userRepository);

        assertNull(resultMessage.getContent());
        assertEquals(ResultMessage.ResponseMessage.NOT_FOUND, resultMessage.getResponseMessage().NOT_FOUND);
    }

    @Test
    public void deleteUser_should_call_delete(){
        userDTO.setId(UUID.randomUUID());
        User user = userDTO.toEntity();

        when(userRepository.findById(userDTO.getId())).thenReturn(Optional.of(user));

        ResultMessage resultMessage = userService.deleteUser(userDTO.getId());

        verify(userRepository).findById(userDTO.getId());
        verify(userRepository).delete(user);

        assertEquals(ResultMessage.ResponseMessage.SUCCESS, resultMessage.getResponseMessage().SUCCESS);
    }

    @Test
    public void deleteUser_should_return_empty_when_no_found(){
        userDTO.setId(UUID.randomUUID());
        when(userRepository.findById(userDTO.getId())).thenReturn(Optional.empty());

        ResultMessage resultMessage = userService.deleteUser(userDTO.getId());

        verify(userRepository).findById(userDTO.getId());
        verifyNoMoreInteractions(userRepository);

        assertEquals(ResultMessage.ResponseMessage.NOT_FOUND, resultMessage.getResponseMessage().NOT_FOUND);

    }
}