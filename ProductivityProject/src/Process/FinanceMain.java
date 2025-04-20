package Process;
import Repositories.Finance;
import java.io.IOException;

public class FinanceMain {
    public static void main(String username) throws IOException{
        Finance userMoney = new Finance();
        FinanceDevelopment.menu(username,userMoney);
        userMoney=null;
    } 
}
