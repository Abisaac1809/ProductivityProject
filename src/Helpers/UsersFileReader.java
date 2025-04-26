package Helpers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import Process.Encrypt;
import Repositories.FileManager;

public class UsersFileReader {
	public static boolean userExists(String username) {
		if (username != null) {
			try {
				String path = Paths.get("").toRealPath().toString() + "/src/Storage/UsersFiles/users.txt";
				System.out.println(path);
				File file = new File(path);
				file.createNewFile();
				Scanner fileScanner = new Scanner(file);
				while (fileScanner.hasNext()) {
					if (fileScanner.next().equals(username)) {
						fileScanner.close();
						return true;
					}
					fileScanner.nextLine();
				}
				fileScanner.close();
			} catch (IOException e) {
				System.out.println("Error eroieorg: " + e.getMessage());
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
			FileManager fileManager = new FileManager(
					Paths.get("").toAbsolutePath().toString() + "/src/Storage/UsersFiles/");
			Scanner file = fileManager.getFile("session.txt");
			String username = "";
			if (file.hasNext()) {
				String userSession = file.next();
				username = getUserEncrypted(userSession);
			}
			file.close();
			return username;
		} catch (IOException e) {
			return "";
		}
	}
}
