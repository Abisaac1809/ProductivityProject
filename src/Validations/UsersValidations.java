package Validations;

import Repositories.ArchiveUtil;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Scanner;

public class UsersValidations {
	public static String username(boolean isLogin, Scanner input, ArchiveUtil archiveUtil) {
		String value = "";
		if (input != null) {
			while (true) {
				value = input.next();
				if (Helpers.UsersFileReader.userExists(value , archiveUtil) && !isLogin) {
					System.out.println("Error: [Nombre de usuario ya en uso]");
					System.out.print("- Ingrese otro nombre de usuario: ");
				} else if (!Helpers.UsersFileReader.userExists(value, archiveUtil) && isLogin) {
					System.out.println("Error: [Nombre de usuario no existe]");
					System.out.print("- Ingrese otro nombre de usuario: ");
				} else if (DataValidations.stringCharByChar(value, 0)) {
					return value;
				} else {
					System.out.println("Error: [Caracter invalido encontrado]");
					System.out.print("- Ingrese un nombre correcto: ");
				}
			}
		}
		return value;
	}

	public static void setUsersRouter(ArchiveUtil archiveUtil) {
		try {
			archiveUtil.setRouter(Paths.get("").toAbsolutePath().toString() + "/src/Storage/UsersFiles/");
		} catch (FileNotFoundException | IllegalArgumentException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
