package ro.tuc.ds2020.dtos.rabbitMQ;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String timestamp;
    private String sensor_id;
    private float measurement_value;
}
