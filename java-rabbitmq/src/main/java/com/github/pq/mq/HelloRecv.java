package com.github.pq.mq;

import com.rabbitmq.client.*;

/**
 * @author xiaoniu 2019/5/21.
 */
public class HelloRecv {

    private final static String QUEUE_NAME = "queue_demo";

    public static void main(String[] argv) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        //创建信道
        Channel channel = connection.createChannel();
        //创建队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        channel.basicQos(30);


        DeliverCallback deliverCallback = (consumerTag, message) -> {
            String messageStr = new String(message.getBody(), "UTF-8");
            System.out.println(" [x] consumerTag="+consumerTag+";Received= '" + messageStr + "'");
            try {
                doSomeWork(messageStr);
            } finally {
                System.out.println(" [x] Done");
                //channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
            }
        };

        CancelCallback cancelCallback = (String consumerTag)-> {
            System.out.println("cancel....consumerTag="+consumerTag);
        };
        channel.basicConsume(QUEUE_NAME, false, deliverCallback,cancelCallback);


    }

    private static void doSomeWork(String messageStr) {
        System.out.println("do something....");
    }
}
