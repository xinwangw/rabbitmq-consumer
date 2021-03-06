package local.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

import com.rabbitmq.client.ConnectionFactory;

@SpringBootApplication
public class App {
	private static final Logger log = LoggerFactory.getLogger(App.class);
	
	@Value("${rabbitmq.host}")
	private String host;
	
	@Value("${rabbitmq.username}")
	private String username;
	
	@Value("${rabbitmq.password}")
	private String password;
	
	@Value("${rabbitmq.port}")
	private int port;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public ConnectionFactory factory() {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(host);
		factory.setUsername(username);
		factory.setPassword(password);
		factory.setPort(port);
		return factory;
	}
	
	@Bean
	public HibernateJpaSessionFactoryBean sessionFactory() {
	    return new HibernateJpaSessionFactoryBean();
	}

}
