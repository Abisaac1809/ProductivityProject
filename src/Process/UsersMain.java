package Process;

import java.util.Scanner;

import Functionalities.UsersFunctionalities;
import Repositories.ArchiveUtil;

public class UsersMain {
	public static String main(Scanner input, ArchiveUtil archiveUtil) {
		Validations.UsersValidations.setUsersRouter(archiveUtil);
		String username = Helpers.UsersFileReader.getSession(archiveUtil);
		if (input != null) {
			if (username == "") {
				int option = UsersFunctionalities.loginMenu(input);
				username = "";
				if (option == 1) {
					username = UsersFunctionalities.signin(input, archiveUtil);
				} else if (option == 2) {
					username = UsersFunctionalities.signup(input, archiveUtil);
				}
			}
		}
		return username;
	}
}
