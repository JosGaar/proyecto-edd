/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataPack;

import java.time.LocalDate;
/**
 *
 * @author User
 */
public class Visit {
    private String codeVisit;
    private Visitor visitor;
    private LocalDate entryDate;
    private LocalDate exitDate;
    
    public Visit(String codeVisit, Visitor visitor, LocalDate entryDate) {
        this.codeVisit = codeVisit;
        this.visitor = visitor;
        this.entryDate = entryDate;
    }

    public String getCodeVisit() {
        return codeVisit;
    }

    public void setCodeVisit(String codeVisit) {
        this.codeVisit = codeVisit;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDate getExitDate() {
        return exitDate;
    }

    public void setExitDate(LocalDate exitDate) {
        this.exitDate = exitDate;
    }
    
    @Override
    public  String toString(){
        String fatherToString= super.toString();
        return "\nCódigo de la Visita: "+this.codeVisit
                +this.visitor.toString()
                +"\nFecha de Entrada: "+this.entryDate
                +"\nFecha de Salida: "+((this.exitDate != null)?this.exitDate:"(Aún en la reserva)");
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) 
            return true;
        
        if (obj instanceof Visit) {
            Visit v = (Visit) obj;
            return v.getCodeVisit().equals(this.getCodeVisit());
        }
       
       if (obj instanceof String) {
            String id = (String) obj;
            return id.equals(this.getCodeVisit());
        }
       
        return false;
    }
}
