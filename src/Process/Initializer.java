package Process;

public class Initializer {

    public static void initializeVector(int[] vector) {
        if (vector != null) {
            for (int i = 0; i < vector.length; i++) {
                vector[i] = 0;
            }    
        }
    }
    
    public static void initializeVector(String[] vector) {
        if (vector != null) {
            for (int i = 0; i < vector.length; i++) {
                vector[i] = "e";
            }
        }
    }
    
    public static void initializeArray(int[][][] array) {
        
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    for (int k = 0; k < array[i][j].length; k++) {
                        array[i][j][k] = 0;
                    }
                }
            }
        }
    }
    
    public static void initializeArray(int[][] array) {
        
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    array[i][j] = 0;
                }
            }
        }
    }    
}
