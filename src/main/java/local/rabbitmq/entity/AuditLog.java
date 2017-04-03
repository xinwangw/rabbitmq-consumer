package local.rabbitmq.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "audit_log")
public class AuditLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	long id;

	@Column(name = "action_details", length = 1024)
	private String actionDetails;

	@Column(name = "log_level")
	private String logLevel;

	@Column(name = "action_time")
	private Date actionTime;
	
	@Column(name = "entity_id")
	private String entityId;
	
	@Column(name = "component_id")
	private String componentId;
	
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "event_type_code")
	private String eventTypeCode;
	
	@Column(name = "activity_source")
	private String activitySource;
	
	@Column(name = "transaction_duration")
	private int transactionDuration;
	
	@Column(name = "institution_code")
	private String institutionCode;
	
	@Transient
	private String[] hostTraces;
	
	@Transient
	private String[] parametersForMessageFormat;
	
	@Transient
	private String eventDataValue;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getActionDetails() {
		return actionDetails;
	}

	public void setActionDetails(String actionDetails) {
		this.actionDetails = actionDetails;
	}

	public String getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}

	public Date getActionTime() {
		return actionTime;
	}

	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getComponentId() {
		return componentId;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEventTypeCode() {
		return eventTypeCode;
	}

	public void setEventTypeCode(String eventTypeCode) {
		this.eventTypeCode = eventTypeCode;
	}

	public String getActivitySource() {
		return activitySource;
	}

	public void setActivitySource(String activitySource) {
		this.activitySource = activitySource;
	}

	public int getTransactionDuration() {
		return transactionDuration;
	}

	public void setTransactionDuration(int transactionDuration) {
		this.transactionDuration = transactionDuration;
	}

	public String getInstitutionCode() {
		return institutionCode;
	}

	public void setInstitutionCode(String institutionCode) {
		this.institutionCode = institutionCode;
	}

	public String[] getHostTraces() {
		return hostTraces;
	}

	public void setHostTraces(String[] hostTraces) {
		this.hostTraces = hostTraces;
	}

	public String[] getParametersForMessageFormat() {
		return parametersForMessageFormat;
	}

	public void setParametersForMessageFormat(String[] parametersForMessageFormat) {
		this.parametersForMessageFormat = parametersForMessageFormat;
	}

	public String getEventDataValue() {
		return eventDataValue;
	}

	public void setEventDataValue(String eventDataValue) {
		this.eventDataValue = eventDataValue;
	}
	
}
