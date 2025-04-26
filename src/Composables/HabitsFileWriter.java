package Composables;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class HabitsFileWriter {
    public static void saveHabits(String route, String[] dailyHabits, int[] dailyHabitMinutes) throws IOException {
        
        if (route != null && dailyHabits != null && dailyHabitMinutes != null) {
            String line = "";
            try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(route))) {
                for (int i = 0; i < dailyHabits.length; i++) {
                    line = String.format("%s#%d", dailyHabits[i], dailyHabitMinutes[i]);
                    fileWriter.write(line);
                    if (i != (dailyHabits.length - 1)) {
                        fileWriter.newLine();
                    }
                }
            }
            catch(FileNotFoundException e) {  
                System.out.println("¡Lo sentimos!, ha ocurrido un error");
                System.out.println("Error: [No se ha encontrado el archivo]");
            }
        }  
    }
    
    public static void savePerformance(String route, int[][][] habitTimeSpentDaily) throws IOException {
        
        if (route != null && habitTimeSpentDaily != null) {
            
            try(BufferedWriter fileWriter = new BufferedWriter(new FileWriter(route))) {
                for (int i = 0; i < habitTimeSpentDaily.length; i++) {
                    for (int j = 0; j < habitTimeSpentDaily[i].length; j++) {
                        for (int k = 0; k < habitTimeSpentDaily[i][j].length; k++) {
                            fileWriter.write(String.format("%s ", habitTimeSpentDaily[i][j][k]));
                        }
                        fileWriter.newLine();
                    }
                    fileWriter.newLine();
                }
            }
            catch(FileNotFoundException e) {
                System.out.println("¡Lo sentimos!, ha ocurrido un error");
                System.out.println("Error: [No se ha encontrado el archivo]");    
            }
        }
    }    
}
