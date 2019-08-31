package gg.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import gg.rest.service.UserService;
import gg.rest.dto.ResultMessage;
import gg.rest.dto.UserDTO;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(UserServiceController.class)
public class UserServiceControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    private ResultMessage resultMessage
            = new ResultMessage(ResultMessage.ResponseMessage.SUCCESS);

    private UserDTO userDTO = UserDTO.builder()
            .firstName("Seungri")
            .surName("Hwang")
            .position("Java developer")
            .build();

    @Test
    public void getUsers() throws Exception {
        when(userService.getUsers()).thenReturn(resultMessage);

        mvc.perform(MockMvcRequestBuilders.get("/users").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath(
                        "responseMessage",
                        Matchers.is(ResultMessage.ResponseMessage.SUCCESS.name())));
    }

    @Test
    public void getUser() throws Exception {
        UUID id = UUID.randomUUID();
        when(userService.getUser(id)).thenReturn(resultMessage);

        mvc.perform(MockMvcRequestBuilders.get("/users/"+id).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath(
                        "responseMessage",
                        Matchers.is(ResultMessage.ResponseMessage.SUCCESS.name())));
    }

    @Test
    public void saveUser() throws Exception {
        when(userService.saveUser(any(UserDTO.class))).thenReturn(resultMessage);

        mvc.perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath(
                        "responseMessage",
                        Matchers.is(ResultMessage.ResponseMessage.SUCCESS.name())));
    }

    @Test
    public void updateUser() throws Exception {
        UUID id = UUID.randomUUID();
        userDTO.setId(id);
        when(userService.updateUser(any(UserDTO.class))).thenReturn(resultMessage);

        mvc.perform(MockMvcRequestBuilders.put("/users/"+id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath(
                        "responseMessage",
                        Matchers.is(ResultMessage.ResponseMessage.SUCCESS.name())));
    }

    @Test
    public void deleteUser() throws Exception {
        UUID id = UUID.randomUUID();
        when(userService.deleteUser(id)).thenReturn(resultMessage);

        mvc.perform(MockMvcRequestBuilders.delete("/users/"+id).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath(
                        "responseMessage",
                        Matchers.is(ResultMessage.ResponseMessage.SUCCESS.name())));
    }
}