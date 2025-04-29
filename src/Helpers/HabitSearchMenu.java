package Helpers;

import Composables.FileManager;
import Functionalities.HabitsArrays;
import Process.Initializer;
import Repositories.ArchiveUtil;
import Repositories.DailyHabit;
import Structures.Node;
import Validations.DataValidations;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;


public class HabitSearchMenu {

    public static Node<DailyHabit> habitSearchMenu(String user, String[] serials) throws IOException {
        
        if (user != null) {
            String route = Paths.get("").toAbsolutePath().toString()+ "/src/Storage/HabitsFiles/";
            LocalDateTime actualDateTime = LocalDateTime.now();
            int rand = (int) (Math.random() * 20000) + 10000;
            String file1 = user + "habits_" + actualDateTime.now().toString().replace(':', '-') +"_"+rand;
            file1 = FileManager.getToFile(file1, route);
            serials[1]= FileManager.getFileSerial(file1);
            String habitsRoute = file1;
            file1 = user + "perfomance_" + actualDateTime.now().toString().replace(':', '-') +"_"+rand;
            file1 = FileManager.getToFile(file1, route);
			String performanceUserRoute = file1;
            int rows = 0;
            try {
                ArchiveUtil archiveUtil = new ArchiveUtil(route);
            }
            catch (FileNotFoundException | IllegalArgumentException e) {
			System.out.println("Error: " + e.getMessage());
			return null;
            }
            ArchiveUtil archiveUtil;
            String[] dailyHabits;
            String[] routes;
            int[] dailyHabitMinutes;
            int[][][] habitTimeSpentDaily;
            
            rows = FileManager.determineRows(route+habitsRoute);

            FileManager.createFileIfNotExists(route+habitsRoute);
            FileManager.createFileIfNotExists(route+performanceUserRoute);

            archiveUtil = new ArchiveUtil(route);
            dailyHabits = new String[rows];
            routes = new String[2];
            dailyHabitMinutes = new int[rows];
            habitTimeSpentDaily = new int[12][31][rows];
            
            Initializer.initializeVector(routes);
            Initializer.initializeVector(dailyHabits);
            Initializer.initializeVector(dailyHabitMinutes);
            Initializer.initializeArray(habitTimeSpentDaily);
        
            HabitsFileReader.getDailyHabits(habitsRoute, archiveUtil, dailyHabits, dailyHabitMinutes);
            HabitsFileReader.getHabitTimeSpentDaily(performanceUserRoute, archiveUtil, habitTimeSpentDaily);
            
            int[] completedDays = HabitsArrays.countCompletedDays(dailyHabitMinutes, habitTimeSpentDaily);
            
            DailyHabit habit = menu(dailyHabits, dailyHabitMinutes, completedDays);
            return new Node<>(habit);
        }
        return null;
    }
    
    private static DailyHabit menu(String[] dailyHabits, int[] dailyHabitMinutes, int[] completedDays) {
        if (dailyHabits == null || dailyHabitMinutes == null || completedDays == null) {
            throw new IllegalArgumentException("Los argumentos no pueden ser nulos");
        }
        int option;
        System.out.printf("\n-----------------------\n\n");
        System.out.println("Inserta el tipo de búsqueda de tu rendimiento");
        System.out.println("1. Búsqueda por hábito");
        System.out.println("------------------------");
        option = DataValidations.validateInt("Ingrese su opción- ", 1, 1);
        
        if (option == 1) {
            DailyHabit habit = searchByHabit(dailyHabits, dailyHabitMinutes, completedDays);
            return habit;
        }
        return null;
    }
    
    private static DailyHabit searchByHabit(String[] dailyHabits, int[] dailyHabitMinutes, int[] completedDays) {
        if (dailyHabits == null || dailyHabitMinutes == null || completedDays == null) {
            throw new IllegalArgumentException("Los argumentos no pueden ser nulos");
        }
        int option = DataValidations.chosePosition(dailyHabits);
        for (int i = 0; i < dailyHabits.length; i++) {
            if (i == option) {
                String habit = dailyHabits[i];
                int minutes = dailyHabitMinutes[i];
                int completedDaysInYear = completedDays[i];
                DailyHabit newHabit = new DailyHabit(habit, minutes, completedDaysInYear);
                return newHabit;
            }
        }
        return null;
    }
}