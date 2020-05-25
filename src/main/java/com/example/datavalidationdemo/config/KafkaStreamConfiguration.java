package com.example.datavalidationdemo.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.errors.LogAndContinueExceptionHandler;
import org.apache.kafka.streams.processor.WallclockTimestampExtractor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaStreamConfiguration {

    @Value("${spring.kafka.streams.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.streams.application-id}")
    private String applicationId;

    @Value("${spring.kafka.streams.replication-factor}")
    private Integer replicationFactor;

    @Value("${spring.kafka.properties.auto-offset-reset}")
    private String offsetResetConfig;

    @Value("${spring.kafka.properties.num.streams.thread}")
    private int numThreads;

    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public KafkaStreamsConfiguration getStreamConfig() {
        Map<String, Object> props = new HashMap();

        props.put(ConsumerConfig.GROUP_ID_CONFIG, applicationId);
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, applicationId);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, offsetResetConfig);
        props.put(StreamsConfig.REPLICATION_FACTOR_CONFIG, replicationFactor);
        props.put(StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, WallclockTimestampExtractor.class);
        props.put(StreamsConfig.DEFAULT_DESERIALIZATION_EXCEPTION_HANDLER_CLASS_CONFIG, LogAndContinueExceptionHandler.class);
        props.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, numThreads);

        return new KafkaStreamsConfiguration(props);

    }
}
