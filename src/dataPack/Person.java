/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataPack;

import java.time.LocalDateTime;

/**
 *
 * @author ASUS
 */
public class Person {
    
    protected String identification;
    protected String names;
    protected String  lastNames;

    public Person(String identification, String names, String lastNames) {
        this.identification = identification;
        this.names = names;
        this.lastNames = lastNames;
    }
    
    public Incident reportIncident(String incidentCode, String description, LocalDateTime dateTimeReport, IncidentStatus incidentStatus, String annotations, Area area){
        return new Incident(incidentCode, description, dateTimeReport, this, incidentStatus, annotations, area);
    }

   public String fullname() {
        return "\nNombres: " + this.names
                + "\nApellidos: " + this.lastNames;
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
   
    @Override
    public String toString(){
    return "\nIdentificación: "+this.identification
            +fullname();
    }
}
