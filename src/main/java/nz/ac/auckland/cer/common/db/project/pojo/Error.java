package nz.ac.auckland.cer.common.db.project.pojo;

public class Error {

	private String message;
	private String[] stackTrace;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String[] getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String[] stackTrace) {
		this.stackTrace = stackTrace;
	}
}
