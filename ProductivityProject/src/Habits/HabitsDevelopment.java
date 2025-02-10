package Habits;

import java.io.IOException;

public class HabitsDevelopment {
    
    public static void habitsManagmentMenu(String habitsRoute, String performanceRoute, String[] dailyHabits,
                                            int[] dailyHabitMinutes, int[][][] habitTimeSpentDaily) throws IOException {
        
        if (habitsRoute != null && performanceRoute != null && dailyHabits != null && dailyHabitMinutes != null
            && habitTimeSpentDaily != null) {
            
            int opcion = 0;
        
            System.out.printf("\n%30s", "Gestión de Hábitos\n\n");
            System.out.println("1. Mostrar Hábitos Fijados");
            System.out.println("2. Fijar Hábito");
            System.out.println("3. Eliminar Hábito");
            System.out.println("4. Registrar progreso");
        
            opcion = Habits.HabitsValidation.validateInt("Ingrese la opción: ", 1, 4);
        
            switch(opcion) {
                case 1:
                    showHabits(dailyHabits, dailyHabitMinutes);
                    break;
            
                case 2: 
                    addHabit(habitsRoute, performanceRoute, dailyHabits, dailyHabitMinutes, habitTimeSpentDaily);
                    break;
                
                case 3:
                    deleteHabit(habitsRoute, performanceRoute, dailyHabits, dailyHabitMinutes, habitTimeSpentDaily);
                    break;
    
                case 4:
                    registerHabit(performanceRoute, dailyHabits, dailyHabitMinutes, habitTimeSpentDaily);
                    break;
            }
        }
    }
    
    
    private static void showHabits(String[] dailyHabits, int[] dailyHabitMinutes) {
        
        if (dailyHabits != null && dailyHabitMinutes != null) {
            
            if (dailyHabits.length == 0) {
                
                System.out.println("\nTodavía no has fijado ningún hábito\n");
            }
            else {
                
                System.out.printf("%30s\n\n", "Hábitos Fijados");
                System.out.printf("%-40s %-40s\n", "Hábito", "Minutos");
        
                for (int i = 0; i < dailyHabits.length; i++) {
                    System.out.printf("%-40s %-40d\n", dailyHabits[i], dailyHabitMinutes[i]);
                }     
            }  
        }
    }
    
    
    private static void addHabit(String habitsRoute, String performanceRoute, String[] dailyHabits,
                                 int[] dailyHabitMinutes, int[][][] habitTimeSpentDaily) throws IOException {
        
        if (habitsRoute != null && dailyHabits != null && dailyHabitMinutes != null) {
            
            String newHabit = "";
            int newMinutes = 0;
            String[] newDailyHabits;
            int[] newDailyHabitMinutes;
            int[][][] newHabitTimeSpentDaily;
        
            newHabit = Habits.HabitsValidation.validateString("Ingresa el nuevo hábito: ");
            newMinutes = Habits.HabitsValidation.validateInt("Ingresa los minutos que quieres realizar: ");
        
            newDailyHabits = new String[dailyHabits.length + 1];
            newDailyHabitMinutes = new int[dailyHabitMinutes.length + 1];
            newHabitTimeSpentDaily = new int[12][31][dailyHabits.length + 1];
        
            Habits.HabitsValidation.initializeVector(newDailyHabits);
            Habits.HabitsValidation.initializeVector(newDailyHabitMinutes);
            Habits.HabitsValidation.initializeArray(newHabitTimeSpentDaily);
        
            Habits.HabitsHelpers.appendHabit(dailyHabits, dailyHabitMinutes, newDailyHabits, newDailyHabitMinutes, newHabit, newMinutes);
            Habits.HabitsHelpers.appendHabitInPerformance(habitTimeSpentDaily, newHabitTimeSpentDaily);
            
            Habits.HabitsValidation.saveHabits(habitsRoute, newDailyHabits, newDailyHabitMinutes);
            Habits.HabitsValidation.savePerformance(performanceRoute, newHabitTimeSpentDaily);
            
            newDailyHabits = null;
            newDailyHabitMinutes = null;
            newHabitTimeSpentDaily = null;
        }

    }
    
    
    private static void deleteHabit(String habitsRoute, String performanceRoute, String[] dailyHabits,
                                    int[] dailyHabitMinutes, int [][][] habitTimeSpentDaily) throws IOException {
        
        if (habitsRoute != null && dailyHabits != null && dailyHabitMinutes != null) {
            
            String[] newHabitsVector;
            int habitPosition = 0;
            int[] newMinutesVector;
            int[][][] newHabitTimeSpentDaily;
        
            habitPosition = Habits.HabitsValidation.numberOfHabit("Ingresa el hábito que deseas eliminar: ", dailyHabits);
        
            newHabitsVector = new String[dailyHabits.length - 1];
            newMinutesVector = new int[dailyHabitMinutes.length - 1];
            newHabitTimeSpentDaily = new int[12][31][dailyHabits.length - 1];
        
            Habits.HabitsValidation.initializeVector(newHabitsVector);
            Habits.HabitsValidation.initializeVector(newMinutesVector);
            Habits.HabitsValidation.initializeArray(newHabitTimeSpentDaily);
        
            Habits.HabitsHelpers.popHabit(habitPosition, dailyHabits, dailyHabitMinutes, newHabitsVector, newMinutesVector);
            Habits.HabitsHelpers.popHabitInPerformance(habitPosition, dailyHabits, habitTimeSpentDaily, newHabitTimeSpentDaily);
            
            Habits.HabitsValidation.saveHabits(habitsRoute, newHabitsVector, newMinutesVector);
            Habits.HabitsValidation.savePerformance(performanceRoute, newHabitTimeSpentDaily);
        
            newHabitsVector = null;
            newMinutesVector = null;
            newHabitTimeSpentDaily = null;
        }
    }
    
    
    public static void registerHabit(String route, String[] dailyHabits, int[] dailyHabitMinutes,
                                     int[][][] habitTimeSpentDaily) throws IOException {
        
        if (route != null && dailyHabits != null && dailyHabitMinutes != null
            && habitTimeSpentDaily != null && dailyHabits.length == dailyHabitMinutes.length) {
            
            int day = 0;
            int month = 0;
            int habitPosition = 0;
            int minutes = 0;
            int[] date = Habits.HabitsHelpers.getDate();
           
            day = date[0] - 1;
            month = date[1] - 1;
            habitPosition = HabitsValidation.numberOfHabit("Ingresa el hábito que deseas registrar: ",dailyHabits);
            minutes = Habits.HabitsValidation.validateInt("Ingresa los minutos que realizaste: ", 1, 1440);
            
            habitTimeSpentDaily[month][day][habitPosition] += minutes;
            Habits.HabitsValidation.savePerformance(route, habitTimeSpentDaily);
            System.out.println("Se ha registrado tu progreso correctamente");
        }
    }
}
