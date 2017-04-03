package local.rabbitmq;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.sql.Blob;
import java.util.Date;
import java.util.concurrent.TimeoutException;

import javax.annotation.PostConstruct;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import local.rabbitmq.entity.AuditLog;
import local.rabbitmq.entity.ReceivedMessage;
import local.rabbitmq.model.AuditLogRequest;
import local.rabbitmq.service.AuditLogService;
import local.rabbitmq.service.ReceivedMessageService;

@Component
public class Consumer {
	private static final Logger log = LoggerFactory.getLogger(Consumer.class);

	@Value("${consumer.start}")
	private boolean startConsumer;

	@Autowired
	private ConnectionFactory factory;
	
	@Autowired
	private AppConfiguration appConfig;
	
	@Autowired
	private ReceivedMessageService service;
	
	@Autowired
	private AuditLogService auditLogService;
	
	@Autowired
	private SessionFactory sessionFactory;

	@PostConstruct
	public void init() {
		if (!startConsumer)
			return;
		Connection connection;
		try {
			connection = factory.newConnection();
			Channel channel = connection.createChannel();
			channel.queueDeclare(appConfig.getQueueName(), false, false, false, null);
			System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
			DefaultConsumer consumer = new DefaultConsumer(channel) {

				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
						byte[] body) throws IOException {
					String message = new String(body, "UTF-8");
					System.out.println(" [x] Received '" + message + "'");
					ReceivedMessage reMsg = new ReceivedMessage();
					Blob blob = Hibernate.getLobCreator(sessionFactory.openSession()).createBlob(message.getBytes());
					reMsg.setMessage(blob);
					reMsg.setCreateDate(new Date());
					service.save(reMsg);
					try {
						ObjectMapper mapper = new ObjectMapper();
						AuditLogRequest auditLog = mapper.readValue(message, AuditLogRequest.class);
						auditLogService.save(auditLog.getAuditLog());
					} catch (Exception e) {
//						e.printStackTrace();
					}
				}
			};
			channel.basicConsume(appConfig.getQueueName(), true, consumer);
		} catch (IOException e) {
			log.error("Error when init consumer.", e);
		} catch (TimeoutException e) {
			log.error("Error when init consumer.", e);
		}
	}

}
