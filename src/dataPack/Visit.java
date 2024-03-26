package dataPack;

import java.time.LocalDate;

public class Visit {
    private String codeVisit;
    private Person visitor;
    private VisitStatus status;
    
    private LocalDate entryDate;
    private LocalDate exitDate;
    
    public Visit(String codeVisit, Person visitor, VisitStatus status, LocalDate entryDate) {
        this.codeVisit = codeVisit;
        this.visitor = visitor;
        this.status = status;
        this.entryDate = entryDate;
    }

    public String getCodeVisit() {
        return codeVisit;
    }

    public void setCodeVisit(String codeVisit) {
        this.codeVisit = codeVisit;
    }

    public Person getVisitor() {
        return visitor;
    }

    public void setVisitor(Person visitor) {
        this.visitor = visitor;
    }

    public VisitStatus getStatus() {
        return status;
    }

    public void setStatus(VisitStatus status) {
        this.status = status;
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
}
