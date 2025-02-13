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
            System.out.println("5. Mostrar Rendimiento Mensual");
            System.out.println("------------------------------");
            System.out.println("6. Salir");
        
            opcion = Habits.HabitsValidation.validateInt("Ingrese la opción: ", 1, 6);
            
            if (opcion == 1) showHabits(dailyHabits, dailyHabitMinutes);
            if (opcion == 2) addHabit(habitsRoute, performanceRoute, dailyHabits, dailyHabitMinutes, habitTimeSpentDaily);
            if (opcion == 3) deleteHabit(habitsRoute, performanceRoute, dailyHabits, dailyHabitMinutes, habitTimeSpentDaily);
            if (opcion == 4) registerHabit(performanceRoute, dailyHabits, dailyHabitMinutes, habitTimeSpentDaily);
            if (opcion == 5) monthlyHabitTracker(dailyHabits, dailyHabitMinutes, habitTimeSpentDaily);
            if (opcion == 6);
        }
        else {
            System.out.println("\n¡Lo sentimos!. Esta función no está disponible en estos momentos");
            System.out.println("Vuelva a intentarlo más tarde\n");
        }
    }
    
    
    private static void showHabits(String[] dailyHabits, int[] dailyHabitMinutes) {
        
        if (dailyHabits != null && dailyHabitMinutes != null) {
            
            if (dailyHabits.length == 0) {
                
                System.out.println("\nTodavía no has fijado ningún hábito\n");
            }
            else {
                
                System.out.printf("%30s\n\n", "HÁBITOS FIJADOS");
                System.out.printf("%-40s %-40s\n", "HÁBITO", "MINUTOS");
        
                for (int i = 0; i < dailyHabits.length; i++) {
                    System.out.printf("%-40s %-40d\n", dailyHabits[i], dailyHabitMinutes[i]);
                }     
            }  
        }
        else {
            System.out.println("\n¡Lo sentimos!. Esta función no está disponible en estos momentos");
            System.out.println("Vuelva a intentarlo más tarde\n");
        }
    }
    
    
    private static void addHabit(String habitsRoute, String performanceRoute, String[] dailyHabits,
                                 int[] dailyHabitMinutes, int[][][] habitTimeSpentDaily) throws IOException {
        
        if (habitsRoute != null && performanceRoute != null && dailyHabits != null && dailyHabitMinutes != null
            && habitTimeSpentDaily != null) {
            
            String newHabit = "";
            int newMinutes = 0;
            String[] newDailyHabits;
            int[] newDailyHabitMinutes;
            int[][][] newHabitTimeSpentDaily;
        
            newHabit = Habits.HabitsValidation.validateString("Ingresa el nuevo hábito: ").toUpperCase();
            newMinutes = Habits.HabitsValidation.validateInt("Ingresa los minutos que quieres realizar: ", 1,  1440);
        
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
        else {
            System.out.println("\n¡Lo sentimos!. Esta función no está disponible en estos momentos");
            System.out.println("Vuelva a intentarlo más tarde\n");
        }
    }
    
    
    private static void deleteHabit(String habitsRoute, String performanceRoute, String[] dailyHabits,
                                    int[] dailyHabitMinutes, int [][][] habitTimeSpentDaily) throws IOException {
        
        if (habitsRoute != null && dailyHabits != null && dailyHabitMinutes != null) {
            
            String[] newHabitsVector;
            int habitPosition = 0;
            int[] newMinutesVector;
            int[][][] newHabitTimeSpentDaily;
        
            habitPosition = Habits.HabitsValidation.chosePosition(dailyHabits);
        
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
        else {
            System.out.println("\n¡Lo sentimos!. Esta función no está disponible en estos momentos");
            System.out.println("Vuelva a intentarlo más tarde\n");
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
            habitPosition = HabitsValidation.chosePosition(dailyHabits);
            minutes = Habits.HabitsValidation.validateInt("Ingresa los minutos que realizaste: ", 1, 1440);
            
            habitTimeSpentDaily[month][day][habitPosition] += minutes;
            Habits.HabitsValidation.savePerformance(route, habitTimeSpentDaily);
            System.out.println("Se ha registrado tu progreso");
            
            date = null;
        }
        else {
            System.out.println("\n¡Lo sentimos!. Esta función no está disponible en estos momentos");
            System.out.println("Vuelva a intentarlo más tarde\n");
        }
    }
    
    
    public static void monthlyHabitTracker(String[] dailyHabits, int[] dailyHabitsMinutes, int[][][] habitTimeSpentDaily) {
        
        if (dailyHabits != null && dailyHabitsMinutes != null && habitTimeSpentDaily != null) {
            int monthPosition = 0;
            int monthLastDay = 0;
            int[][] completedDays;
            int[] monthsDays;
            String month;
            String[] months = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto",
                               "Septiembre", "Octubre", "Noviembre", "Diciembre"};
            
            monthsDays = Habits.HabitsHelpers.getMonthsDays();
            monthPosition = Habits.HabitsValidation.chosePosition(months);
            month = months[monthPosition];
            monthLastDay = monthsDays[monthPosition];
            
            completedDays = new int[monthLastDay][dailyHabits.length];
            Habits.HabitsValidation.initializeArray(completedDays);
            Habits.HabitsHelpers.getCompletedDays(monthPosition, monthLastDay, dailyHabitsMinutes, habitTimeSpentDaily, completedDays);
            
            Habits.HabitsHelpers.showMonthlyHabitTracker(month, dailyHabits, completedDays);
            
            completedDays = null;
            monthsDays = null;
            months = null;
        }
        else {
            System.out.println("\n¡Lo sentimos!. Esta función no está disponible en estos momentos");
            System.out.println("Vuelva a intentarlo más tarde\n");
        }
    }
}
