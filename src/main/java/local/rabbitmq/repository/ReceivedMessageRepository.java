package local.rabbitmq.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import local.rabbitmq.entity.ReceivedMessage;

public interface ReceivedMessageRepository extends JpaRepository<ReceivedMessage, Long>{

}
