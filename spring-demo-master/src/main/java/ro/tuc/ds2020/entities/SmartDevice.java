package ro.tuc.ds2020.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SmartDevice implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;
    @Column(name = "description", nullable = true)
    private String description;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "maxEnergyConsumption", nullable = false)
    private float maxEnergyConsumption;
    @Column(name = "avgConsumption", nullable = false)
    private float avgConsumption;

    @ManyToOne
    private Person person;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<SmartSensor> smartSensors;

    // used for CREATE
    public SmartDevice(String description,
                       String address,
                       float maxEnergyConsumption,
                       float avgConsumption,
                       Set<SmartSensor> smartSensors
                       ) {
        this.description = description;
        this.address = address;
        this.maxEnergyConsumption = maxEnergyConsumption;
        this.avgConsumption = avgConsumption;
        this.smartSensors = smartSensors;
    }

    // used for UPDATE
    // AllArgsConstructor
    public SmartDevice(UUID id,
                       String description,
                       String address,
                       float maxEnergyConsumption,
                       float avgConsumption
    ) {
        this.description = description;
        this.address = address;
        this.maxEnergyConsumption = maxEnergyConsumption;
        this.avgConsumption = avgConsumption;
    }

    // custom SET SmartSensors
    public void setSmartSensors(Set<SmartSensor> sensors) {
        this.smartSensors = sensors;
        for (SmartSensor s : sensors) {
            s.setDevice(this);
        }
    }
}
