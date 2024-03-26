package dataPack;

public class Person {

    String identification;

    String names;

    String lastNames;

    public Person(String identification, String names, String lastNames)
    {
        this.identification = identification;
        this.names = names;
        this.lastNames = lastNames;
    }

    @Override
    public String toString()
    {
        return fullname()
                + "\nIdentificación: " + this.identification;
    }

    public String fullname()
    {
        return "Nombres: " + this.names
                + "\nApellidos: " + this.lastNames;
    }

    public String getIdentification()
    {
        return identification;
    }

    public void setIdentification(String identification)
    {
        this.identification = identification;
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

}
