
package Habits;

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
            int[] dailyHabitMinutes;
            int[][][] habitTimeSpentDaily;
        
            habitsUserRoute = "//src//Habits//HabitsDataBase//Habits." + user + ".txt";
            habitsRoute = Paths.get("").toAbsolutePath().toString() + habitsUserRoute;
            performanceUserRoute = "//src//Habits//HabitsDataBase//Performance." + user + ".txt";
            performanceRoute = Paths.get("").toAbsolutePath().toString() + performanceUserRoute;
            
            rows = (Habits.HabitsValidation.userHasHabits(habitsRoute, performanceRoute)) ? (Habits.HabitsValidation.determineRows(habitsRoute)) : 0;      
        
            dailyHabits = new String[rows];
            dailyHabitMinutes = new int[rows];
            habitTimeSpentDaily = new int[12][31][rows];
        
            Habits.HabitsValidation.initializeVector(dailyHabits);
            Habits.HabitsValidation.initializeVector(dailyHabitMinutes);
            Habits.HabitsValidation.initializeArray(habitTimeSpentDaily);
            
            Habits.HabitsValidation.getDailyHabits(habitsRoute, dailyHabits, dailyHabitMinutes);
            Habits.HabitsValidation.getHabitTimeSpentDaily(performanceRoute, habitTimeSpentDaily);
            
            Habits.HabitsDevelopment.habitsManagmentMenu(habitsRoute, performanceRoute, dailyHabits, dailyHabitMinutes, habitTimeSpentDaily);
            
            dailyHabits = null;
            dailyHabitMinutes = null;
            habitTimeSpentDaily = null;
        }
    }
}
