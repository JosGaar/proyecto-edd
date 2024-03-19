/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataPack;

import java.time.LocalDate;

/**
 *
 * @author ASUS
 */
public class parkRanger extends Person{
 
    
 LocalDate dateOfHire;   

  public parkRanger(String identification, String names, String lastNames, LocalDate dateOfHire) {
        super(identification, names, lastNames);
        this.dateOfHire = dateOfHire;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }
 
    
}
