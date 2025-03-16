package Composables;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class UsersFileWriter {
    public static void saveUser(String username, String password) {
        if (username != null && password != null) {
            try {
                String path = Paths.get(".").toRealPath().toString() + "/src/Storage/UsersFiles/users.txt";
                File file = new File(path);
                file.createNewFile();
                FileWriter writer = new FileWriter(path, true);
                writer.write(String.format("%-20s  %-20s\n", username, password));
                writer.close();
                System.out.println("Usuario Creado!");
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
