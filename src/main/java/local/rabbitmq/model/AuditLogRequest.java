package local.rabbitmq.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import local.rabbitmq.entity.AuditLog;

public class AuditLogRequest {
	private AuditLog auditLog;

	@JsonCreator
	public static AuditLogRequest Create(String jsonString) {

		AuditLogRequest pc = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			pc = mapper.readValue(jsonString, AuditLogRequest.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pc;
	}

	public AuditLog getAuditLog() {
		return auditLog;
	}

	public void setAuditLog(AuditLog auditLog) {
		this.auditLog = auditLog;
	}

}
