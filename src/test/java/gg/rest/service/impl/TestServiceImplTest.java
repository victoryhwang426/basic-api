package gg.rest.service.impl;

import gg.rest.dto.ServiceResponseDTO;
import gg.rest.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestServiceImplTest {
    @InjectMocks
    private TestService testService = new TestServiceImpl("https://jsonplaceholder.typicode.com/posts/1");

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void getTestServiceResult_should_return_serviceResponseDto(){
        ServiceResponseDTO dto = new ServiceResponseDTO();
        dto.setId(1);
        dto.setBody("Body");
        dto.setTitle("Title");
        dto.setUserId(9999);
        ResponseEntity<ServiceResponseDTO> response = mock(ResponseEntity.class);

        when(restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts/1", ServiceResponseDTO.class))
                .thenReturn(response);
        when(response.getBody()).thenReturn(dto);

        ServiceResponseDTO result = testService.getTestServiceResult();

        assertEquals(dto.getId(), result.getId());
        assertEquals(dto.getUserId(), result.getUserId());
        assertEquals(dto.getBody(), result.getBody());
        assertEquals(dto.getTitle(), result.getTitle());
    }
}