package ro.tuc.ds2020.rabbitMQ;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Receiver {
    private CountDownLatch latch;

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        latch.countDown();
    }

}
