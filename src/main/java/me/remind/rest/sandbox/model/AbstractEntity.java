package me.remind.rest.sandbox.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

import static org.springframework.util.Assert.notNull;

@MappedSuperclass
public abstract class AbstractEntity extends BaseTimeEntity {

    private static final long serialVersionUID = 382472348372916662L;

    @Id
    @Type(type = "uuid-char")
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

    protected AbstractEntity() {
        id = UUID.randomUUID();
    }

    @Override
    public int hashCode() {
        if (getId() != null) {
            return getId().hashCode();
        }
        return super.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (other instanceof AbstractEntity) {
            // Check whether id of entities match
            return id.equals(((AbstractEntity) other).getId());
        } else {
            return false;
        }
    }

    /**
     * @return the id
     */
    public UUID getId() {
        return id;
    }

    public void setId(@NotNull UUID id) {
        notNull(id, "ID of entity may not be null");
        this.id = id;
    }

    public static UUID generateUUID() {
        return UUID.randomUUID();
    }
}
