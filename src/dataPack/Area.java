package dataPack;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ASUS
 */
public class Area {

    String codeArea;

    String name;

    String description;

    StateArea stateArea;

    LinkedList<Incident> incidents;

    public Area(String codeArea, String name, String description, StateArea stateArea)
    {
        this.codeArea = codeArea;
        this.name = name;
        this.description = description;
        this.stateArea = stateArea;
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

    public StateArea getStateArea()
    {
        return stateArea;
    }

    public void setStateArea(StateArea stateArea)
    {
        this.stateArea = stateArea;
    }

    @Override

    public String toString()
    {

        return "\nCódigo del área: " + this.getCodeArea()
                + "\nNombre del área: " + this.getName()
                + "\nDescripción: " + this.description
                + "\nEstado: " + this.stateArea;
    }

}
