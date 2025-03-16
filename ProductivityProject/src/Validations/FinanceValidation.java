package Validations;
import Helpers.FinanceFileReader;
import java.util.Scanner;
import java.util.InputMismatchException;

public class FinanceValidation {
    public static double valEntry(double balance,String text){
        double amount;
        Scanner enter =new Scanner(System.in);
        while(true){
            try{
                System.out.println(text);
                amount=enter.nextDouble();
                amount=Math.abs(amount);
                if(amount>balance){
                    System.out.println("Error: No tienes suficiente dinero si quieres retirar ese monto");
                }
                else{
                    return amount;
                }
            }
            catch(InputMismatchException ime){
                System.out.println("Error: Por favor, ingrese un numero");
                enter.nextLine();
            }
        }
    }
    
    public static double valEntry(String text){
        double amount;
        Scanner enter=new Scanner(System.in);
        while(true){
            try{
                System.out.println(text);
                amount=enter.nextDouble();
                amount=Math.abs(amount);
                return amount;
            }
            catch(InputMismatchException ime){
                System.out.print("Error: Ingrese solo numeros\n");
                enter.nextLine();
            }
        }
    }
    
    public static String valConcept(String text,String file,boolean flag){
        String concept;
        Scanner enter=new Scanner(System.in);
        while(true){
            try{
                System.out.println(text);
                concept=enter.nextLine().replace(" ", "_");
                concept=concept.toLowerCase();
                if(flag==true){
                    if(FinanceFileReader.searchInFile(concept,file)){
                        if(concept.equals("")){
                            concept="deuda";
                        }
                        return concept;
                    }else{
                        System.out.println("Error: Concepto ya usado");
                    }
                }
                if(flag==false){
                    if(!FinanceFileReader.searchInFile(concept,file)){
                        return concept;
                    }else{
                        System.out.println("Error: Concepto no encontrado");
                    }
                }
            }
            catch(InputMismatchException ime){
                System.out.print("Error: "+ime.getMessage());
                enter.nextLine();
            }
        }
    }
    
    public static double options(){
        Scanner input = new Scanner(System.in);
        double option=0;
        while(true){
            try{
                option=input.nextDouble();
                if(option==1 || option == 2 || option ==3 || option == 4 || option == 5 || option == 6){
                    return option;
                }else{
                    System.out.println("Error: Opcion no valida");
                    System.out.print("-Ingrese su opcion:");
                }
            } catch(InputMismatchException e){
                System.out.println("Error: Por favor ingrese un caracter válido");
                input.nextLine();
            }
        }
    }
}
