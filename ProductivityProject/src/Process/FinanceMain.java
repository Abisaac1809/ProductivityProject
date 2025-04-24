package Process;
import Repositories.Finance;
import java.io.IOException;
import java.util.Scanner;

public class FinanceMain {
    public static void main(String username) throws IOException{
        Finance userMoney = new Finance();
        Scanner auxi= new Scanner(System.in);
        FinanceDevelopment.menu(username,userMoney, auxi);
        userMoney=null;
        auxi.close();
    } 
}
