package Repositories;

public class DailyHabit {
    private String habit;
    private int minutes;
    private int completedDays;

    public DailyHabit(String habit, int minutes, int completedDays) {
        if (habit == null)
            throw new IllegalArgumentException("falta el argumento habit");
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

    public String getHabit() {
        return this.habit;
    }

    public int getMinutes() {
        return this.minutes;
    }

    public int getCompletedDays() {
        return this.completedDays;
    }

    public void setHabit(String habit) {
        if (habit == null)
            throw new IllegalArgumentException("falta el argumento habit");
        validateHabit(habit);
        this.habit = habit;
    }

    public void setMinutes(int minutes) {
        if (minutes < 0 || minutes > 1440)
            throw new IllegalArgumentException("Los minutos deben estar entre 0 y 1440");
        this.minutes = minutes;
    }

    public void setCompletedDays(int completedDays) {
        if (completedDays < 0 || completedDays > 365)
            throw new IllegalArgumentException("Los días completados deben estar entre 0 y 365");
        this.completedDays = completedDays;
    }

    private void validateHabit(String habit) {
        if (habit.trim().isEmpty())
            throw new IllegalArgumentException("el hábito debe contener al menos 1 carácter");
    }

    public String toStringContent() {
		return String.format("Hábito: %s\nMinutos realizados: %d\nDías completados: %d/365\n", this.habit, this.minutes, this.completedDays);
	}
}