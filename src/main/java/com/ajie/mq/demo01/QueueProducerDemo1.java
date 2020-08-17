package com.ajie.mq.demo01;

import javax.jms.Connection;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.ajie.mq.ActiveMQConfig;

/**
 * 队列消息
 * 
 * @author niezhenjie
 */
public class QueueProducerDemo1 {
	
	static final ActiveMQConfig.ConfigVo config = ActiveMQConfig.getConfig();

	public static void main(String[] args) throws Exception {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(config.getUrl()+":"+config.getPort());
		Connection connection =  factory.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue("quque01");
		MessageProducer messageProducer = session.createProducer(queue);
		for(int i=1;i<=3;i++){
			TextMessage text = session.createTextMessage("ququq-msg-"+i);
			messageProducer.send(text);
		}
		session.close();
		connection.close();
	}
	
}
