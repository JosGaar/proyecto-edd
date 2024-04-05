package entities;

import dataStructures.LinkedList;

public class Area {

    private String codeArea;
    private String name;
    private String description;
    private LinkedList<Incident> incidents;

    public Area(String codeArea, String name, String description)
    {
        this.codeArea = codeArea;
        this.name = name;
        this.description = description;
        this.incidents = new LinkedList<>();
    }

    public String getCodeArea()
    {
        return codeArea;
    }

    public void setCodeArea(String codeArea)
    {
        this.codeArea = codeArea;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public LinkedList<Incident> getIncidents()
    {
        return incidents;
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("\nC�digo del �rea: ").append(this.getCodeArea())
                .append("\nNombre del �rea: ").append(this.getName())
                .append("\nDescripci�n: ").append(this.getDescription());

        return sb.toString();
    }

}
