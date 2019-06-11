package com.github.pq.mq;

import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author xiaoniu 2019/5/21.
 */
public class HelloSend {

    private final static String QUEUE_NAME = "queue_demo";

    @Test
    public void test1() throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        Map args = new HashMap();
        args.put( "x-max-priority" , 10) ;
        channel.queueDeclare(QUEUE_NAME, true, false, false, args);
        String message = "Hello World qiang4!";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        System.out.println(" hello....");
        connection.addShutdownListener(new ShutdownListener() {
            @Override
            public void shutdownCompleted(ShutdownSignalException cause) {
                System.out.println("hello....shutdown begin");
                System.out.println("cause:"+cause.getMessage());
                cause.isHardError();
            }
        });
        System.out.println("hello....begin to close");
        channel.close();
        connection.close();

    }

    @Test
    public void test2() throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        String message = "Hello World qiang4!";
        //channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        channel.basicPublish("",QUEUE_NAME,true,MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int replyCode, String replyText, String exchange,
                                     String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg  = new String(body);
                System.out.println("Basic.Return返回结果:"+msg);
            }
        });
        System.out.println(" [x] Sent '" + message + "'");
        System.out.println(" hello....");
        connection.addShutdownListener(new ShutdownListener() {
            @Override
            public void shutdownCompleted(ShutdownSignalException cause) {
                System.out.println("hello....shutdown begin");
                System.out.println("cause:"+cause.getMessage());
                cause.isHardError();
            }
        });
        System.out.println("hello....begin to close");
        channel.close();
        connection.close();


    }


    @Test
    public void test3() throws IOException, TimeoutException{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        Map args = new HashMap();
        args.put( "x-max-priority" , 10) ;
        channel.queueDeclare(QUEUE_NAME, true, false, false, args);
        String message = "Hello World qiang4!";
        try {
            //将信道置为publisher confirm 模式
            channel.confirmSelect();
            //之后正常发送消息
            channel.basicPublish("","routingKey", null, message.getBytes());

            if(!channel.waitForConfirms()){
                System.out.println("send message failed");
                // do something else ...
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        channel.close();
        connection.close();
    }



}
