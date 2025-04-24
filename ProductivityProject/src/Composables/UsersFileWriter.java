package Composables;

import java.io.IOException;
import java.nio.file.Paths;

import Process.Encrypt;
import Repositories.FileManager;

public class UsersFileWriter {
	public static void saveUser(String username, String password, FileManager fileManager) {
		if (username != null && password != null) {
			try {
				String text = String.format("%-20s  %-20s\n", username, password);
				if (!fileManager.fileExist("users.txt"))
					fileManager.createFile("users.txt");
				fileManager.writeTextInFile(text, "users.txt", true);
				System.out.println("Usuario Creado!");
			} catch (IOException e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}

	public static void createSessionFile(String username, FileManager fileManager) {
		if (username != null) {
			try {
				if (!fileManager.fileExist("session.txt"))
					fileManager.createFile("session.txt");
				fileManager.writeTextInFile(Encrypt.encrypt(username), "session.txt", false);
			} catch (IOException e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}

	public static void logout() {
		try {
			FileManager fileManager = new FileManager(
					Paths.get("").toAbsolutePath().toString() + "/src/Storage/UsersFiles/");
			fileManager.emptyFile("session.txt");
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
