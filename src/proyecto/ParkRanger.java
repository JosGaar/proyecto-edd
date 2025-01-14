package proyecto;

import dataStructures.LinkedList;
import java.time.LocalDate;

public class ParkRanger extends Person {

    LocalDate dateOfHire;
    RangerStatus status;
    Visibility visibility;
    LinkedList<Incident> incidents;

    public ParkRanger(LocalDate dateOfHire, String identification, String names, String lastNames, RangerStatus status, Visibility visibility)
    {
        super(identification, names, lastNames);
        this.dateOfHire = dateOfHire;
        this.status = status;
        this.incidents = new LinkedList<>();
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

    public RangerStatus getStatus()
    {
        return status;
    }

    public void setStatus(RangerStatus status)
    {
        this.status = status;
    }

    public String getIdentification()
    {
        return identification;
    }

    public void setIdentification(String identification)
    {
        this.identification = identification;
    }

    public LocalDate getDateOfHire()
    {
        return dateOfHire;
    }

    public void setDateOfHire(LocalDate dateOfHire)
    {
        this.dateOfHire = dateOfHire;
    }

    public String getNames()
    {
        return names;
    }

    public void setNames(String names)
    {
        this.names = names;
    }

    public String getLastNames()
    {
        return lastNames;
    }

    public void setLastNames(String lastNames)
    {
        this.lastNames = lastNames;
    }

    @Override

    public String toString()
    {
        String fatherToString = super.toString();

        return fatherToString + "\nFecha de contratación:" + this.dateOfHire;

    }

}
