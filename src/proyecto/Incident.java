package proyecto;

import java.time.LocalDateTime;

public class Incident {

    String incidentCode;

    public void setIncidentCode(String incidentCode)
    {
        this.incidentCode = incidentCode;
    }

    String description;
    LocalDateTime dateTimeReport;
    LocalDateTime dateTimeAttention;
    ParkRanger agentSupport;
    IncidentStatus incidentStatus;
    Visibility visibility;
    String annotations;
    Area area;

    public Incident(String incidentCode, String description, LocalDateTime dateTimeReport, LocalDateTime dateTimeAttention,
            ParkRanger agentSupport, IncidentStatus incidentStatus, String annotations, Area area, Visibility visibility)
    {
        this.description = description;
        this.dateTimeReport = dateTimeReport;
        this.dateTimeAttention = dateTimeAttention;
        this.agentSupport = agentSupport;
        this.incidentStatus = incidentStatus;
        this.annotations = annotations;
        this.area = area;
        this.incidentCode = incidentCode;
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

    public void setDateTimeReport(LocalDateTime dateTimeReport)
    {
        this.dateTimeReport = dateTimeReport;
    }

    public String getIncidentCode()
    {
        return incidentCode;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public LocalDateTime getDateTimeAttention()
    {
        return dateTimeAttention;
    }

    public LocalDateTime getDateTimeReport()
    {
        return dateTimeReport;
    }

    public void setDateTimeAttention(LocalDateTime dateTimeAttention)
    {
        this.dateTimeAttention = dateTimeAttention;
    }

    public ParkRanger getAgentSupport()
    {
        return agentSupport;
    }

    public void setAgentSupport(ParkRanger agentSupport)
    {
        this.agentSupport = agentSupport;
    }

    public IncidentStatus getIncidentStatus()
    {
        return incidentStatus;
    }

    public void setIncidentStatus(IncidentStatus incidentStatus)
    {
        this.incidentStatus = incidentStatus;
    }

    public String getAnnotations()
    {
        return annotations;
    }

    public void setAnnotations(String annotations)
    {
        this.annotations = annotations;
    }

    public Area getArea()
    {
        return area;
    }

    public void setArea(Area area)
    {
        this.area = area;
    }

    @Override
    public String toString()
    {
        String fullName = this.agentSupport == null ? "No se ha registrado un agente por el momento" : this.agentSupport.fullname();
        String dateTimeAttention = this.dateTimeAttention == null ? "No ha sido atendido." : this.dateTimeAttention.toString();

        return "\nC�digo de incidencia: " + this.incidentCode
                + "\nFecha/Hora de incidente: " + this.dateTimeReport
                + "\nFecha/Hora de atención: " + dateTimeAttention
                + "\nAgente de Soporte: " + fullName
                + "\nEstado de Incidencia: " + this.incidentStatus
                + "\nAnotaciones: " + this.annotations
                + "\nArea: " + this.area.getName();

    }

}
