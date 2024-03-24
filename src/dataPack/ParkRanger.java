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
public class ParkRanger extends Person{
 
    
 LocalDate dateOfHire;   
 
 RangerStatus status;

    public ParkRanger(LocalDate dateOfHire, String identification, String names, String lastNames, RangerStatus status) {
        super(identification, names, lastNames);
        this.dateOfHire = dateOfHire;
        this.status = status;
    }

    public RangerStatus getStatus() {
        return status;
    }

    public void setStatus(RangerStatus status) {
        this.status = status;
    }
    
    public LocalDate getDateOfHire() {
        return dateOfHire;
    }

    public void setDateOfHire(LocalDate dateOfHire) {
        this.dateOfHire = dateOfHire;
    }
    
    @Override
    public  String toString(){
        String fatherToString= super.toString();
        return fatherToString+"\nFecha de contratación:"+this.dateOfHire;
    }
}
