package Helpers;

public class HabitsDisplay {
    public static void showHabits(String[] dailyHabits, int[] dailyHabitMinutes) {
        
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
    
    public static void showMonthlyHabitTracker(String month, String[] dailyHabits, int [][]completedDays) {
        
        if (month != null && dailyHabits != null && completedDays != null) {
            String mark;
        
            System.out.printf("\nRENDIMIENTO DE %s\n", month.toUpperCase());
            printLine(43 + (3 * completedDays.length));
            System.out.printf("%-40s|  ", "HÁBITO");
        
            for (int i = 0; i < completedDays.length; i++){
                System.out.printf("%-3d", (i+1));
            }
            System.out.println("|");
        
            printLine(43 + (3 * completedDays.length));
        
            for (int i = 0; i < dailyHabits.length; i++) {
                System.out.printf("%-40s|  ", dailyHabits[i]);
                for (int j = 0; j < completedDays.length; j++) {
                    mark = (completedDays[j][i] == 1) ? "X" : "-"; 
                    System.out.printf("%-3s", mark);
                }
                System.out.println("|");
            }
            printLine(43 + (3 * completedDays.length));    
        }   
    }
    
    private static void printLine (int numberOfCharacters) {
        
        if (numberOfCharacters > 0) {
            for (int i = 0; i < numberOfCharacters; i++) {
                System.out.print("-");
            }
            System.out.println("|");
        }
    }    
}
