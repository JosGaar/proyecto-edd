/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataPack;

import dataStructures.LinkedList;
import java.time.LocalDate;

/**
 *
 * @author ASUS
 */
public class Visitor extends Person{
    private String address;
    private String phoneNumber;
    private LinkedList<Visit> visits;
    private VisitStatus status;

    public Visitor(String address, String phoneNumber, VisitStatus status, String identification, String names, String lastNames) {
        super(identification, names, lastNames);
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.visits = new LinkedList<>();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public VisitStatus getStatus() {
        return status;
    }

    public void setStatus(VisitStatus status) {
        this.status = status;
    }

    public void insertVisits(Visit visit) {
        this.visits.addByLast(visit);
    }
    
    public void removeVisits(Visit visit) {
        this.visits.remove(visit);
    }
    
    @Override
    public  String toString(){
        String fatherToString= super.toString();
        return fatherToString+"\nDirecci�n registrada: "+this.address
                +"\nN�mero de T�lefono: "+this.phoneNumber;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) 
            return true;
        
        if (obj instanceof Visitor) {
            Visitor v = (Visitor) obj;
            return v.identification.equals(this.identification);
        }
       
       if (obj instanceof String) {
            String id = (String) obj;
            return id.equals(this.identification);
        }
       
        return false;
    }
}
