package Helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HabitsFileReader {
 public static void getDailyHabits(String route, String[] dailyHabits, int[] dailyHabitMinutes) {
        
        if (route != null && dailyHabits != null && dailyHabitMinutes != null
            && dailyHabits.length == dailyHabitMinutes.length) {
            
            String line = ""; 
            int i = 0;
            try (Scanner fileReader = new Scanner(new File(route))){
                while (fileReader.hasNextLine() && i < dailyHabits.length ) {
                    
                    line = fileReader.nextLine();
                    Scanner lineReader = new Scanner(line);
                    lineReader.useDelimiter("#");
                    
                    while(lineReader.hasNext()){
                        dailyHabits[i] = lineReader.next();
                        dailyHabitMinutes[i] = lineReader.nextInt();
                    }
                    i++;
                }
            }
            catch (FileNotFoundException e) {
                System.out.println("¡Lo sentimos!, ha ocurrido un error");
                System.out.println("Error: [No se ha encontrado el archivo]");
            }
        }
    }
    
    
    public static void getHabitTimeSpentDaily(String route, int[][][] habitTimeSpentDaily){
        
        if (route != null && habitTimeSpentDaily != null) {
            int i = 0;
            
            try(Scanner fileReader = new Scanner(new File(route))){
                while (fileReader.hasNextLine()) {
                    for (int j = 0; j < habitTimeSpentDaily[i].length; j++) {
                        for (int k = 0; k < habitTimeSpentDaily[i][j].length; k++) {
                            habitTimeSpentDaily[i][j][k] = fileReader.nextInt();
                        }
                    }
                    fileReader.nextLine();
                    if (!fileReader.hasNextInt()) return;
                    i++;
                }
            } catch (FileNotFoundException ex) {
                System.out.println("¡Lo sentimos!, ha ocurrido un error");
                System.out.println("Error: [No se ha encontrado el archivo]");
            }
        }
    }    
}
