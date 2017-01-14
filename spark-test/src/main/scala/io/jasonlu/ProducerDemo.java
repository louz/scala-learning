package io.jasonlu;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * Created by Louz on 2016/5/27.
 */
public class ProducerDemo {
    private static KafkaProducer<String, String> procuder;

    public static void main(String[] args) {
        Producer<String, String> producer = getKafkaProducer();
        String topic = "my-topic";
        for(int i = 0; i < 100; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, "NO." + Integer.toString(i));
            producer.send(record);
        }

        producer.close();
    }

    public static Producer<String, String> getKafkaProducer() {
        if (procuder == null) {
            Properties props = new Properties();
            props.put("bootstrap.servers", "localhost:9092");
            props.put("acks", "all");
            props.put("retries", 0);
            props.put("batch.size", 16384);
            props.put("linger.ms", 1);
            props.put("buffer.memory", 33554432);
            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

            procuder = new KafkaProducer<>(props);
        }

        return procuder;
    }
}
