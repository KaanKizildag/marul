package com.marul.config;

import com.marul.dto.MailGondermeDto;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrampServers;

    @Bean
    public KafkaTemplate<String, MailGondermeDto> kafkaTemplate(ProducerFactory<String, MailGondermeDto> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public ProducerFactory<String, MailGondermeDto> producerFactory() {
        Map<String, Object> config = producerConfig();
        return new DefaultKafkaProducerFactory<>(config);
    }

    private Map<String, Object> producerConfig() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrampServers);
        config.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return config;
    }

}
