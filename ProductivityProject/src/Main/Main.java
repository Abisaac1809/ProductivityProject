package Main;

import java.io.IOException;
import java.util.Scanner;

// Abisaac Carmona - 32.218.469
// Adriana Ochoa - 31.711.538
// Jose Mendez - 31.390.867
// Gabriel Vieira - 32.297.573

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("---PRODUCTIVITY PROJECT---\n");
        Scanner input = new Scanner(System.in);
        MainDevelopment.mainMenu(input);
        System.out.println("\n---PROGRAMA CERRADO---");
        input.close();
    }
}
