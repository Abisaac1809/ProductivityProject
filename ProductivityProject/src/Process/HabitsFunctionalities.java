package Process;

import Habits.HabitsValidation;
import java.io.IOException;

public class HabitsFunctionalities {
    public static void habitsManagmentMenu(String[] routes, String[] dailyHabits, int[] dailyHabitMinutes, int[][][] habitTimeSpentDaily) throws IOException {
        
        if (routes != null && dailyHabits != null && dailyHabitMinutes != null && habitTimeSpentDaily != null) {
            
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
            
            if (opcion == 1) Helpers.HabitsDisplay.showHabits(dailyHabits, dailyHabitMinutes);
            if (opcion == 2) addHabit(routes, dailyHabits, dailyHabitMinutes, habitTimeSpentDaily);
            if (opcion == 3) deleteHabit(routes, dailyHabits, dailyHabitMinutes, habitTimeSpentDaily);
            if (opcion == 4) registerHabit(routes[1], dailyHabits, dailyHabitMinutes, habitTimeSpentDaily);
            if (opcion == 5) monthlyHabitTracker(dailyHabits, dailyHabitMinutes, habitTimeSpentDaily);
            if (opcion == 6);
        }
        else {
            System.out.println("\n¡Lo sentimos!. Esta función no está disponible en estos momentos");
            System.out.println("Vuelva a intentarlo más tarde\n");
        }
    }
    
    private static void addHabit(String[] routes, String[] dailyHabits, int[] dailyHabitMinutes, int[][][] habitTimeSpentDaily) throws IOException {
        
        if (routes != null && dailyHabits != null && dailyHabitMinutes != null
            && habitTimeSpentDaily != null) {
            
            String newHabit = "";
            int newMinutes = 0;
            String[] newDailyHabits;
            int[] newDailyHabitMinutes;
            int[][][] newHabitTimeSpentDaily;
        
            newHabit = Validations.DataValidatior.validateString("Ingresa el nuevo hábito: ").toUpperCase();
            newMinutes = Validations.DataValidatior.validateInt("Ingresa los minutos que quieres realizar: ", 1,  1440);
            
            if (Validations.HabitsValidations.habitNameExist(dailyHabits, 0, newHabit)) {
                System.out.println("\nError: [El hábito " + newHabit + " ya existe]\n");
                return;   
            }
            newDailyHabits = new String[dailyHabits.length + 1];
            newDailyHabitMinutes = new int[dailyHabitMinutes.length + 1];
            newHabitTimeSpentDaily = new int[12][31][dailyHabits.length + 1];
        
            Initializer.initializeVector(newDailyHabits);
            Initializer.initializeVector(newDailyHabitMinutes);
            Initializer.initializeArray(newHabitTimeSpentDaily);
        
            HabitsArrays.appendHabit(dailyHabits, dailyHabitMinutes, newDailyHabits, newDailyHabitMinutes, newHabit, newMinutes);
            HabitsArrays.appendHabitInPerformance(habitTimeSpentDaily, newHabitTimeSpentDaily);
            
            Composables.HabitsFileWriter.saveHabits(routes[0], newDailyHabits, newDailyHabitMinutes);
            Composables.HabitsFileWriter.savePerformance(routes[1], newHabitTimeSpentDaily);
            
            newDailyHabits = null;
            newDailyHabitMinutes = null;
            newHabitTimeSpentDaily = null;
        }
        else {
            System.out.println("\n¡Lo sentimos!. Esta función no está disponible en estos momentos");
            System.out.println("Vuelva a intentarlo más tarde\n");
        }
    }
    
    
    private static void deleteHabit(String[] routes, String[] dailyHabits, int[] dailyHabitMinutes, int [][][] habitTimeSpentDaily) throws IOException {
        
        if (routes != null && dailyHabits != null && dailyHabitMinutes != null) {
            
            String[] newHabitsVector;
            int habitPosition = 0;
            int[] newMinutesVector;
            int[][][] newHabitTimeSpentDaily;
        
            habitPosition = Validations.DataValidatior.chosePosition(dailyHabits);
        
            newHabitsVector = new String[dailyHabits.length - 1];
            newMinutesVector = new int[dailyHabitMinutes.length - 1];
            newHabitTimeSpentDaily = new int[12][31][dailyHabits.length - 1];
        
            Initializer.initializeVector(newHabitsVector);
            Initializer.initializeVector(newMinutesVector);
            Initializer.initializeArray(newHabitTimeSpentDaily);
        
            HabitsArrays.popHabit(habitPosition, dailyHabits, dailyHabitMinutes, newHabitsVector, newMinutesVector);
            HabitsArrays.popHabitInPerformance(habitPosition, dailyHabits, habitTimeSpentDaily, newHabitTimeSpentDaily);
            
            Composables.HabitsFileWriter.saveHabits(routes[0], newHabitsVector, newMinutesVector);
            Composables.HabitsFileWriter.savePerformance(routes[1], newHabitTimeSpentDaily);
        
            newHabitsVector = null;
            newMinutesVector = null;
            newHabitTimeSpentDaily = null;
        }
        else {
            System.out.println("\n¡Lo sentimos!. Esta función no está disponible en estos momentos");
            System.out.println("Vuelva a intentarlo más tarde\n");
        }
    }
    
    
    public static void registerHabit(String route, String[] dailyHabits, int[] dailyHabitMinutes, int[][][] habitTimeSpentDaily) throws IOException {
        
        if (route != null && dailyHabits != null && dailyHabitMinutes != null && habitTimeSpentDaily != null && dailyHabits.length == dailyHabitMinutes.length) {
            
            int day = 0;
            int month = 0;
            int habitPosition = 0;
            int minutes = 0;
            int[] date = Dates.getDate();
           
            day = date[0] - 1;
            month = date[1] - 1;
            habitPosition = Validations.DataValidatior.chosePosition(dailyHabits);
            minutes = Validations.DataValidatior.validateInt("Ingresa los minutos que realizaste: ", 1, 1440);
            
            habitTimeSpentDaily[month][day][habitPosition] += minutes;
            Composables.HabitsFileWriter.savePerformance(route, habitTimeSpentDaily);
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
            
            monthsDays = Dates.getMonthsDays();
            monthPosition = Validations.DataValidatior.chosePosition(months);
            month = months[monthPosition];
            monthLastDay = monthsDays[monthPosition];
            
            completedDays = new int[monthLastDay][dailyHabits.length];
            Initializer.initializeArray(completedDays);
            HabitsArrays.getCompletedDays(monthPosition, monthLastDay, dailyHabitsMinutes, habitTimeSpentDaily, completedDays);
            
            Helpers.HabitsDisplay.showMonthlyHabitTracker(month, dailyHabits, completedDays);
            
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
