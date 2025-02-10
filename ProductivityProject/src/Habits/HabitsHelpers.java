package Habits;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class HabitsHelpers {
    
    public static void appendHabit(String[] dailyHabits, int[] dailyHabitMinutes,
                                    String[] newDailyHabits, int[] newDailyHabitMinutes,
                                    String newHabit, int newMinutes){
        
        if (dailyHabits != null && dailyHabitMinutes != null && newDailyHabits != null &&
            newDailyHabitMinutes != null && newHabit != null && newMinutes > 0) {
            
            for (int i = 0; i < (newDailyHabits.length - 1); i++) {
                newDailyHabits[i] = dailyHabits[i];
                newDailyHabitMinutes[i] = dailyHabitMinutes[i];
            }
        
        newDailyHabits[newDailyHabits.length - 1] = newHabit;
        newDailyHabitMinutes[newDailyHabitMinutes.length - 1] = newMinutes; 
        System.out.printf("\nSe ha fijado el hábito: %s\n", newHabit);
        }
    }
        
        
    public static void appendHabitInPerformance(int[][][] habitTimeSpentDaily, int[][][] newHabitTimeSpentDaily) {
        
        if (habitTimeSpentDaily != null && newHabitTimeSpentDaily != null) {
            
            for (int i = 0; i < habitTimeSpentDaily.length; i++) {
                for (int j = 0; j < habitTimeSpentDaily[i].length; j++) {
                    for (int k = 0; k < habitTimeSpentDaily[i][j].length; k++){
                        newHabitTimeSpentDaily[i][j][k] = habitTimeSpentDaily[i][j][k];
                    }
                }
            }
        }
    }
    
        
    public static void popHabit(int habitPosition, String[] dailyHabits, int[] dailyHabitMinutes,
                                 String[] newDailyHabits, int[] newDailyHabitMinutes) {
        
        if (habitPosition >= 0 && dailyHabits != null && dailyHabitMinutes != null
            && newDailyHabits != null && newDailyHabitMinutes!= null) {
            
            int i = 0;
            int j = 0;
            
            while (i < dailyHabits.length) {
                if (habitPosition == i) {
                    i++;
                }
                else {
                    newDailyHabits[j] = dailyHabits[i];
                    newDailyHabitMinutes[j] = dailyHabitMinutes[i];
                    i++;
                    j++;  
                }
            }
        }
    }
            
    public static void popHabitInPerformance(int habitPosition, String[] dailyHabits, int [][][] habitTimeSpentDaily,
                                              int[][][] newHabitTimeSpentDaily) {
        
        if (habitPosition > 0 && dailyHabits != null && habitTimeSpentDaily != null && newHabitTimeSpentDaily != null) {
            
            int z = 0;
            int k = 0;
            
            for (int i = 0; i < habitTimeSpentDaily.length; i++) {
                for (int j = 0; j < habitTimeSpentDaily[i].length; j++) {
                    while (z < habitTimeSpentDaily[i][j].length) {
                        if (habitPosition == z) {
                            z++;
                        }
                        else {
                            newHabitTimeSpentDaily[i][j][k] = habitTimeSpentDaily[i][j][z];
                            k++;
                            z++;
                        }
                    }
                    z = 0;
                    k = 0;
                }
            }
        }
    }
            
            
    public static int[] getDate(){
        String stringDate = "";
        int[] date;
        date = new int[2];
        Habits.HabitsValidation.initializeVector(date);
        LocalDateTime localTime = LocalDateTime.now();
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd-MM");
        
        stringDate = localTime.format(formater);
        Scanner scanner = new Scanner(stringDate);
        scanner.useDelimiter("-");
        date[0] = scanner.nextInt();
        date[1] = scanner.nextInt();
        
        return date;
    }
}
