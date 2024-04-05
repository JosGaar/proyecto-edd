package entities;

import utils.Visibility;
import java.time.LocalDateTime;
import java.util.Objects;

public class Incident {

    private String incidentCode;
    private String description;
    private LocalDateTime dateTimeReport;
    private LocalDateTime dateTimeAttention;
    private ParkRanger agentSupport;
    private IncidentStatus incidentStatus;
    private Visibility visibility;
    private String annotations;
    private Area area;
    private Person reportingPerson;

    public Incident(String incidentCode, Person reportingPerson, String description, LocalDateTime dateTimeReport, LocalDateTime dateTimeAttention,
            ParkRanger agentSupport, String annotations, Area area)
    {
        this.description = description;
        this.reportingPerson = reportingPerson;
        this.dateTimeReport = dateTimeReport;
        this.dateTimeAttention = dateTimeAttention;
        this.agentSupport = agentSupport;
        this.incidentStatus = IncidentStatus.PENDING;
        this.annotations = annotations;
        this.area = area;
        this.incidentCode = incidentCode;
        this.visibility = Visibility.VISIBLE;
    }

    public Person getReportingPerson()
    {
        return reportingPerson;
    }

    public void setReportingPerson(Person reportingPerson)
    {
        this.reportingPerson = reportingPerson;
    }

    public void setIncidentCode(String incidentCode)
    {
        this.incidentCode = incidentCode;
    }

    public String getIncidentCode()
    {
        return incidentCode;
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
    public int hashCode()
    {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Incident other = (Incident) obj;
        return Objects.equals(this.incidentCode, other.incidentCode);
    }

    @Override
    public String toString()
    {
        String fullName = this.agentSupport == null ? "No se ha registrado un agente por el momento" : this.agentSupport.fullname();
        String dateTimeAttention = this.dateTimeAttention == null ? "No ha sido atendido." : this.dateTimeAttention.toString();

        return "\nCÛdigo de incidencia: " + this.incidentCode
                + "\nFecha/Hora de incidente: " + this.dateTimeReport
                + "\nFecha/Hora de atenci√≥n: " + dateTimeAttention
                + "\nAgente de Soporte: " + fullName
                + "\nEstado de Incidencia: " + this.incidentStatus
                + "\nAnotaciones: " + this.annotations
                + "\nArea: " + this.area.getName();
    }

}
