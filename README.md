#Batch Validation Assessment

###### A processor validating data.

### Environments
`SERVER_PORT` as default `9680` \
`KAFKA_BOOTSTRAP_SERVERS` - kafka bootstrap url as default `localhost:29091` \
`APPLICATION_ID` - id kafka streams consumer as default `data_validator`\
`REPLICATION_FACTOR` - kafka replication factor as default `1`\
`OFFSET_RESET` - kafka auto-offset-reset as default `latest` ~~earliest~~

####Producer - parser CSV file project you can find https://github.com/sZolotar/data-producer.git

####How to run application:

for running use `docker-compose`

!!!! For start validation Framework use  
> http://localhost:9780/sendData

####How to install Confluent Kafka for local development:
Kafka-Confluent
https://docs.confluent.io/current/quickstart/ce-docker-quickstart.html  

to install with **Docker**:
> git clone https://github.com/confluentinc/examples


>**cd** examples  
**git checkout** 5.3.2-post  
**cd** cp-all-in-one/  
**docker-compose up** -d --build  