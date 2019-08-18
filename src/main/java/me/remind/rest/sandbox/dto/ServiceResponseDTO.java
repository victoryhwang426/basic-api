package me.remind.rest.sandbox.dto;

import lombok.Data;

@Data
public class ServiceResponseDTO {

    private int userId;
    private int id;
    private String title;
    private String body;
}
