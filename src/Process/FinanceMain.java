package Process;
import Repositories.ArchiveUtil;
import Repositories.Finance;
import Validations.FinanceValidation;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

import Functionalities.FinanceFunctionalities;

public class FinanceMain {
    public static void main(String username, Scanner input, ArchiveUtil archiveUtil) throws IOException{
        Finance userMoney = new Finance();
		FinanceValidation.setFinanceRouter(archiveUtil);
        FinanceFunctionalities.menu(username,userMoney, input, archiveUtil);
        userMoney=null;
    } 
}
