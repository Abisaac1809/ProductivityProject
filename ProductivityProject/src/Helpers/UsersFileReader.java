package Helpers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import Process.Encrypt;

public class UsersFileReader {
   public static boolean userExists(String username) {
        if (username != null) {
            try {
                String path = Paths.get("").toRealPath().toString() + "/src/Storage/UsersFiles/users.txt";
                File file = new File(path);
                file.createNewFile();
                Scanner fileScanner = new Scanner(file);
                while (fileScanner.hasNextLine()) {
                    if (fileScanner.next().equals(username)) {
                        fileScanner.close();
                        return true;
                    }
                    fileScanner.nextLine();
                }
                fileScanner.close();
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return false;
    }
    public static void checkPassword(String username, Scanner input) {
        if (username != null && input != null) {
            while (true) {
                try {
                    String path = Paths.get("").toRealPath().toString() + "/src/Storage/UsersFiles/users.txt";
                    Scanner file = new Scanner(new File(path));
                    String p = "";
                    System.out.print("- Contraseña: ");
                    String password = Encrypt.encrypt(input.next());
                    while (file.hasNextLine()) {
                        if (file.next().equals(username)) {
                            p = file.next();
                        }
                        file.nextLine();
                    }
                    if (p.equals(password)) {
                        file.close();
                        return;
                    } else {
                        System.out.println("Error: [Contraseña inválida]");
                    }
                    file.close();
                } catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
    }

    public static String getUserEncrypted(String usernameEncrypted) {
        String username = "";
        try {
            String path = Paths.get("").toRealPath().toString() + "/src/Storage/UsersFiles/users.txt";
            Scanner file = new Scanner(new File(path));
            while (file.hasNextLine()) {
                String user = file.next();
                if (Encrypt.encrypt(user).equals(usernameEncrypted)) {
                    file.close();
                    return user;
                }
            }
            file.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return username;
    }    
    
    public static String getSession() {
        try {
            String path = Paths.get("").toRealPath().toString() + "/src/Storage/UsersFiles/session.txt";
            Scanner file = new Scanner(new File(path));
            String username = "";
            if (file.hasNextLine()) {
                String userSession = file.next();
                username = getUserEncrypted(userSession);
            } else {
                System.out.println("ERROR: [Sesion del usuario invalida!]");
            }
            file.close();
            return username;
        } catch (IOException e) {
            return "";
        }
    }
}
