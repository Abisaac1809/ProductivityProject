package Repositories;

public class DailyHabit {
    private String habit;
    private int minutes;

    public DailyHabit(String habit, int minutes) {
        if (habit == null)
            throw new IllegalArgumentException("falta el argumento habit");
        if (minutes < 0 || minutes > 1440)
            throw new IllegalArgumentException("Los minutos deben estar entre 0 y 1440");

        validateHabit(habit);
        this.habit = habit;
        this.minutes = minutes;
    }

    public String getHabit() {
        return this.habit;
    }

    public int getMinutes() {
        return this.minutes;
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

    private void validateHabit(String habit) {
        if (habit.trim().isEmpty())
            throw new IllegalArgumentException("el hábito debe contener al menos 1 carácter");
    }

    public String toStringContent() {
		return "Habitos: "+this.habit + ", " + "Tiempo: "+ this.minutes;
	}
}