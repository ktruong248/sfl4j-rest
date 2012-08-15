package api;

/**
 * generic logging service client runtime exception. It's responsibiliy of the source to set the errorCode correctly
 */
public class LoggingServiceClientException extends RuntimeException {
    private String errorCode;

    public LoggingServiceClientException(String errorCode, String message, Throwable throwable) {
        super(message, throwable);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
