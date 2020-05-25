package com.example.datavalidationdemo.topology;

import com.example.datavalidationdemo.config.KafkaStreamConfiguration;
import com.example.datavalidationdemo.model.DataDto;
import com.example.datavalidationdemo.model.SensorDataDto;
import com.example.datavalidationdemo.processor.DataValidatorProcessor;
import com.example.datavalidationdemo.util.JsonPOJOSerde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.Topology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;

import static com.example.datavalidationdemo.topology.TopologyConstants.*;

@Component
public class DataValidationTopology {

    private final KafkaStreamConfiguration kafkaStreamConfiguration;

    @Autowired
    public DataValidationTopology(KafkaStreamConfiguration kafkaStreamConfiguration) {
        this.kafkaStreamConfiguration = kafkaStreamConfiguration;
    }

    @Bean
    public void startStreams() {
        Properties properties = kafkaStreamConfiguration.getStreamConfig().asProperties();

        KafkaStreams validationStreams = new KafkaStreams(createDataValidationTopology(), properties);

        validationStreams.start();

    }

    /**
     * Create topology for processing sensor data
     * Steps:
     * 1. Read data from input_data topic
     * 2. Processing and validating in {@link DataValidatorProcessor}
     * 3. Send final result to dqf-temperature topic
     * @return {@link Topology}
     */
    private Topology createDataValidationTopology() {

        JsonPOJOSerde<DataDto> dataJsonSerde = new JsonPOJOSerde<>(DataDto.class);
        JsonPOJOSerde<SensorDataDto> sensorDataJsonSerde = new JsonPOJOSerde<>(SensorDataDto.class);

        Topology topology = new Topology();

        topology
                .addSource(DATA_SOURCE,
                        Serdes.String().deserializer(),
                        sensorDataJsonSerde.deserializer(),
                        TOPIC_INPUT_DATA_SOURCE)

                .addProcessor(PROCESSOR_DATA_VALIDATOR,
                        DataValidatorProcessor::new,
                        DATA_SOURCE)

                .addSink(SINK_DATA_VALIDATION,
                        TOPIC_OUTPUT_DATA_SOURCE,
                        Serdes.String().serializer(),
                        dataJsonSerde.serializer(),
                        PROCESSOR_DATA_VALIDATOR);


        System.out.println("=====================");
        System.out.println(topology.describe());            // print schema topology
        System.out.println("=====================");

        return topology;
    }
}
