package dataPack;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class NatureReserveManager {

    public LinkedList<Area> areas;
    public LinkedList<ParkRanger> parkRangers;
    public LinkedList<Incident> incidents;
    public LinkedList<Visitor> visitors;
    public LinkedList<Visit> visits;

    public NatureReserveManager()
    {
        this.areas = new LinkedList<>();
        this.parkRangers = new LinkedList<>();
        this.incidents = new LinkedList<>();
        this.visitors = new LinkedList<>();
        this.visits = new LinkedList<>();
    }

    public boolean addVisitor(Visitor newVisitor)
    {
        Criteria<Visitor> criteriaByIdentification = visitor -> visitor.getIdentification().equals(newVisitor.getIdentification());

        if (visitors.getElement(criteriaByIdentification) == null) {
            return visitors.insertElement(newVisitor);
        }
        return false;
    }

    public boolean removeVisitor(String identification)
    {
        Criteria<Visitor> criteriaByIdentification = visitor -> visitor.getIdentification().equals(identification);
        Visitor auxVisitor = visitors.getElement(criteriaByIdentification);

        if (auxVisitor != null) {
            return visitors.removeElement(auxVisitor);
        }
        return false;
    }

    public boolean updateVisitor(String address, String phoneNumber, String identification, String names, String lastNames)
    {
        Criteria<Visitor> criteriaByIdentification = visitor -> visitor.getIdentification().equals(identification);
        Visitor auxVisitor = visitors.getElement(criteriaByIdentification);

        if (auxVisitor == null) {
            return false;
        }

        Consumer<Visitor> updateAction = updateVistor -> {
            updateVistor.setAddress(address);
            updateVistor.setNames(names);
            updateVistor.setLastNames(lastNames);
            updateVistor.setPhoneNumber(phoneNumber);
        };
        return this.visitors.updateElement(auxVisitor, criteriaByIdentification, updateAction);
    }

    public boolean addVisit(Visit newVisit)
    {
        LinkedList<Visit> activeVisits = getActiveVisits();
        for (Visit visit : activeVisits) {
            if (visit.getVisitor().equals(newVisit.getVisitor())) {
                return false;
            }
        }
        return visits.insertElement(newVisit);
    }

    public boolean removeVisit(String codeVisit)
    {
        Criteria<Visit> criteriaByCodeVisit = visit -> visit.getCodeVisit().equals(codeVisit);
        Visit visitToRemove = visits.getElement(criteriaByCodeVisit);

        if (visitToRemove != null) {
            return visits.removeElement(visitToRemove);
        }
        return false;
    }

    public boolean updateVisit(String codeVisit, Person visitor, VisitStatus status, LocalDateTime entryDate, LocalDateTime exitDate)
    {
        Criteria<Visit> criteriaByCodeVisit = visit -> visit.getCodeVisit().equals(codeVisit);
        Visit visitToUpdate = visits.getElement(criteriaByCodeVisit);

        if (visitToUpdate == null) {
            return false;
        }

        Consumer<Visit> updateAction = updateVisit -> {
            updateVisit.setVisitor(visitor);
            updateVisit.setStatus(status);
            updateVisit.setEntryDate(entryDate);
            if (exitDate != null) {
                updateVisit.setExitDate(exitDate);
            }
        };
        return this.visits.updateElement(visitToUpdate, criteriaByCodeVisit, updateAction);
    }

    public boolean addParkRanger(ParkRanger newParkRanger)
    {

        Criteria<ParkRanger> criteriaByCedula = parkRanger -> parkRanger.getIdentification().equals(newParkRanger.getIdentification());
        ParkRanger auxParkRanger = parkRangers.getElement(criteriaByCedula);

        if (auxParkRanger == null) {

            return parkRangers.insertElement(newParkRanger);

        }
        auxParkRanger = null; // Limpiar el objeto auxParkRanger
        return false;

    }

    public boolean removeParkRanger(String identification)
    {

        Criteria< ParkRanger> criteriaByIdentification = parkRanger -> parkRanger.getIdentification().equals(identification);
        ParkRanger auxParkRanger = parkRangers.getElement(criteriaByIdentification);

        if (auxParkRanger != null) {

            return parkRangers.removeElement(auxParkRanger);

        }

        return false;
    }

    public boolean updateParkRanger(String identification, String names, String lastNames, LocalDate dateOfHire, RangerStatus status)
    {

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

    public boolean addArea(Area newArea)
    {

        Criteria<Area> criteriaByCodeArea = area -> area.getCodeArea().equals(newArea.getCodeArea());
        Area auxArea = areas.getElement(criteriaByCodeArea);

        if (auxArea == null) {

            return areas.insertElement(newArea);

        }
        auxArea = null;

        return false;

    }

    public boolean removeArea(String codeArea)
    {

        Criteria<Area> criteriaByCodeArea = area -> area.getCodeArea().equals(codeArea);
        Area auxArea = areas.getElement(criteriaByCodeArea);

        if (auxArea != null) {

            return areas.removeElement(auxArea);

        }

        return false;

    }

    public boolean updateArea(String codeArea, String name, String description)
    {

        Criteria<Area> criteriaByCodeArea = area -> area.getCodeArea().equals(codeArea);
        Area auxArea = areas.getElement(criteriaByCodeArea);

        if (auxArea == null) {

            return false;

        }
        Consumer<Area> updateAccion = area -> {

            area.setCodeArea(codeArea);
            area.setDescription(description);
            area.setName(name);
        };

        return areas.update(auxArea, criteriaByCodeArea, updateAccion);

    }

    public boolean addIncident(Incident newIncident)
    {
        Criteria<Incident> criteriaByCodeIncident = incident -> incident.getIncidentCode().equals(newIncident.getIncidentCode());
        Incident auxIncident = incidents.getElement(criteriaByCodeIncident);

        if (auxIncident == null) {

            return incidents.insertElement(newIncident);
        }
        auxIncident = null;
        return false;

    }

    public boolean removeIncident(String codeIncident)
    {

        Criteria<Incident> criteriaByCodeIncident = incident -> incident.getIncidentCode().equals(codeIncident);

        Incident auxIncident = incidents.getElement(criteriaByCodeIncident);

        if (auxIncident != null) {

            return incidents.removeElement(auxIncident);
        }
        return false;

    }

    public boolean updateIncident(String incidentCode, String description, LocalDateTime dateTimeReport,
            LocalDateTime dateTimeAttention, ParkRanger agentSupport, IncidentStatus incidentStatus, String annotations,
            Area area)
    {

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

    public String orderIncidentsByReportDate(LocalDateTime startTime, LocalDateTime endTime)
    {

        ErrorControl errorController = new ErrorControl();

        if (!errorController.validateDateTimeRange(startTime, endTime)) {

            return "Error: la fecha de inicio  ingresada es igual o sobrepasa la fecha de fin";
        }

        TimeIntervalCriteria<Incident> intervalCriteria = (incident, start, end)
                -> incident.getDateTimeReport().isAfter(start) && incident.getDateTimeReport().isBefore(end);

        ArrayList<Incident> auxIncidents = incidents.getAllElementInInterval(intervalCriteria, startTime, endTime);

        // Ordenar los incidentes por fecha de reporte en orden descendente 
        Collections.sort(auxIncidents, new Comparator<Incident>() {
            @Override
            public int compare(Incident incident1, Incident incident2)
            {
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

    public String generateDailyVisitorReport(LocalDate date)
    {
        StringBuilder report = new StringBuilder();

        // Filtrar las visitas para obtener solo las que coinciden con la fecha dada
        LinkedList<Visit> dailyVisits = visitsPerDay(date);

        // Generar el informe con la información de las visitas filtradas
        if (dailyVisits.isEmpty()) {
            report.append("No hay visitantes para la fecha ").append(date).append(".");
        } else {
            report.append("Informe de visitantes para el ").append(date).append(":\n");
            for (Visit visit : dailyVisits) {
                report.append("- ").append(visit.getVisitor().fullname());
                if (visit.getExitDate() == null) {
                    report.append(" (Aún en la reserva)");
                }
                report.append("\n");
            }
        }
        return report.toString();
    }

    public LinkedList<Visit> visitsPerDay(LocalDate date)
    {
        LinkedList<Visit> dailyVisits = new LinkedList<>();
        for (Visit visit : visits) {
            if (visit.getEntryDate().equals(date)) {
                dailyVisits.insertElement(visit);
            }
        }
        return dailyVisits;
    }

    public LinkedList<Visit> getActiveVisits()
    {
        LinkedList<Visit> activeVisits = new LinkedList<>();

        for (Visit visit : visits) {
            if (visit.getStatus() == VisitStatus.Active) {
                activeVisits.insertElement(visit);
            }
        }
        return activeVisits;
    }

}
