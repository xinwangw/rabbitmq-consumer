package local.rabbitmq.model;

import java.util.List;

import local.rabbitmq.entity.AuditLog;

public class AuditLogResponse {
	private List<AuditLog> data;

	public List<AuditLog> getData() {
		return data;
	}

	public void setData(List<AuditLog> data) {
		this.data = data;
	}

}
