package ro.tuc.ds2020.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
public class SmartSensor implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @Column(name = "description")
    private String description;

    @Column(name = "maxValue", nullable = false)
    private float maxValue;

    @ManyToOne
    private SmartDevice device;

    public SmartSensor(String description, float maxValue) {
        this.description = description;
        this.maxValue = maxValue;
    }

    public SmartSensor(UUID id,String description, float maxValue) {
        this.id = id;
        this.description = description;
        this.maxValue = maxValue;
    }

    public SmartSensor() {

    }
}
