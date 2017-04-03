package local.rabbitmq.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import local.rabbitmq.entity.AuditLog;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

}
