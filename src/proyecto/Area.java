package proyecto;

public class Area {

    String codeArea;
    String name;
    String description;
    LinkedList<Incident> incidents;

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

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("\nCódigo del área: ").append(this.getCodeArea())
                .append("\nNombre del área: ").append(this.getName())
                .append("\nDescripción: ").append(this.getDescription());
        
        /*      .append("\nIncidentes: ");

        Esto permite añadir los incidentes al area.
        
        Node current = incidents.firstElement;

        if (current == null) {
            sb.append("No se han encontrados incidentes para esta area.");
        }

        while (current != null) {
            sb.append("\n").append(current.value);
            current = current.next;
        } */

        return sb.toString();
    }

}
