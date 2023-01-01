package javdev.procesarorden;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;


@SpringBootApplication
public class ProcesarordenApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcesarordenApplication.class, args);
	}

}
