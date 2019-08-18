package me.remind.rest.sandbox.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import me.remind.rest.sandbox.model.User;

import java.util.UUID;

@Data
public class UserUpdateDTO {
    @JsonProperty("id")
    private UUID id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("sur_name")
    private String surName;

    private String position;

    @JsonProperty("github_profile_url")
    private String githubProfileUrl;

    public User toEntity(){
        return User.builder()
                .id(id)
                .firstName(firstName)
                .surName(surName)
                .position(position)
                .githubProfileUrl(githubProfileUrl)
                .build();
    }

    @Builder
    public UserUpdateDTO(UUID id,
                         String firstName,
                         String surName,
                         String position,
                         String githubProfileUrl) {
        this.id = id;
        this.firstName = firstName;
        this.surName = surName;
        this.position = position;
        this.githubProfileUrl = githubProfileUrl;
    }
}
