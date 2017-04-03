package local.rabbitmq.exception;

public class SenderServiceException extends Exception{

	private static final long serialVersionUID = -8268350889723321885L;

	public SenderServiceException() {
		super();
	}

	public SenderServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SenderServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public SenderServiceException(String message) {
		super(message);
	}

	public SenderServiceException(Throwable cause) {
		super(cause);
	}

}
