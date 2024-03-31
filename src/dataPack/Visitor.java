package dataPack;

public class Visitor extends Person {

    private String address;
    private String phoneNumber;
    private LinkedList<Visit> visits;
    // private VisitStatus status;

    public Visitor(String address, String phoneNumber, String identification, String names, String lastNames)
    {
        super(identification, names, lastNames);
        this.address = address;
        this.phoneNumber = phoneNumber;
        // this.status = status;
        this.visits = new LinkedList<>();
    }

    public LinkedList<Visit> getVisits()
    {
        return visits;
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

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        for (Visit v : visits) {
            if (v.getStatus() == VisitStatus.Active) {
                sb.append("Codigo de visita: ").append(v.getCodeVisit()).append(", fecha: ").append(v.getEntryDate());
            }
        }

        String visitsInfo = sb.length() > 0 ? "\n " + sb.toString() + "\n" : "";
        String fatherToString = super.toString();
        return fatherToString + "\nDirección: " + this.address + "\n"
                + "Número de Télefono: " + this.phoneNumber
                + visitsInfo;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Visitor) {
            Visitor v = (Visitor) obj;
            return v.identification.equals(this.identification);
        }
        return true;
    }
}
