package proyecto;

import java.time.LocalDateTime;
import java.util.Objects;

public class Visit {

    private String codeVisit;
    private Visitor visitor;
    private Visibility visibility;
    private LocalDateTime entryDate;
    private LocalDateTime exitDate;

    public Visit(String codeVisit, Visitor visitor, Visibility visibility, LocalDateTime entryDate)
    {
        this.codeVisit = codeVisit;
        this.visitor = visitor;
        this.visibility = visibility;
        this.entryDate = entryDate;
    }

    public Visibility getVisibility()
    {
        return visibility;
    }

    public void setVisibility(Visibility visibility)
    {
        this.visibility = visibility;
    }

    public String getCodeVisit()
    {
        return codeVisit;
    }

    public void setCodeVisit(String codeVisit)
    {
        this.codeVisit = codeVisit;
    }

    public Visitor getVisitor()
    {
        return visitor;
    }

    public void setVisitor(Visitor visitor)
    {
        this.visitor = visitor;
    }

    public LocalDateTime getEntryDate()
    {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate)
    {
        this.entryDate = entryDate;
    }

    public LocalDateTime getExitDate()
    {
        return exitDate;
    }

    public void setExitDate(LocalDateTime exitDate)
    {
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
