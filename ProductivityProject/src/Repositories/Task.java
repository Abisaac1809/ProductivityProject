package Repositories;

public class Task {
	private String title;
	private String description;
	private String status;

	public Task(String title, String description, String status) {
		if (title == null)
			throw new MissingArgumentException("title");
		if (description == null)
			throw new MissingArgumentException("description");
		if (status == null)
			throw new MissingArgumentException("status");
		validateTitle(title);
		validateDescription(description);
		validateStatus(status);
		this.title = title;
		this.description = description;
		this.status = status;
	}

	public String getTitle() {
		return this.title;
	}

	public String getStatus() {
		return this.status;
	}

	public String getDescription() {
		return this.description;
	}

	private void validateTitle(String title) {
		boolean isValid = title.length() > 0 && title.length() <= 20;
		if (!isValid)
			throw new ObjectValidationException("titulo debe contener entre 1 y 20 caracters");
	}

	private void validateDescription(String description) {
		if (description.isEmpty())
			throw new ObjectValidationException("la descripcion debe tener minimo 1 caracter");
	}

	private void validateStatus(String status) {
		if (!status.equals("No Hecha") && !status.equals("Hecha"))
			throw new ObjectValidationException("estado de tarea incorrecto [" + status + "]");
	}
}
