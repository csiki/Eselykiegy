package library;

public class NotEnoughAlcoholException extends Exception {

	private static final long serialVersionUID = -7538803841911158763L;
	
	NotEnoughAlcoholException(String msg) {
		super(msg);
	}
	
	NotEnoughAlcoholException(String msg, Throwable throwable) {
		super(msg, throwable);
	}
}
