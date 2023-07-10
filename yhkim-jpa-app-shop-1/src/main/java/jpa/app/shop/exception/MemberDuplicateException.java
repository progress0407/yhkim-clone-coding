package jpa.app.shop.exception;

public class MemberDuplicateException extends RuntimeException {
	public MemberDuplicateException(String message) {
		super(message);
	}
}
