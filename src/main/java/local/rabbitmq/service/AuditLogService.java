package local.rabbitmq.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import local.rabbitmq.entity.AuditLog;
import local.rabbitmq.repository.AuditLogRepository;

@Component
public class AuditLogService {
	private static final Logger log = LoggerFactory.getLogger(AuditLogService.class);
	
	@Autowired
	private AuditLogRepository repo;
	
	public void save(AuditLog message){
		repo.saveAndFlush(message);
	}
	
	public List<AuditLog> findAll(){
		return repo.findAll();
	}

	public void clearAll(){
		repo.deleteAll();
	}
}
