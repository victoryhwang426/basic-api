package gg.rest.dto;

import gg.rest.model.User;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class UserResponseDTO {
    private UUID id;
    private String firstName;
    private String surName;
    private String position;
    private String githubProfileUrl;
    private Date createdDate;
    private Date lastModified;

    public UserResponseDTO(User user){
        id = user.getId();
        firstName = user.getFirstName();
        surName = user.getSurName();
        position = user.getPosition();
        githubProfileUrl = user.getGithubProfileUrl();
        createdDate = user.getCreated();
        lastModified = user.getLastModified();
    }
}
