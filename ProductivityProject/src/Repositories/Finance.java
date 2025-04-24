package Repositories;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Finance {
    
    private List<String> title;
    private List<Double> debt;
    private Double money;
    
    public Finance(){
        title = new List<>();
        debt = new List<>();
    }
    
    //getters
    public Double getMoney(){
        return money;
    }
        
    public String getTitle(int i){
        return title.get(i);
    }
        
    public Double getDebt(int i){
        return debt.get(i);
    }
    
    //setters
    public void setTitle(String text){
        String name = valConcept(text,true);
        title.add(name);
    }

    public void setDebt(String text){
        Double amount=valEntry(text);
        debt.add(amount);
    }

    public void setMoney(Double munny){
        money=munny;
    }
    
    //methods
    public void addMoney(String text){
        double munny=valEntry(text);
        money+=munny;
    }
    
    public void subtractMoney(String text){
        double munny=valEntry(text);
        money-=munny;
    }
    
    public int debtLength(){
        return debt.size();
    }
    
    public int titleLength(){
        return title.size();
    }
    
    public void deleteDebt(int i){
        debt.remove(i);
        title.remove(i);
    }
    
    public void loadDebt(Double num){
        debt.add(num);
    }
    
    public void loadTitle(String text){
        title.add(text);
    }
    
    //Utilitarias
    
    public double valEntry(String text){
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
            }
        }
    }
    
    public String valConcept(String text, boolean flag){
        String concept;
        Scanner enter=new Scanner(System.in);
        boolean check=true;
        while(true){
            try{
                System.out.println(text);
                concept=enter.nextLine().replace(" ", "_");
                concept=concept.toLowerCase();
                if(flag==true){
                    for(int i=0;i<titleLength();i++){
                        if(concept.equals(getTitle(i))){
                            check=false;
                        }
                    }
                    if(check){
                        return concept;
                    }else{
                        System.out.println("Error: Concepto ya usado");
                        check=true;
                    }
                }
                if(flag==false){
                    for(int i=0;i<debtLength();i++){
                        if(concept.equals(getTitle(i))){
                            deleteDebt(i);
                            check=false;
                            return "hecho";

                        }
                    }
                    if(check){
                    }else{
                        System.out.println("Error: Concepto no encontrado");
                    }
                }
            }
            catch(InputMismatchException ime){
                System.out.print("Error: "+ime.getMessage());
            }
        }
    }
    
    public double debtSearching(String text) {
        Double c = this.getMoney();
        Double d;
            for(int i=0;i<this.debtLength()-1;i++){
                d = this.getDebt(i);
                if(this.getTitle(i).equals(text)){
                    if(d<=c){
                        System.out.println(this.getDebt(i));
                        this.deleteDebt(i);
                        return c-d;
                    }else{
                        System.out.println("Error: No hay suficiente saldo para pagar esa deuda");
                        return c;
                    }
                }
            }
            System.out.println("Error: Deuda no encontrada");
        return c;
    }
    
}
