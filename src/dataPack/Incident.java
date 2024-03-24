/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataPack;

import java.time.LocalDateTime;

public class Incident {
    
    public String incidentCode;
    public String description;
    public LocalDateTime dateTimeReport;
    public LocalDateTime dateTimeAttention;
    public Person reporter;
    public ParkRanger agentSupport;
    public IncidentStatus incidentStatus;
    public String annotations;
    public Area area;

    public Incident(String incidentCode, String description, LocalDateTime dateTimeReport, LocalDateTime dateTimeAttention, Person reporter, ParkRanger agentSupport, IncidentStatus incidentStatus, String annotations, Area area) {
        this.description = description;
        this.dateTimeReport = dateTimeReport;
        this.dateTimeAttention = dateTimeAttention;
        this.reporter = reporter;
        this.agentSupport = agentSupport;
        this.incidentStatus = incidentStatus;
        this.annotations = annotations;
        this.area = area;
        this.incidentCode = incidentCode;
    }
    
    public Incident(String incidentCode, String description, LocalDateTime dateTimeReport, Person reporter, IncidentStatus incidentStatus, String annotations, Area area) {
        this.description = description;
        this.dateTimeReport = dateTimeReport;
        this.reporter = reporter;
        this.incidentStatus = incidentStatus;
        this.annotations = annotations;
        this.area = area;
        this.incidentCode = incidentCode;
    }
    
    public String getIncidentCode() {
        return incidentCode;
    }

    public void setIncidentCode(String incidentCode) {
        this.incidentCode = incidentCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTimeReport() {
        return dateTimeReport;
    }

    public void setDateTimeReport(LocalDateTime dateTimeReport) {
        this.dateTimeReport = dateTimeReport;
    }

    public LocalDateTime getDateTimeAttention() {
        return dateTimeAttention;
    }

    public void setDateTimeAttention(LocalDateTime dateTimeAttention) {
        this.dateTimeAttention = dateTimeAttention;
    }

    public ParkRanger getAgentSupport() {
        return agentSupport;
    }

    public void setAgentSupport(ParkRanger agentSupport) {
        this.agentSupport = agentSupport;
    }

    public IncidentStatus getIncidentStatus() {
        return incidentStatus;
    }

    public void setIncidentStatus(IncidentStatus incidentStatus) {
        this.incidentStatus = incidentStatus;
    }

    public String getAnnotations() {
        return annotations;
    }

    public void setAnnotations(String annotations) {
        this.annotations = annotations;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
 
    @Override
    public String toString() {
        return   "\nCódigo de incidencia: " + this.incidentCode
                + "\nFecha/Hora de incidente: " + this.dateTimeReport
                + "\nFecha/Hora de atención: " + ((this.dateTimeAttention != null)?this.dateTimeAttention:"Aún no atendido.")
                + "\nReportante: " + this.reporter.fullname()
                + "\nAgente de Soporte: " + ((this.agentSupport != null)?this.agentSupport.fullname():"Aún no atendido.")
                + "\nEstado de Incidencia: " + this.incidentStatus
                + "\nAnotaciones: " + this.annotations
                + "\nÁrea: " + this.area.getName();
    }
}
