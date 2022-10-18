package com.jym.car.mq;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.jym.car.model.entity.JymCarBrand;
import com.sun.deploy.net.HttpUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.net.www.http.HttpClient;

/**
 * @author lb
 */
/*@Component
@RocketMQMessageListener(consumerGroup = "sanyouConsumer",topic = "sanyouTopic")
public class SanYouTopicListener implements RocketMQListener<String> {

    @Override
    public void onMessage(String s) {
        JymCarBrand brand = JSON.parseObject(s, JymCarBrand.class);
        System.out.println("处理消息" + brand);
    }
}*/
