package local.rabbitmq.resource;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import local.rabbitmq.exception.SenderServiceException;
import local.rabbitmq.model.RabbitmqRequest;
import local.rabbitmq.model.RabbitmqResponse;
import local.rabbitmq.model.ReceivedMessageResponse;
import local.rabbitmq.service.ReceivedMessageService;
import local.rabbitmq.service.SenderService;

@RestController
@RequestMapping("/api")
public class RabbitmqResource {
	@Autowired
	private SenderService sender;
	
	@Autowired
	private ReceivedMessageService service;

	@RequestMapping(value = "/send", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<RabbitmqResponse> inbox(@RequestBody RabbitmqRequest request) throws SenderServiceException {
		RabbitmqResponse response = new RabbitmqResponse();
		sender.publish(request.getMessage());
		return new ResponseEntity<RabbitmqResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/received", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<ReceivedMessageResponse> inbox(){
		ReceivedMessageResponse response = new ReceivedMessageResponse();
		response.setData(service.findAll());
		return new ResponseEntity<ReceivedMessageResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/received", method = RequestMethod.DELETE, produces = "application/json")
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
