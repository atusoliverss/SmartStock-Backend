package br.com.ifba.smartstock;

import br.com.ifba.smartstock.client.SpringClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@Log4j2
public class SmartStockBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartStockBackendApplication.class, args);

		//SpringClient.main(args);
	}

}
