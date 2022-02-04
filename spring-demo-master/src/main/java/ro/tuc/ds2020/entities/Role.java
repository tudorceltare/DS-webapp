package ro.tuc.ds2020.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Role implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "userId", nullable = false)
    private UUID userId;

    public Role() {
    }

    public Role(String name, UUID userId) {
        this.name = name;
        this.userId = userId;
    }

    public Role(UUID id, String name, UUID userId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
    }
}
