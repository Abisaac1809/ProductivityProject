package Process;
import Repositories.Finance;
import java.io.IOException;
import java.util.Scanner;

public class FinanceMain {
    public static void main(String username, Scanner input) throws IOException{
        Finance userMoney = new Finance();
        FinanceDevelopment.menu(username,userMoney, input);
        userMoney=null;
    } 
}
