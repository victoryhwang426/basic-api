package me.remind.rest.sandbox.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.remind.rest.sandbox.model.User;

@Data
public class UserRegisterDTO {
    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("sur_name")
    private String surName;

    private String position;

    @JsonProperty("github_profile_url")
    private String githubProfileUrl;

    public User toEntity(){
        return User.builder()
                .firstName(firstName)
                .surName(surName)
                .position(position)
                .githubProfileUrl(githubProfileUrl)
                .build();
    }

    @Builder
    public UserRegisterDTO(String firstName,
                           String surName,
                           String position,
                           String githubProfileUrl) {
        this.firstName = firstName;
        this.surName = surName;
        this.position = position;
        this.githubProfileUrl = githubProfileUrl;
    }
}
