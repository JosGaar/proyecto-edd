/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataPack;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author ASUS
 */
public class NatureReserveManager {

    LinkedList<Area> areas;

    LinkedList<ParkRanger> parkRangers;

    LinkedList<Incident> incidents;

    public NatureReserveManager() {

        this.areas = new LinkedList<>();
        this.parkRangers = new LinkedList<>();
        this.incidents = new LinkedList<>();
    }

    public boolean addParkRanger(ParkRanger newParkRanger) {

        Criteria< ParkRanger> criteriaByCedula = parkRanger -> parkRanger.getIdentification().equals(newParkRanger.getIdentification());

        ParkRanger auxParkRanger = parkRangers.getElement(criteriaByCedula);

        if (auxParkRanger == null) {

            return parkRangers.insertElement(newParkRanger);

        }
        auxParkRanger = null; // Limpiar el objeto auxParkRanger
        return false;

    }

    public boolean removeParkRanger(String identification) {

        Criteria< ParkRanger> criteriaByIdentification = parkRanger -> parkRanger.getIdentification().equals(identification);

        ParkRanger auxParkRanger = parkRangers.getElement(criteriaByIdentification);

        if (auxParkRanger != null) {

            return parkRangers.removeElement(auxParkRanger);

        }

        return false;
    }

    public boolean updateParkRanger(String identification, String names, String lastNames, LocalDate dateOfHire,RangerStatus status) {

        Criteria<ParkRanger> criteriaByIdentification = parkRanger -> parkRanger.getIdentification().equals(identification);

        ParkRanger auxParkRanger = parkRangers.getElement(criteriaByIdentification);

        if (auxParkRanger == null) {
            return false;

        }
        Consumer<ParkRanger> updateAction = parkRanger -> {
            parkRanger.setStatus(status);
            parkRanger.setNames(names);
            parkRanger.setLastNames(lastNames);
            parkRanger.setDateOfHire(dateOfHire);
        };
        return this.parkRangers.update(auxParkRanger, criteriaByIdentification, updateAction);

    }

    public boolean addArea(Area newArea) {

        Criteria<Area> criteriaByCodeArea = area -> area.getCodeArea().equals(newArea.getCodeArea());

        Area auxArea = areas.getElement(criteriaByCodeArea);

        if (auxArea == null) {

            return areas.insertElement(newArea);

        }
        auxArea = null;

        return false;

    }

    public boolean removeArea(String codeArea) {

        Criteria<Area> criteriaByCodeArea = area -> area.getCodeArea().equals(codeArea);

        Area auxArea = areas.getElement(criteriaByCodeArea);

        if (auxArea != null) {

            return areas.removeElement(auxArea);

        }

        return false;

    }

    public boolean updateArea(String codeArea, String name, String description, StateArea stateArea) {

        Criteria<Area> criteriaByCodeArea = area -> area.getCodeArea().equals(codeArea);

        Area auxArea = areas.getElement(criteriaByCodeArea);

        if (auxArea == null) {

            return false;

        }
        Consumer<Area> updateAccion = area -> {

            area.setCodeArea(codeArea);
            area.setDescription(description);
            area.setName(name);
            area.setStateArea(stateArea);

        };

        return areas.update(auxArea, criteriaByCodeArea, updateAccion);

    }

    public boolean addIncident(Incident newIncident) {
        
        if (newIncident.agentSupport.getStatus() == RangerStatus.busy){
        return false;
        }

        Criteria<Incident> criteriaByCodeIncident = incident -> incident.getIncidentCode().equals(newIncident.getIncidentCode());

        Incident auxIncident = incidents.getElement(criteriaByCodeIncident);

        if (auxIncident == null) {

            return incidents.insertElement(newIncident);
        }
        auxIncident = null;
        return false;

    }

    public boolean removeIncident(String codeIncident) {

        Criteria<Incident> criteriaByCodeIncident = incident -> incident.getIncidentCode().equals(codeIncident);

        Incident auxIncident = incidents.getElement(criteriaByCodeIncident);

        if (auxIncident != null) {

            return incidents.removeElement(auxIncident);
        }
        return false;

    }

    public boolean updateIncident(String incidentCode, String description, LocalDateTime dateTimeReport, LocalDateTime dateTimeAttention, ParkRanger agentSupport, IncidentStatus incidentStatus, String annotations, Area area) {

        Criteria<Incident> criteriaByCodeIncident = incident -> incident.getIncidentCode().equals(incidentCode);

        Incident auxIncident = incidents.getElement(criteriaByCodeIncident);

        if (auxIncident == null) {
            return false;
        }
        Consumer<Incident> updateAction = incident -> {
         
            incident.setIncidentCode(incidentCode);
            incident.setAnnotations(annotations);
            incident.setDescription(description);
            incident.setDateTimeReport(dateTimeReport);
            incident.setDateTimeAttention(dateTimeAttention);
            incident.setAgentSupport(agentSupport);
            incident.setIncidentStatus(incidentStatus);
            incident.setArea(area);

        };

        return incidents.update(auxIncident, criteriaByCodeIncident, updateAction);

    }

    //Pendiente por arreglar   /*Arreglado*/
    public String ordenarIncidentesPorFechaReporte(LocalDateTime startTime, LocalDateTime endTime) {
        
        ErrorControl errorController = new ErrorControl();
        
        if (!errorController.validateDateTimeRange(startTime, endTime)){
        
        return "Error: la fecha de inicio  ingresada es igual o sobrepasa la fecha de fin";
        }
        
        TimeIntervalCriteria<Incident> intervalCriteria = (incident, start, end)
                -> incident.getDateTimeReport().isAfter(start) && incident.getDateTimeReport().isBefore(end);

        ArrayList<Incident> auxIncidents = incidents.getAllElementInInterval(intervalCriteria, startTime, endTime);

        // Ordenar los incidentes por fecha de reporte en orden descendente 
        Collections.sort(auxIncidents, new Comparator<Incident>() {
            @Override
            public int compare(Incident incident1, Incident incident2) {
                LocalDateTime fechaReporte1 = incident1.getDateTimeReport();
                LocalDateTime fechaReporte2 = incident2.getDateTimeReport();
                return fechaReporte2.compareTo(fechaReporte1);
            }
        });

        // Construir un StringBuilder con los incidentes ordenados
        StringBuilder builder = new StringBuilder();
        for (Incident incident : auxIncidents) {
            builder.append(incident);
            builder.append("\n");
        }

        return builder.toString();
    }

}
