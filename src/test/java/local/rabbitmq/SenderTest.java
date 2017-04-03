package local.rabbitmq;

import junit.framework.TestCase;
import local.rabbitmq.App;

import com.rabbitmq.client.ConnectionFactory;

import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rabbitmq.client.Channel;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@ActiveProfiles("test")
public class SenderTest extends TestCase {
	@Autowired
	private ConnectionFactory factory;
	
	@Autowired
	private AppConfiguration appConfig;
	
	private Channel channel;
	private Connection connection;
	
	@Before
	public void before() throws IOException, TimeoutException{
		connection = factory.newConnection();
	    channel = connection.createChannel();
	    channel.queueDeclare(appConfig.getQueueName(), false, false, false, null);
	}
	
	@After
	public void after() throws IOException, TimeoutException{
		channel.close();
	    connection.close();
	}
	
	@Test
	public void test() throws IOException, TimeoutException{
	    String message = "Hello World!";
	    channel.basicPublish("", appConfig.getQueueName(), null, message.getBytes());
	    System.out.println(" [x] Sent '" + message + "'");
	}
	
	@Test
	public void test2() throws IOException, TimeoutException{
	    String message = "Hello World 2!";
	    channel.basicPublish("", appConfig.getQueueName(), null, message.getBytes());
	    System.out.println(" [x] Sent '" + message + "'");
	}
}
