package com.test;

import java.io.IOException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.test.services.ConsoleKafkaProducer;

@Configuration
@EnableScheduling
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.test"})
@SpringBootApplication(scanBasePackages={"com.test"})
public class ApplicationBootStrap implements CommandLineRunner{

	private final ConsoleKafkaProducer consoleKafkaProducer;
	
	public ApplicationBootStrap(ConsoleKafkaProducer consoleKafkaProducer) {
		this.consoleKafkaProducer= consoleKafkaProducer;
	}
	
	@Override
	public void run(String... args) throws IOException{
		String filePath = "/home/vboxuser/Documents/test_JSON.json";
		consoleKafkaProducer.send(filePath);
	}
}
