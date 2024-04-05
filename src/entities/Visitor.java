package entities;

import dataStructures.LinkedList;

public class Visitor extends Person {

    private String address;
    private String phoneNumber;
    private LinkedList<Visit> visits;
    private VisitStatus status;

    public Visitor(String address, String phoneNumber, String identification, String names, String lastNames)
    {
        super(identification, names, lastNames);
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.visits = new LinkedList<>();
        this.status = VisitStatus.INACTIVE;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public VisitStatus getStatus()
    {
        return status;
    }

    public void setStatus(VisitStatus status)
    {
        this.status = status;
    }

    public void insertVisit(Visit visit)
    {
        this.visits.addByLast(visit);
    }

    public void removeVisit(Visit visit)
    {
        this.visits.remove(visit);
    }

    @Override
    public String toString()
    {
        String fatherToString = super.toString();
        return fatherToString + "\nDirección registrada: " + this.address
                + "\nNúmero de Télefono: " + this.phoneNumber;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }

        if (obj instanceof Visitor)
        {
            Visitor v = (Visitor) obj;
            return v.getIdentification().equals(this.getIdentification());
        }

        if (obj instanceof String)
        {
            String id = (String) obj;
            return id.equals(this.getIdentification());
        }

        return false;
    }
}
