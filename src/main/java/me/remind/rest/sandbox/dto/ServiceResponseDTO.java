package me.remind.rest.sandbox.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
public class ServiceResponseDTO {
    private int userId;
    private int id;
    private String title;
    private String body;

    @Builder
    public ServiceResponseDTO(int userId,
                              int id,
                           String title,
                           String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }
}
