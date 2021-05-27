package spring5_webmvc_mybatis_study.dto;

public class ErrorResponse {
	private String message;

	public ErrorResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
