package me.remind.rest.sandbox.controller;

import me.remind.rest.sandbox.dto.ServiceResponseDTO;
import me.remind.rest.sandbox.service.TestService;
import org.hamcrest.Matchers;
import org.junit.Before;
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

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(TestServiceController.class)
public class TestServiceControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private TestService testService;

    private ServiceResponseDTO serviceResponseDTO = new ServiceResponseDTO();

    @Before
    public void setup(){
        serviceResponseDTO.setId(1);
        serviceResponseDTO.setUserId(9000);
        serviceResponseDTO.setBody("Body");
        serviceResponseDTO.setTitle("Title");
    }

    @Test
    public void getServiceResponse() throws Exception {
        when(testService.getTestServiceResult()).thenReturn(serviceResponseDTO);

        mvc.perform(MockMvcRequestBuilders.get("/externalservice/").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath(
                        "id",
                        Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath(
                        "userId",
                        Matchers.is(9000)));
    }

}