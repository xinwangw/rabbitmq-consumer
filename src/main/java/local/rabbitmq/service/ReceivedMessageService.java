package local.rabbitmq.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import local.rabbitmq.entity.ReceivedMessage;
import local.rabbitmq.repository.ReceivedMessageRepository;

@Component
public class ReceivedMessageService {
	private static final Logger log = LoggerFactory.getLogger(ReceivedMessageService.class);
	
	@Autowired
	private ReceivedMessageRepository repo;
	
	public void save(ReceivedMessage message){
		repo.saveAndFlush(message);
	}
	
	public List<ReceivedMessage> findAll(){
		return repo.findAll();
	}

	public void clearAll(){
		repo.deleteAll();
	}
}
