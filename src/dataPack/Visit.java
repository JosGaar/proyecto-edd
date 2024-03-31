package dataPack;

import java.time.LocalDateTime;

public class Visit {

    private String codeVisit;
    private Person visitor;
    private Visibility visibility;
    private VisitStatus status;
    private LocalDateTime entryDate;
    private LocalDateTime exitDate;

    public Visit(String codeVisit, Person visitor, VisitStatus status, LocalDateTime entryDate, Visibility visibility)
    {
        this.codeVisit = codeVisit;
        this.visitor = visitor;
        this.status = status;
        this.entryDate = entryDate;
        this.visibility = visibility;
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

    public Person getVisitor()
    {
        return visitor;
    }

    public void setVisitor(Person visitor)
    {
        this.visitor = visitor;
    }

    public VisitStatus getStatus()
    {
        return status;
    }

    public void setStatus(VisitStatus status)
    {
        this.status = status;
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
    public String toString()
    {

        String exit = this.exitDate == null ? "No ha salido aun." : this.exitDate.toString();
        
        return "Codigo de visita = " + codeVisit + "\n"
                + "Visitante = " + visitor.names + ", " + visitor.lastNames + "\n"
                + "Estado de la visita = " + status + "\n"
                + "Fecha de entrada = " + entryDate + "\n"
                + "Fecha de salida = " + exit;
    }

}
