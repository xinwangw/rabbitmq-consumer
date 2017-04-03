package local.rabbitmq;

import static org.junit.Assert.*;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import local.rabbitmq.model.AuditLogRequest;

public class ConvertAuditLog {

	@Test
	public void test() {
		String message = "{\"auditLog\":{\"actionDetails\":\"{\\\"breachId\\\":null,\\\"data\\\":{\\\"eventDefId\\\":2,\\\"breachType\\\":\\\"Processing\\\",\\\"product\\\":\\\"PAYMENT\\\",\\\"institutionName\\\":\\\"BASS & EQUITABLE BUILDING SOC LTD\\\",\\\"institutionCode\\\":\\\"BAE\\\",\\\"institutionType\\\":\\\"P\\\",\\\"participantInstitutionCode\\\":\\\"ASL\\\",\\\"institutionCategory\\\":\\\"AM\\\",\\\"reasonForBreach\\\":\\\"sdfsf\\\"}}\",\"logLevel\":\"INFO\",\"actionTime\":1491200006318,\"entityId\":\"3883\",\"componentId\":\"BreachDetailsResource\",\"userId\":\"bpayrcc\",\"hostTraces\":[\"RNP298.bpay.com.au\"],\"eventTypeCode\":\"CREATE_NON_COMPLIANCE_EVENT_SUCCESSFUL\",\"activitySource\":\"RNP298\",\"transactionDuration\":16,\"parametersForMessageFormat\":[\"bpayrcc\",\"CSL\",\"3883\"],\"eventDataValue\":null,\"institutionCode\":\"CSL\"}}";
		try {
			ObjectMapper mapper = new ObjectMapper();
			AuditLogRequest auditLog = mapper.readValue(message, AuditLogRequest.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
