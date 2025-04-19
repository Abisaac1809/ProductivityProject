package Repositories;

public class ObjectValidationException extends IllegalArgumentException {
	public ObjectValidationException(String message) {
		super(String.format("- Error-Validacion: [%s]", message));
	}
}
