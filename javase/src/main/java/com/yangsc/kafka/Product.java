package com.yangsc.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * <p>Description: Product</p>
 *
 * <p>Copyright: © 2018-至今 All rights reserved.</p>
 *
 * @author yangsanchao
 * *
 * @date 2020/6/28 14:44
 **/
public class Product {
    /**
     * 主题名称-之前已经创建
     */
    private static final String brokerList = "localhost:9092";
    private static final String topic = "yangsanchao";

    public static void main(String[] args) {
        Properties properties = new Properties();
        /**
         * 设置key序列化器
         */
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        /**
         * 设置重试次数
         */
        properties.put(ProducerConfig.RETRIES_CONFIG, 10);
        /**
         * 设置值序列化器
         */
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        /**
         * 设置集群地址
         */
        properties.put("bootstrap.servers", brokerList);
        // KafkaProducer 线程安全
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        ProducerRecord<String, String> record = new ProducerRecord<>(topic,
                "Kafka-demo-001", "hello, Kafka!");
        try {
            producer.send(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.close();


    }

}
