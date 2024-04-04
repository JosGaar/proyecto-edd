package proyecto;

import dataStructures.LinkedList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;

public class NatureReserveManager {

    public LinkedList<Area> areas;
    public LinkedList<ParkRanger> parkRangers;
    public LinkedList<Incident> incidents;
    public LinkedList<Visitor> visitors;
    public LinkedList<Visit> visits;
    
    public NatureReserveManager() {
        this.areas = new LinkedList<>();
        this.parkRangers = new LinkedList<>();
        this.incidents = new LinkedList<>();
        this.visitors = new LinkedList<>();
        this.visits = new LinkedList<>();
    }
    
    public boolean addVisitor(Visitor newVisitor) {
        if(newVisitor == null)
            return false;
        if(!visitors.contains(newVisitor)){
            return visitors.addByLast(newVisitor);
        }
        return false;
     }
    
    public boolean removeVisitor(String identification) {
        if(identification == null)
            return false;
        Criteria<Visitor> criteriaByIdentification = visitor -> visitor.getIdentification().equals(identification);
        Visitor auxVisitor = visitors.getElement(criteriaByIdentification);
        
        if (auxVisitor != null) 
            return visitors.remove(auxVisitor);
        return false;
    }
    
    public boolean updateVisitor(String address, String phoneNumber, VisitStatus status, String identification, String names, String lastNames) {
        Criteria<Visitor> criteriaByIdentification = visitor -> visitor.getIdentification().equals(identification);
        Visitor auxVisitor = visitors.getElement(criteriaByIdentification);
        
        if (auxVisitor == null) 
            return false;
        
        Consumer<Visitor> updateAction = updateVistor -> {
            updateVistor.setAddress(address);
            updateVistor.setNames(names);
            updateVistor.setLastNames(lastNames);
            updateVistor.setPhoneNumber(phoneNumber);
        };
        return this.visitors.update(auxVisitor, criteriaByIdentification, updateAction);
    }
    
    public boolean addVisit(Visit newVisit) {
        if(!this.visitors.contains(newVisit.getVisitor()))
            return false;
        if(this.visits.contains(newVisit))
            return false;
        
        LinkedList<Visitor> activeVisitors = getActiveVisitors();
        if(activeVisitors.contains(newVisit.getVisitor()))
            return false;
        
        if(visits.addByLast(newVisit)){
            newVisit.getVisitor().setStatus(VisitStatus.Active);
            newVisit.getVisitor().insertVisit(newVisit);
            return true;
        }
        return false;
    }
    
    public boolean endVisit(String codeVisit, LocalDateTime endOfVisit) {
        Visit visitToEnd = this.visits.getElement(codeVisit);
        
        if(visitToEnd == null || !visitToEnd.getVisitor().getStatus().equals(VisitStatus.Active))
                return false;
        
        visitToEnd.getVisitor().setStatus(VisitStatus.Inactive );
        visitToEnd.setExitDate(endOfVisit);
        return true;
    }
    
    public boolean removeVisit(String codeVisit) {
        Criteria<Visit> criteriaByCodeVisit = visit -> visit.getCodeVisit().equals(codeVisit);
        Visit visitToRemove = visits.getElement(criteriaByCodeVisit);
        
        if (visitToRemove != null) 
            return visits.remove(visitToRemove);
        return false;
    }

    public boolean updateVisit(String codeVisit, Visitor visitor, LocalDateTime entryDate, LocalDateTime exitDate) {
        Criteria<Visit> criteriaByCodeVisit = visit -> visit.getCodeVisit().equals(codeVisit);
        Visit visitToUpdate = visits.getElement(criteriaByCodeVisit);
        
        if (visitToUpdate == null) 
            return false;
        
        Consumer<Visit> updateAction = updateVisit -> {
            updateVisit.setVisitor(visitor);
            updateVisit.setEntryDate(entryDate);
            if (exitDate != null) {
                updateVisit.setExitDate(exitDate);
            }
        };
        return this.visits.update(visitToUpdate, criteriaByCodeVisit, updateAction);
    }

    public boolean addParkRanger(ParkRanger newParkRanger) {
        Criteria< ParkRanger> criteriaByCedula = parkRanger -> parkRanger.getIdentification().equals(newParkRanger.getIdentification());
        ParkRanger auxParkRanger = parkRangers.getElement(criteriaByCedula);
        
        if (auxParkRanger == null) 
            return parkRangers.addByLast(newParkRanger);
        return false;
    }

    public boolean removeParkRanger(String identification) {
        Criteria< ParkRanger> criteriaByIdentification = parkRanger -> parkRanger.getIdentification().equals(identification);
        ParkRanger auxParkRanger = parkRangers.getElement(criteriaByIdentification);
        
        if (auxParkRanger != null) 
            return parkRangers.remove(auxParkRanger);

        return false;
    }

    public boolean updateParkRanger(String identification, String names, String lastNames, LocalDate dateOfHire,RangerStatus status) {
        Criteria<ParkRanger> criteriaByIdentification = parkRanger -> parkRanger.getIdentification().equals(identification);
        ParkRanger auxParkRanger = parkRangers.getElement(criteriaByIdentification);

        if (auxParkRanger == null) 
            return false;

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

        if (auxArea == null) 
            return areas.addByLast(newArea);
        return false;
    }

    public boolean removeArea(String codeArea) {
        Criteria<Area> criteriaByCodeArea = area -> area.getCodeArea().equals(codeArea);
        Area auxArea = areas.getElement(criteriaByCodeArea);

        if (auxArea != null) 
            return areas.remove(auxArea);

        return false;
    }

    public boolean updateArea(String codeArea, String name, String description) {
        Criteria<Area> criteriaByCodeArea = area -> area.getCodeArea().equals(codeArea);
        Area auxArea = areas.getElement(criteriaByCodeArea);

        if (auxArea == null) 
            return false;

        Consumer<Area> updateAccion = area -> {
            area.setCodeArea(codeArea);
            area.setDescription(description);
            area.setName(name);
        };

        return areas.update(auxArea, criteriaByCodeArea, updateAccion);
    }

    public boolean addIncident(Incident newIncident) {
        if (newIncident.agentSupport.getStatus() == RangerStatus.busy)
            return false;
        
        Criteria<Incident> criteriaByCodeIncident = incident -> incident.getIncidentCode().equals(newIncident.getIncidentCode());
        if (this.incidents.contains(criteriaByCodeIncident)) {
            if(incidents.addByLast(newIncident)){
                newIncident.getArea().incidents.addByLast(newIncident);
                return true;
            } else{
                return false;
            }
        }
        
        return false;
    }

    public boolean removeIncident(String codeIncident) {
        Criteria<Incident> criteriaByCodeIncident = incident -> incident.getIncidentCode().equals(codeIncident);
        Incident auxIncident = incidents.getElement(criteriaByCodeIncident);

        if (auxIncident != null) 
            return incidents.remove(auxIncident);

        return false;
    }

    public boolean updateIncident(String incidentCode, String description, LocalDateTime dateTimeReport, LocalDateTime dateTimeAttention, ParkRanger agentSupport, IncidentStatus incidentStatus, String annotations, Area area) {
        Criteria<Incident> criteriaByCodeIncident = incident -> incident.getIncidentCode().equals(incidentCode);
        Incident auxIncident = incidents.getElement(criteriaByCodeIncident);

        if (auxIncident == null) 
            return false;

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
    
    public void showIncidentes(){
        LinkedList<Incident> incidentsVisible = this.getVisbleIncidents();
        if (!incidentsVisible.isEmpty()) {
            System.out.println("No se han registrado incidentes por el momento.");
            return;
        }
        System.out.println("\nIncidentes disponibles: ");
        String message = incidentsVisible.getElementsToString();
        System.out.println(((message!=null)?message:"No se han registrado incidentes por el momento."));
    }
    
    private LinkedList<Incident> getVisbleIncidents(){
        Criteria<Incident> criteriaForIncidents = incident -> incident.getVisibility() == Visibility.visible;
        LinkedList<Incident> incidentsVisible = this.incidents.getElements(criteriaForIncidents);
        return incidentsVisible;
    }
    
    public boolean atenderIncident(Incident in, ParkRanger parkRangerIdSelect, LocalDateTime dateAttention) {
        LinkedList<Incident> pendingIncidents = this.getPendingIncidents();
        Incident incidentToAttend = pendingIncidents.getElement(in.getIncidentCode());
        
        if (incidentToAttend == null) {
            return false;
        }
        
        LinkedList<ParkRanger> freeParkRangers = this.getFreeParkRangers();
        ParkRanger parkRangerToAssign = freeParkRangers.getElement(parkRangerIdSelect.getIdentification());
        
        if (parkRangerToAssign == null) {
            return false;
        }
        
        if (incidentToAttend.getDateTimeReport().compareTo(dateAttention) < 0) {
            return false;
        }
        
        incidentToAttend.setAgentSupport(parkRangerToAssign);
        incidentToAttend.setDateTimeAttention(dateAttention);
        incidentToAttend.setIncidentStatus(IncidentStatus.inProcess);
        
        return true;
    }
    
    private LinkedList<Incident> getPendingIncidents(){
        LinkedList<Incident> incidentsVisible = this.getVisbleIncidents();
        return incidentsVisible.getElements(incident -> incident.getIncidentStatus() == IncidentStatus.pending);
    }
    
    private LinkedList<ParkRanger> getFreeParkRangers(){
        return this.parkRangers.getElements(parkRanger -> parkRanger.getStatus() == RangerStatus.free);
    }
    
    public boolean terminateIncident(Incident incidentToTerminate) {
        LinkedList<Incident> inProcessIncidents = this.getInProcessIncidents();

        if (inProcessIncidents.isEmpty()) {
            return false;
        }
        
        if (inProcessIncidents.contains(incidentToTerminate)) {
            incidentToTerminate.setIncidentStatus(IncidentStatus.resolved);
            incidentToTerminate.getAgentSupport().setStatus(RangerStatus.free);
            return true;
        }
        return false;
    }
    
    private LinkedList<Incident> getInProcessIncidents(){
        LinkedList<Incident> incidentsVisible = this.getVisbleIncidents();
        return incidentsVisible.getElements(incident -> incident.getIncidentStatus() == IncidentStatus.inProcess);
    }
    
    public String orderIncidentsByReportDate(LocalDateTime startTime, LocalDateTime endTime) {
        ErrorControl errorController = new ErrorControl();
        if (!errorController.validateDateTimeRange(startTime, endTime)) {
            return "Error: la fecha de inicio ingresada es igual o sobrepasa la fecha de fin";
        }

        TimeIntervalCriteria<Incident> intervalCriteria = (incident, start, end) ->
                incident.getDateTimeReport().isAfter(start) && incident.getDateTimeReport().isBefore(end);

        ArrayList<Incident> auxIncidents = incidents.getAllElementInInterval(intervalCriteria, startTime, endTime);

        Collections.sort(auxIncidents, (incident1, incident2) -> {
            LocalDateTime fechaReporte1 = incident1.getDateTimeReport();
            LocalDateTime fechaReporte2 = incident2.getDateTimeReport();
            return fechaReporte2.compareTo(fechaReporte1);
        });

        StringBuilder builder = new StringBuilder();
        for (Incident incident : auxIncidents) {
            builder.append(incident);
            builder.append("\n");
        }

        return builder.toString();
    }
    
    public String generateDailyVisitorReport(LocalDate date) {
        LinkedList<Visit> dailyVisits = this.visitsPerDay(date);
        LinkedList<Visit> activeVisits = this.getActiveVisits();
        StringBuilder report = new StringBuilder();
        
        report.append("\n=== Informe de visitantes para el ").append(date).append(": ===\n");
        if (dailyVisits.isEmpty()) {
            report.append("- No hay visitantes para la fecha. ").append(date).append(".");
        }
        
        if (activeVisits.isEmpty()) {
            report.append("\n- No se encontraron visitantes con una visita activa. \n");
        }
        
        if(dailyVisits.isEmpty() && activeVisits.isEmpty()){
            return report.toString();
        }
        
        LinkedList<Visit> visitsToAppend = this.getAllVisitsReport(dailyVisits, activeVisits);
        appendVisitorList(report, visitsToAppend);
        appendStatistics(report, dailyVisits);
        
        return report.toString();
    }
    
    private LinkedList<Visit> getAllVisitsReport(LinkedList<Visit> dailyVisits, LinkedList<Visit> activeVisits){
        LinkedList<Visit> allVisits = new LinkedList<>();
        for (Visit visit : dailyVisits) {
            if (!allVisits.contains(visit)) {
                allVisits.addByLast(visit);
            }
        }
        for (Visit visit : activeVisits) {
            if (!allVisits.contains(visit)) {
                allVisits.addByLast(visit);
            }
        }
        return allVisits;
    }
    
    private void appendVisitorList(StringBuilder report, LinkedList<Visit> visitsToAppend) {
        report.append(visitsToAppend.getElementsToString());
    }
    
    private void appendStatistics(StringBuilder report, LinkedList<Visit> dailyVisits) {
        report.append("\n\n=== Estadísticas: ===\n");
        int totalVisitingTime = getTotalVisitingTime(dailyVisits);
        int numberOfVisitors = dailyVisits.size();
        double averageVisitingTime = calculateAverageVisitingTime(totalVisitingTime, numberOfVisitors);
        
        report.append("- Número de visitantes en el día: ").append(numberOfVisitors).append("\n");
        report.append("- Tiempo promedio de visita: ").append(averageVisitingTime).append(" minutos\n");
        
        LinkedList<Visitor> repeatedVisitors = findRepeatedVisitors(dailyVisits);
        if (!repeatedVisitors.isEmpty()) {
            report.append("- Visitantes frecuentes en el día:\n");
            for (Visitor visitor : repeatedVisitors) {
                report.append("  - ").append(visitor.getIdentification())
                        .append(visitor.fullname()).append("\n");
            }
        }
    }
    
    private int getTotalVisitingTime(LinkedList<Visit> dailyVisits) {
        int totalVisitingTime = 0;
        for (Visit visit : dailyVisits) {
            if (visit.getExitDate() != null) {
                totalVisitingTime += ChronoUnit.MINUTES.between(visit.getEntryDate(), visit.getExitDate());
            }
        }
        return totalVisitingTime;
    }

    private double calculateAverageVisitingTime(int totalVisitingTime, int numberOfVisitors) {
        return (numberOfVisitors > 0) ? (double) totalVisitingTime / numberOfVisitors : 0;
    }
    
    private LinkedList<Visitor> findRepeatedVisitors(LinkedList<Visit> dailyVisits) {
        LinkedList<Visitor> repeatedVisitors = new LinkedList<>();
        LinkedList<String> visitedIds = new LinkedList<>();
        
        for (Visit visit : dailyVisits) {
            String visitorId = visit.getVisitor().getIdentification();
            if (visitedIds.contains(visitorId) && !repeatedVisitors.contains(visitorId)) {
                repeatedVisitors.addByLast(visit.getVisitor());
            }
            visitedIds.addByLast(visitorId);
        }
        
        return repeatedVisitors;
    }
    
    private LinkedList<Visit> visitsPerDay(LocalDate date){
        LinkedList<Visit> dailyVisits = new LinkedList<>();
        for (Visit visit : visits) {
            if (visit.getEntryDate().toLocalDate().equals(date)) {
                dailyVisits.addByLast(visit);
            }
        }
        return dailyVisits;
    }
    
    private LinkedList<Visit> getActiveVisits() {
        Criteria<Visit> criteriaByActivity = visit -> visit.getVisitor().getStatus().equals(VisitStatus.Active);
        return this.visits.getElements(criteriaByActivity);
    }
    
    private LinkedList<Visitor> getActiveVisitors() {
        Criteria<Visitor> criteriaByActivity = visitor -> visitor.getStatus().equals(VisitStatus.Active);
        return this.visitors.getElements(criteriaByActivity);
    }
}
