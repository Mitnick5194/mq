package com.ajie.mq.demo01;

import javax.jms.Connection;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.ajie.mq.ActiveMQConfig;

/**
 * queue队列消费者
 * 
 * @author niezhenjie
 */
public class QueueConsumerDemo1 {
	static final ActiveMQConfig.ConfigVo config = ActiveMQConfig.getConfig();

	public static void main(String[] args) throws Exception {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(
				config.getUrl() + ":" + config.getPort());
		Connection connection = factory.createConnection();
		connection.start();
		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue("quque01");
		MessageConsumer consumer = session.createConsumer(queue);
		while (true) {
			TextMessage receive = (TextMessage) consumer.receive();
			System.out.println("接收到的消息-"+receive.getText());
		}

	}
}
