package Repositories;

/**
 * Representa un hábito diario con su tiempo de realización y progreso.
 * 
 * Almacena información sobre un hábito específico, los minutos dedicados diariamente
 * y la cantidad de días en los que se ha completado el hábito.
 */
public class DailyHabit {
    private String habit;
    private int minutes;
    private int completedDays;

    /**
     * Constructor para crear un objeto DailyHabit.
     * 
     * @param habit Nombre o descripción del hábito
     * @param minutes Minutos dedicados al hábito diariamente (0-1440)
     * @param completedDays Días en los que se completó el hábito (0-365)
     * @throws IllegalArgumentException Si alguno de los parámetros es inválido
     */
    public DailyHabit(String habit, int minutes, int completedDays) {
        if (habit == null)
            throw new IllegalArgumentException("Error: Falta nombre o descripción del hábito");
        if (minutes < 0 || minutes > 1440)
            throw new IllegalArgumentException("Los minutos deben estar entre 0 y 1440");
        if (completedDays < 0 || completedDays > 365) {
            throw new IllegalArgumentException("Los días completados deben estar entre 0 y 365");
        }
        validateHabit(habit);

        this.habit = habit;
        this.minutes = minutes;
        this.completedDays = completedDays;
    }

    /**
     * Obtiene el nombre o descripción del hábito
     * 
     * @return Nombre o descripción del hábito
     */
    public String getHabit() {
        return this.habit;
    }

    /**
     * Obtiene los minutos dedicados al hábito diariamente
     * 
     * @return Minutos dedicados al hábito diariamente
     */
    public int getMinutes() {
        return this.minutes;
    }

    /**
     * Obtiene los días en los que se completó el hábito
     * 
     * @return Días en los que se completó el hábito
     */
    public int getCompletedDays() {
        return this.completedDays;
    }

    /**
     * Establece un nuevo nombre o descripción para el hábito.
     * 
     * @param habit Nuevo nombre o descripción del hábito
     * @throws IllegalArgumentException Si el hábito es nulo o vacío
     */
    public void setHabit(String habit) {
        if (habit == null)
            throw new IllegalArgumentException("falta el argumento habit");
        validateHabit(habit);
        this.habit = habit;
    }

    /**
     * Establece los minutos dedicados al hábito diariamente.
     * 
     * @param minutes Minutos dedicados al hábito (0-1440)
     * @throws IllegalArgumentException Si los minutos están fuera del rango válido
     */
    public void setMinutes(int minutes) {
        if (minutes < 0 || minutes > 1440)
            throw new IllegalArgumentException("Los minutos deben estar entre 0 y 1440");
        this.minutes = minutes;
    }

    /**
     * Establece los días en los que se completó el hábito.
     * 
     * @param completedDays Días completados (0-365)
     * @throws IllegalArgumentException Si los días están fuera del rango válido
     */
    public void setCompletedDays(int completedDays) {
        if (completedDays < 0 || completedDays > 365)
            throw new IllegalArgumentException("Los días completados deben estar entre 0 y 365");
        this.completedDays = completedDays;
    }

    /**
     * Valida que el hábito no esté vacío.
     * 
     * @param habit Hábito a validar
     * @throws IllegalArgumentException Si el hábito está vacío
     */
    private void validateHabit(String habit) {
        if (habit.trim().isEmpty())
            throw new IllegalArgumentException("Error: El hábito debe contener al menos 1 carácter");
    }

    /**
     * Genera una representación en formato String del hábito y sus estadísticas.
     * 
     * @return String formateado con la información del hábito
     */
    public String toStringContent() {
        return String.format("Hábito- %s\nMinutos realizados- %d\nDías completados- %d/365\n", 
                this.habit, this.minutes, this.completedDays);
    }
}