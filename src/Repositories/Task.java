package Repositories;

/**
 * Representa una tarea con su título, descripción y estado de completitud.
 * 
 * Permite gestionar y validar la información básica de una tarea,
 * incluyendo su estado de progreso.
 */
public class Task {
    private String title;
    private String description;
    private String status;

    /**
     * Constructor para crear un objeto Task.
     * 
     * @param title Título de la tarea (1-20 caracteres)
     * @param description Descripción detallada de la tarea (mínimo 1 carácter)
     * @param status Estado de la tarea ("Hecha" o "No Hecha")
     * @throws IllegalArgumentException Si alguno de los parámetros es inválido o nulo
     */
    public Task(String title, String description, String status) {
        if (title == null)
            throw new IllegalArgumentException("Error: falta titulo");
        if (description == null)
            throw new IllegalArgumentException("Error: falta descripcion");
        if (status == null)
            throw new IllegalArgumentException("Error: falta estado");
        validateTitle(title);
        validateDescription(description);
        validateStatus(status);
        this.title = title;
        this.description = description;
        this.status = status;
    }

    /**
	 * Obtiene el título de la tarea
	 * 
     * @return Título de la tarea
     */
    public String getTitle() {
        return this.title;
    }

    /**
	 * Obtiene el estado actual de la tarea
	 * 
     * @return Estado actual de la tarea
     */
    public String getStatus() {
        return this.status;
    }

    /**
	 * Obtiene la descripción de la tarea
	 * 
     * @return Descripción de la tarea
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Valida que el título cumpla con los requisitos de longitud.
     * 
     * @param title Título a validar
     * @throws IllegalArgumentException Si el título no tiene entre 1 y 20 caracteres
     */
    private void validateTitle(String title) {
        boolean isValid = title.length() > 0 && title.length() <= 20;
        if (!isValid)
            throw new IllegalArgumentException("titulo debe contener entre 1 y 20 caracters");
    }

    /**
     * Valida que la descripción no esté vacía.
     * 
     * @param description Descripción a validar
     * @throws IllegalArgumentException Si la descripción está vacía
     */
    private void validateDescription(String description) {
        if (description.isEmpty())
            throw new IllegalArgumentException("la descripcion debe tener minimo 1 caracter");
    }

    /**
     * Valida que el estado sea uno de los permitidos.
     * 
     * @param status Estado a validar
     * @throws IllegalArgumentException Si el estado no es "Hecha" o "No Hecha"
     */
    private void validateStatus(String status) {
        if (!status.equals("No Hecha") && !status.equals("Hecha"))
            throw new IllegalArgumentException("estado de tarea incorrecto [" + status + "]");
    }

    /**
     * Genera una representación en formato String de la tarea.
     * 
     * @return String formateado con la información de la tarea
     */
    public String toStringContent() {
        return this.title + ", " + this.description + ", " + this.status;
    }
}