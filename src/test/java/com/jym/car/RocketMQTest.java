package com.jym.car;

import com.jym.car.model.entity.User;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
public class RocketMQTest {

    /*@Autowired
    private RocketMQTemplate template;*/

    @Test
    public void send() throws InterruptedException {
        //template.convertAndSend("sanyouTopic", );
        Thread.sleep(60000);
    }

}
