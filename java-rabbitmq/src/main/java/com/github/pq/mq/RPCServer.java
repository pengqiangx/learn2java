package com.github.pq.mq;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author xiaoniu 2019/6/4.
 */
public class RPCServer {
    private static final String RPC_QUEUE_NAME = "rpc_queue";

    public static void main(String args) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(RPC_QUEUE_NAME,false,false,false,null);
        channel.basicQos(1);

        System.out.println("awaiting RPC requests....");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                AMQP. BasicProperties replyProps = new AMQP.BasicProperties
                        .Builder()
                        .correlationId(properties.getCorrelationId())
                        .build () ;
                String response = "";
                try{
                    String message = new String(body,"UTF-8");
                    int n = Integer.parseInt(message);
                    System.out.println(".. fib ("+message+")");
                    response += fib(n);
                }catch (Exception e){
                    System.out.println("error:"+e.toString());
                }finally {
                    channel.basicPublish("",properties.getReplyTo(),
                    replyProps, response.getBytes("UTF-8"));
                    channel . basicAck(envelope.getDeliveryTag() , false);
                }
            }
        };
        channel.basicConsume(RPC_QUEUE_NAME,false,consumer);


    }
    private static int fib(int n){
        if(n == 0 || n ==1 ){
            return n;
        }
        return fib(n-1)+fib(n-2);
    }
}
