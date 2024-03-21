/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataPack;

/**
 *
 * @author ASUS
 */
public class Person {
    
String identification;

String names;
        
String  lastNames;

    public Person(String identification, String names, String lastNames) {
        this.identification = identification;
        this.names = names;
        this.lastNames = lastNames;
    }

   public String fullname() {

        return "\nNombres: " + this.names
                + "\nApellidos: " + this.lastNames;

    }    
    
@Override

public String toString(){

return fullname()+
       "\nIdentificación: "+this.identification;
       
}

    
}
