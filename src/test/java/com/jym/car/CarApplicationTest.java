/*
package com.jym.car;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jym.car.mapper.JymCarModelMapper;
import com.jym.car.model.entity.JymCarModel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import javax.websocket.SendResult;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;

*/
/**
 * @author lb
 *//*

@SpringBootTest
public class CarApplicationTest {

    @Test
    public void testRock1() throws MQClientException, UnsupportedEncodingException, MQBrokerException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("testMsg");

        producer.setNamesrvAddr("192.168.211.153:9876");

        producer.setSendMsgTimeout(60000);

        producer.start();

        Message message = new Message("sanyouTopic", "TagA" , "java日记".getBytes(RemotingHelper.DEFAULT_CHARSET));

        SendResult send = producer.send(message);
        System.out.println(send);

        producer.shutdown();
    }


    @Test
    public void testRock2() throws Exception{
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("sanyouConsumer");

        consumer.setNamesrvAddr("192.168.211.153:9876");

        consumer.subscribe("sanyouTopic","*");

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    System.out.println(new String(msg.getBody()));
                }

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();

       // System.out.println("Consumer Started");
    }

    @Autowired
    JymCarModelMapper jymCarModelMapper;

    @Test
    public void testIndex1() throws Exception{
        long one = System.currentTimeMillis();

        List<JymCarModel> list = jymCarModelMapper.findByModelName("s");

        long two = System.currentTimeMillis();

        System.out.println(two - one);
    }


}
*/
