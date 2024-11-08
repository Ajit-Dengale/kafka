package com.test.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import com.test.utilities.ProducerUtility;

@Configuration
public class KafkaProducerConfig {
	@Value("${kafka.bootstrap-servers}")
	private String bootStrapServer;
	@Value("${kafka.acks}")
	private String acks;
	@Value("${kafka.linger.ms}")
	private String lingerMS;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer()
	{
		PropertySourcesPlaceholderConfigurer properties = new PropertySourcesPlaceholderConfigurer();
		properties.setLocation(new ClassPathResource("application.properties"));
		properties.setIgnoreResourceNotFound(false);
		return properties;
	}
	
	@Bean
	public ProducerFactory<String, String> producerFactory(){
		Map<String, Object> configProps = new HashMap();
		configProps.put("bootStrapServer", ProducerUtility.smartsConf.getProperty("kafka.bootstrap-servers"));
		configProps.put("key.serializer", StringSerializer.class);
		configProps.put("value.serializer", StringSerializer.class);
		configProps.put("acks", this.acks);
		configProps.put("linger.ms", this.lingerMS);
		
		return new DefaultKafkaProducerFactory(configProps);
	}
	
	@Bean(name={"kafkaTemplate"})
	public KafkaTemplate<String, String> kafkaTemplate()
	{
		return new KafkaTemplate(producerFactory());
	}
}
