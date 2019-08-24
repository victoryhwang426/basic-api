package gg.rest.controller;

import gg.rest.service.TestService;
import lombok.extern.slf4j.Slf4j;
import gg.rest.dto.ServiceResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/externalservice")
public class TestServiceController {

    @Autowired
    TestService testService;

    @RequestMapping({"", "/"})
    public ServiceResponseDTO getServiceResponse() {
        return testService.getTestServiceResult();
    }

}
