package com.example.datavalidationdemo.processor;

import com.example.datavalidationdemo.model.DataDto;
import com.example.datavalidationdemo.model.SensorDataDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.processor.AbstractProcessor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.processor.To;

import static com.example.datavalidationdemo.topology.TopologyConstants.SINK_DATA_VALIDATION;

@Slf4j
public class DataValidatorProcessor extends AbstractProcessor<String, SensorDataDto> {

    @Override
    public void init(ProcessorContext context) {
        super.init(context);
    }

    @Override
    public void process(String key, SensorDataDto value) {

        if (value.IsCelsius()) {

            var data = DataDto.builder()
                    .validationValue(getValidationValue(value.getValueNominal()))
                    .sensorIdentifier(value.getSensorIdentifier())
                    .build();

            log.info("Data validation: result for sensorIdentifier - [{}] and value_nominal [{}], is [{}] ",
                    data.getSensorIdentifier(),
                    value.getValueNominal(),
                    data.getValidationValue());

            context().forward(key, data, To.child(SINK_DATA_VALIDATION));
        }
    }

    private int getValidationValue(double value) {

        if (value >= 0.0 && value <= 100.0) {
            return 1;
        }

        return 0;
    }
}
