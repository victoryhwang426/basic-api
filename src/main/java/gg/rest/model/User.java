package gg.rest.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Setter
public class User extends AbstractEntity {
    @Column
    private String firstName;
    @Column
    private String surName;
    @Column
    private String position;
    @Column(name="github_profile_url")
    private String githubProfileUrl;

    @Builder
    public User(UUID id,
                String firstName,
                String surName,
                String position,
                String githubProfileUrl){
        this.setId(id == null ? generateUUID() : id);
        this.firstName = firstName;
        this.surName = surName;
        this.position = position;
        this.githubProfileUrl = githubProfileUrl;
    }
}
