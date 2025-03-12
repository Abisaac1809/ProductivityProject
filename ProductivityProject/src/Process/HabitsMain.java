
package Process;

import java.io.IOException;
import java.nio.file.Paths;

public class HabitsMain {
    
    public static void HabitsMain(String user) throws IOException {
        
        if (user != null) {
            
            String habitsUserRoute = "";
            String habitsRoute = "";
            String performanceRoute = "";
            String performanceUserRoute = "";
            int rows = 0;
            String[] dailyHabits;
            String[] routes;
            int[] dailyHabitMinutes;
            int[][][] habitTimeSpentDaily;
        
            habitsUserRoute = "//src//Storage//HabitsFiles//Habits." + user + ".txt";
            habitsRoute = Paths.get("").toAbsolutePath().toString() + habitsUserRoute;
            performanceUserRoute = "//src//Storage//HabitsFiles//Performance." + user + ".txt";
            performanceRoute = Paths.get("").toAbsolutePath().toString() + performanceUserRoute;
            
            rows = (Habits.HabitsValidation.userHasHabits(habitsRoute, performanceRoute)) ? (Validations.FileManager.determineRows(habitsRoute)) : 0;      
        
            dailyHabits = new String[rows];
            routes = new String[2];
            dailyHabitMinutes = new int[rows];
            habitTimeSpentDaily = new int[12][31][rows];
            
            Initializer.initializeVector(routes);
            Initializer.initializeVector(dailyHabits);
            Initializer.initializeVector(dailyHabitMinutes);
            Initializer.initializeArray(habitTimeSpentDaily);
            routes[0] = habitsRoute; routes[1] = performanceRoute;
            
            Helpers.HabitsFileReader.getDailyHabits(habitsRoute, dailyHabits, dailyHabitMinutes);
            Helpers.HabitsFileReader.getHabitTimeSpentDaily(performanceRoute, habitTimeSpentDaily);
            
            HabitsFunctionalities.habitsManagmentMenu(routes, dailyHabits, dailyHabitMinutes, habitTimeSpentDaily);
            
            dailyHabits = null;
            dailyHabitMinutes = null;
            habitTimeSpentDaily = null;
        }
    }
}
