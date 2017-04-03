package local.rabbitmq.resource;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import local.rabbitmq.exception.SenderServiceException;
import local.rabbitmq.model.AuditLogResponse;
import local.rabbitmq.model.RabbitmqResponse;
import local.rabbitmq.service.AuditLogService;

@RestController
@RequestMapping("/api")
public class AuditLogResource {
	@Autowired
	private AuditLogService service;
	
	@RequestMapping(value = "/auditlog", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<AuditLogResponse> inbox(){
		AuditLogResponse response = new AuditLogResponse();
		response.setData(service.findAll());
		return new ResponseEntity<AuditLogResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/auditlog", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<String> deleteAll(){
		service.clearAll();
		return new ResponseEntity<String>("Deleted All.", HttpStatus.OK);
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR) // 500
	@ExceptionHandler({ SenderServiceException.class })
	protected ResponseEntity<RabbitmqResponse> handleBindException(HttpServletRequest req, SenderServiceException ex) {
		RabbitmqResponse response = new RabbitmqResponse();
		response.setError("Server error.");
		return new ResponseEntity<RabbitmqResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
