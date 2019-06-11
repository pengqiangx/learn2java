package com.github.pq.mq;

import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaoniu 2019/5/21.
 */
public class Demo2 {

    private static final String EXCHANGE_NAME = "exchange_demo";
    private static final String ROUTING_KEY = "routingkey_demo";
    private static final String QUEUE_NAME = "queue_demo";
    private static final String IP_ADDRESS = "127.0.0.1";
    private static final int PORT = 5672;

    @Test
    public void send() throws Exception {
        ConnectionFactory factory = new ConnectionFactory() ;
        factory.setVirtualHost("/");
        factory.setHost(IP_ADDRESS) ;
        factory.setPort(PORT) ;
        factory. setUsername("guest");
        factory. setPassword("guest");
        // 创建连接
        Connection connection = factory.newConnection();
        // 创建信道
        Channel channel = connection.createChannel();
        // 创建交换器 ( type="direct" 、持久化的、非自动删除的交换器)
        channel.exchangeDeclare(EXCHANGE_NAME, "direct" , true , false , null) ;
        //创建队列（一个持久化、非排他的、非自动删除的队列）
        channel. queueDeclare(QUEUE_NAME , true , false , false , null) ;
        //将交换器与队列通过路由键绑定
        channel.queueBind(QUEUE_NAME , EXCHANGE_NAME , ROUTING_KEY);
        //发送一条持久化的消息: hello world !
        String message = "Hello World n!";
        channel.basicPublish(EXCHANGE_NAME , ROUTING_KEY ,
                MessageProperties.PERSISTENT_TEXT_PLAIN,
                message.getBytes()) ;
        //关闭资源
        channel.close() ;
        connection.close ();
    }
    @Test
    public void consumer() throws  Exception {
        Address[] addresses = new Address[]{
           new Address(IP_ADDRESS,PORT)
        };
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        //这里的连接方式与生产者的 demo 略有不同 注意辨别区别
        // 创建连接
        Connection connection = factory.newConnection(addresses);
        // 创建信道
        final Channel channel = connection.createChannel() ;
        // 设置客户端最多接收未被 ack 的消息的个数
        channel.basicQos(64);
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body)
                    throws IOException {
                System.out.println(" recv message: " + new String(body));
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }

        };
        channel.basicConsume(QUEUE_NAME , consumer);
        //等待回调函数执行完毕之后 关闭资源
        TimeUnit.SECONDS.sleep(5);
        channel.close();
        connection.close();

        }


    @Test
    public void test2() throws Exception {
        ConnectionFactory factory = new ConnectionFactory() ;
        factory.setVirtualHost("/");
        factory.setHost(IP_ADDRESS) ;
        factory.setPort(PORT) ;
        factory. setUsername("guest");
        factory. setPassword("guest");
        // 创建连接
        Connection connection = factory.newConnection();
        // 创建信道
        Channel channel = connection.createChannel();
        // 创建交换器
        channel.exchangeDeclare("exchange_destination", "fanout" , true , false , null) ;

        channel.exchangeDeclare("exchange_source", "direct" , true , false , null) ;

        //交换器和交换器的绑定
        channel.exchangeBind("exchange_destination","exchange_source","routingkey_demo");

        //创建队列（一个持久化、非排他的、非自动删除的队列）
        channel. queueDeclare("queue_demo" , true , false , false , null) ;
        //将交换器与队列通过路由键绑定
        channel.queueBind("queue_demo" , "exchange_destination" , "routingkey_demo");
        //发送一条持久化的消息: hello world !
        String message = "Hello World !";
        channel.basicPublish("exchange_source" , "routingkey_demo" ,
                MessageProperties.PERSISTENT_TEXT_PLAIN,
                message.getBytes()) ;

        Map<String , Object> headers = new HashMap<String, Object>() ;
        headers.put( "localtion" , "here " );
        headers.put( "time" , " today" );

        new AMQP.BasicProperties().builder()
                .contentType( "text/p1ain" ) //
                .deliveryMode(2) //投递模式为2 ，即消息会被持久化(即存入磁盘)在服务器中
                .priority (1)//消息的优先级
                .headers(headers) //带有 headers 的消息
                .expiration("60000") //发送一条带有过期时间的消息
                .build();


        //关闭资源
        channel.close() ;
        connection.close ();
    }
}

