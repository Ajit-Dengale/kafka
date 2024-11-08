package com.test.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.test.utilities.ProducerUtility;

@Service
public class ConsoleKafkaProducer {
	private static final Logger log= LoggerFactory.getLogger(ConsoleKafkaProducer.class);
	static Properties props = new Properties();
	@Autowired
	@Qualifier("kafkaTemplate")
	private KafkaTemplate<String,String> kafkaTemplate;

    String kafkaTopic;
    String keyValue;
	
	public void send(String filePath) {
      try {
    	  	kafkaTopic = ProducerUtility.smartsConf.getProperty("kafkaTopic");
    	  	String jsonContent = Files.readString(Path.of(filePath));
    	  	String key = ProducerUtility.smartsConf.getProperty("keyValue");
    	  	this.kafkaTemplate.send(this.kafkaTopic, key, jsonContent);
    	  	log.info("JSON successfully send to topic - " + this.kafkaTopic+ "'{}'",jsonContent);  	  	
            }
      catch (IOException e) {
            log.info("Exception occured while sending the JSON");
            log.info(e.getMessage());
        }
	 }
}

