package Repositories;

public class MissingArgumentException extends IllegalArgumentException {
	public MissingArgumentException(String argument) {
		super(String.format("- Error-Instancia: Objecto incompleto [%s]", argument));
	}
}
