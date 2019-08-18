package me.remind.rest.sandbox.model;

import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseTimeEntity {
    @Column(name = "created", unique = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date created;

    @Column(name = "lastModified", unique = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastModified;
}
