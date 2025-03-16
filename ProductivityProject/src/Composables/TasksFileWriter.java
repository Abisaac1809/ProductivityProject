package Composables;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class TasksFileWriter {
    public static void updateFile(ArrayList<String[]> tasks, Scanner input, String username) {
        try {
            String path = Paths.get(".").toRealPath().toString() + "/src/Storage/TasksFiles/" + username + ".tasks.txt";
            File file = new File(path);
            file.createNewFile();
            FileWriter writer = new FileWriter(path);
            for (int i = 0; i < tasks.size(); i++) {
                writer.write(String.format("%s,%s,%s\n",tasks.get(i)[0],tasks.get(i)[1], tasks.get(i)[2]));
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }    
}
