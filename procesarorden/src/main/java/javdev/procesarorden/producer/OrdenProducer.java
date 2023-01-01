package javdev.procesarorden.producer;

import javdev.procesarorden.entities.Orden;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrdenProducer {
    @Value("${topic.name.orden}")
    private String topicName;
    private final KafkaTemplate<String, Orden> ordenkafkaTemplate;

    public void send(Orden orden){
        ordenkafkaTemplate.send(topicName,orden);
    }
}
