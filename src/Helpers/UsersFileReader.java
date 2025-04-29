package Helpers;

import java.util.Scanner;

import Repositories.ArchiveUtil;
import Composables.FileManager;
import Functionalities.UsersFunctionalities;

public class UsersFileReader {
	public static boolean userExists(String username, ArchiveUtil archiveUtil) {
		if (username != null) {
			try {
				String path = archiveUtil.getRouter();
				FileManager.createFileIfNotExists(path + "users.txt");
				Scanner fileScanner = archiveUtil.getArchive("users.txt");
				while (fileScanner.hasNextLine()) {
					if (fileScanner.next().equals(username)) {
						fileScanner.close();
						return true;
					}
					fileScanner.nextLine();
				}
				fileScanner.close();
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
		return false;
	}
public static void checkPassword(String username, Scanner input, ArchiveUtil archiveUtil) {
		if (username != null && input != null) {
			while (true) {
				try {
					Scanner file = archiveUtil.getArchive("users.txt");
					String p = "";
					System.out.print("- Contraseña: ");
					String password = UsersFunctionalities.encrypt(input.next());
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
				} catch (Exception e) {
					System.out.println("Error: " + e.getMessage());
				}
			}
		}
	}

	public static String getUserEncrypted(String usernameEncrypted, ArchiveUtil archiveUtil) {
		String username = "";
		try {
			String path = archiveUtil.getRouter();
			FileManager.createFileIfNotExists(path + "users.txt");
			Scanner file = archiveUtil.getArchive("users.txt");
			while (file.hasNext()) {
				String user = file.next();
				if (UsersFunctionalities.encrypt(user).equals(usernameEncrypted)) {
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

	public static String getSession(ArchiveUtil archiveUtil) {
		try {
			String path = archiveUtil.getRouter();
			FileManager.createFileIfNotExists(path + "session.txt");
			Scanner file = archiveUtil.getArchive("session.txt");
			String username = "";
			if (file.hasNext()) {
				String userSession = file.next();
				username = getUserEncrypted(userSession, archiveUtil);
			}
			file.close();
			return username;
		} catch (Exception e) {
			return "";
		}
	}
}
