/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataPack;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author ASUS
 */
public class Principal {

    public static void main(String[] args) {
        NatureReserveManager manager = new NatureReserveManager();
        
        // Crear instancias de ParkRanger
        ParkRanger parkRanger1 = new ParkRanger(LocalDate.of(2022, 3, 15), "123456", "John", "Doe", RangerStatus.free);
        ParkRanger parkRanger2 = new ParkRanger(LocalDate.of(2021, 8, 20), "789012", "Jane", "Smith", RangerStatus.free);
        
        manager.addParkRanger(parkRanger1);
        manager.addParkRanger(parkRanger2);
        
        Visitor visitor1 = new Visitor("123 Main St", "555-1234", VisitStatus.Inactive, "V1234", "Vistante 1", "Doe");
        Visitor visitor2 = new Visitor("456 Elm St", "555-5678", VisitStatus.Inactive, "V5678", "Visitante 2", "Smith");
        Visitor visitor3 = new Visitor("789 Oak St", "555-9012", VisitStatus.Inactive, "V9012", "Alice", "Johnson");
        
        manager.addVisitor(visitor1);
        manager.addVisitor(visitor2);
        manager.addVisitor(visitor3);
        
        // Crea instancias de Area
        Area area1 = new Area("001", "Observation Area", "An area designated for observing wildlife.", StateArea.accessible);
        Area area2 = new Area("002", "Recreational Area", "An area for recreational activities such as picnicking and hiking.", StateArea.accessible);
        
        manager.addArea(area1);
        manager.addArea(area2);
        
        // Se reportan Incidencias
        Incident incident1 = new Incident("001", "Animal avistado", LocalDateTime.of(2021, 3, 19, 15, 30), LocalDateTime.of(2021, 3, 19, 7, 30), visitor1, parkRanger1, IncidentStatus.resolved, "Ninguna", area1);
        Incident incident2 = new Incident("002", "Lost item", LocalDateTime.of(2021, 3, 20, 10, 0), LocalDateTime.of(2021, 3, 19, 9, 30), visitor2, parkRanger2, IncidentStatus.pending, "Keys", area2);

        // Agregar incidentes al gestor
        manager.addIncident(incident1);
        manager.addIncident(incident2);
        
        // Definir el intervalo de tiempo para el cual se desean ordenar los incidentes
        LocalDateTime startTime = LocalDateTime.of(2021, 3, 15, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2025, 3, 17, 23, 59);

        System.out.println(manager.orderIncidentsByReportDate(startTime, endTime));
        
        /*Nota lo que se encuenta comentado se utilizo para fines de prueba*/
        
        // Creating instances of Visit
        Visit visit1 = new Visit("V-1", visitor1, LocalDate.of(2021, 3, 19));
        Visit visit2 = new Visit("V-2", visitor2, LocalDate.of(2021, 3, 19));
        
        manager.addVisit(visit1);
        manager.addVisit(visit2);
        manager.endVisit("V-1", LocalDate.now());
        manager.endVisit("V-n", LocalDate.now());
        
        String mensaje="";
        /*mensaje  = (manager.endVisit("V-1", LocalDate.now()))?"Visita terminada":"Fallo de cierre";//Prueba de fin de visita
        System.out.println(mensaje);
        mensaje = (manager.endVisit("V-2", LocalDate.now()))?"Visita terminada":"Fallo de cierre";//no finaliza un visita si ya esta finalizada
        System.out.println(mensaje);*/
        mensaje = (manager.endVisit("V-2", LocalDate.now()))?"Visita terminada":"Fallo de cierre";//no finaliza un visita si ya esta finalizada
        System.out.println(mensaje);
        
        Visit visit3 = new Visit("V-3", visitor2,  LocalDate.of(2021, 3, 19));
        
        mensaje = (manager.addVisit(visit3))?"Añadido aún siendo repetido":"Visita no añadida\n";
        System.out.println(mensaje);
        
        LocalDate search = LocalDate.of(2021, 3, 19);
        
        System.out.println("Informe de visitantes para el "+search+":\n"
                +manager.generateDailyVisitorReport(search));
        }
}
