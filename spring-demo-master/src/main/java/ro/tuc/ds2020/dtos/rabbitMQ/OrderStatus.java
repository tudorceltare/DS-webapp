package ro.tuc.ds2020.dtos.rabbitMQ;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatus {
    private Order order;
    private String message;
    private String status;
}
