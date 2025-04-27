package Process;
import Repositories.ArchiveUtil;
import Repositories.Finance;
import Validations.FinanceValidation;

import java.io.IOException;
import java.util.Scanner;

public class FinanceMain {
    public static void main(String username, Scanner input, ArchiveUtil archiveUtil) throws IOException{
        Finance userMoney = new Finance();
		FinanceValidation.setFinanceRouter(archiveUtil);
        FinanceDevelopment.menu(username,userMoney, input, archiveUtil);
        userMoney=null;
    } 
}
