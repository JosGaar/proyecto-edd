package entities;

import utils.Visibility;
import dataStructures.LinkedList;
import java.time.LocalDate;

public class ParkRanger extends Person {

    private LocalDate dateOfHire;
    private RangerStatus status;
    private Visibility visibility;
    private LinkedList<Incident> incidents;

    public ParkRanger(LocalDate dateOfHire, String identification, String names, String lastNames, RangerStatus status, Visibility visibility)
    {
        super(identification, names, lastNames);
        this.dateOfHire = dateOfHire;
        this.status = status;
        this.incidents = new LinkedList<>();
        this.visibility = visibility;
    }

    public LinkedList<Incident> getIncidents()
    {
        return incidents;
    }

    public void setIncidents(LinkedList<Incident> incidents)
    {
        this.incidents = incidents;
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

    public LocalDate getDateOfHire()
    {
        return dateOfHire;
    }

    public void setDateOfHire(LocalDate dateOfHire)
    {
        this.dateOfHire = dateOfHire;
    }

    @Override

    public String toString()
    {
        String fatherToString = super.toString();

        return fatherToString + "\nFecha de contratación:" + this.dateOfHire;

    }

}
