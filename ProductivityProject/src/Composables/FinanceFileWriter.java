package Composables;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FinanceFileWriter {
    public static void fileCreate(String file) {
        try{
            String dfault= "0.00";
            String path=Paths.get(".").toRealPath().toString()+"/src/Storage/FinanceFiles/"+file;
            File newfile = new File(path);
            if(newfile.createNewFile()){
                FileWriter writer = new FileWriter(path);
                writer.write(dfault);
                writer.close();
            }
        } catch(IOException i){
            System.out.println("Error: "+i.getMessage());
        }
    }
    
    public static void fileWriter1(String file, double add,ArrayList<String> debts,int i){
        try{
            String path=Paths.get(".").toRealPath().toString()+"/src/Storage/FinanceFiles/"+file;
            File file1 = new File(path);
            file1.createNewFile();
            FileWriter writer = new FileWriter(file1,false);
            writer.write(Double.toString(add));
            writer.close();
            fileWriter1(file1, debts,i,0);
            debts.removeAll(debts);
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    public static void fileWriter1(File file, ArrayList<String> debts,int i,int j){
        try{
            if(j==i){
                return;
            }else{
            File file1 = file;
            file1.createNewFile();
            FileWriter writer2 = new FileWriter(file1,true);
            writer2.write("\n"+debts.get(j));
            writer2.close();
            fileWriter1(file,debts,i,j+1);
            }
            
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
}
