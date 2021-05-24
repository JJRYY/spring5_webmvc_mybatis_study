package spring5_webmvc_mybatis_study.exception;

@SuppressWarnings("serial")
public class DuplicateMemberException extends RuntimeException {

	public DuplicateMemberException(String message) {
		super(message);
	}

}
