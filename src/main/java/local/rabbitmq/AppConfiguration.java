package local.rabbitmq;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
	@Value("${rabbitmq.queue.name}")
	private String queueName;

	public String getQueueName() {
		return queueName;
	}
}
