package com.github.pq.mq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

/**
 * @author xiaoniu 2019/6/4.
 */
public class RpcClient {
    private Connection connection;
    private Channel channel;
    private String requestQueueName = " rpc queue ";
    private String replyQueueName;
    private DefaultConsumer consumer;

    public RpcClient() throws IOException, TimeoutException {
        //省略了创建 Connection Channel 过程，具 参考 1. 4.4
        replyQueueName = channel.queueDeclare().getQueue();
        consumer = new DefaultConsumer(channel);
        channel.basicConsume(replyQueueName, true, consumer);
    }
    public String call (String message ) throws Exception {
        String response = null ;
        String corrld = UUID. randomUUID () . toString ();
        BasicProperties props = new AMQP.BasicProperties.Builder()
                .correlationId(corrld)
                .replyTo(replyQueueName)
                .build();
        //channel.basicPublish ("" , requestQueueName , props , message.getBytes ());
       return null;
    }
}