package local.rabbitmq.service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import local.rabbitmq.App;
import local.rabbitmq.AppConfiguration;
import local.rabbitmq.exception.SenderServiceException;

@Component
public class SenderService {
	private static final Logger log = LoggerFactory.getLogger(SenderService.class);

	@Autowired
	private ConnectionFactory factory;
	
	@Autowired
	private AppConfiguration appConfig;

	public void publish(String message) throws SenderServiceException{
		Channel channel = null;
		Connection connection = null;
		try {
			connection = factory.newConnection();
			channel = connection.createChannel();
			channel.queueDeclare(appConfig.getQueueName(), false, false, false, null);
			channel.basicPublish("", appConfig.getQueueName(), null, message.getBytes());
			log.info("Send message to queue: " + message);
			channel.close();
			connection.close();
		} catch (IOException e) {
			log.error("Error when init Sender.", e);
			throw new SenderServiceException(e);
		} catch (TimeoutException e) {
			log.error("Error when init Sender.", e);
			throw new SenderServiceException(e);
		}
	}
}
