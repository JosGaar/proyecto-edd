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
//           // Crear una lista enlazada para almacenar guardabosques
//        LinkedList<ParkRanger> listaParkRanger = new LinkedList<>();
//
//        // Añadir guardabosques a la lista
//        ParkRanger guardabosques1 = new ParkRanger("1234567890", "Juan", "Perez", LocalDate.parse("2023-01-15"));
//        ParkRanger guardabosques4 = new ParkRanger("09876543211", "Maria", "Gomez", LocalDate.parse("2023-03-20"));
// 
//                ParkRanger guardabosques3 = new ParkRanger("0987654321", "Pepe", "Gomez", LocalDate.parse("2023-03-20"));
//                
//                NatureReserveManager gestor = new NatureReserveManager();
//                
//                    System.out.println( gestor.addParkRanger(guardabosques3));
//                    
//                    
//                   // System.out.println(gestor.removeParkRanger(guardabosques));
//                
//            //   System.out.println( gestor.addParkRanger(guardabosques4));
//                
//                
//              ParkRanger guardabosques4 = null;
//        listaParkRanger.insertElement(guardabosques1);
//        listaParkRanger.insertElement(guardabosques2);
//            listaParkRanger.insertElement(guardabosques4);
//          listaParkRanger.insertElement(guardabosques3);
//listaParkRanger.removeElement(guardabosques4);
//        // Imprimir la lista de guardabosques
//     // Definir el criterio de búsqueda por cédula
//     
//                                          //pametros        //expresion
//Criteria< ParkRanger> criteriaByCedula = guardabosques -> guardabosques.lastNames.equals("Juan");
//
//ParkRanger guardabosquesEncontrado = listaParkRanger.getElement(criteriaByCedula);
//
//if (guardabosquesEncontrado!=null){
//    System.out.println("Encontrado");  
//    }
//
// Crear una lista enlazada para almacenar guardabosques
//LinkedList<ParkRanger> listaGuardabosques = new LinkedList<>();
//
// //Añadir guardabosques a la lista
//ParkRanger guardabosques1 = new ParkRanger("1234567890", "Juan", "Perez", LocalDate.of(2023, 1, 15));
//ParkRanger guardabosques2 = new ParkRanger("0987654321", "Maria", "Gomez", LocalDate.of(2023, 3, 20));
//
//listaGuardabosques.insertElement(guardabosques1);
//listaGuardabosques.insertElement(guardabosques2);
//
// //Definir el criterio de búsqueda por cédula
//                                         //Parametro        //Funcion a evaluar
//Criteria<ParkRanger> criteriaByCedula = guardabosques -> guardabosques.getIdentification().equals("1234567890");
//
//ParkRanger auxGuardabosques = listaGuardabosques.getElement(criteriaByCedula);
//
// //Definir la acción de actualización
//Consumer<ParkRanger> updateAction = guardabosques -> {
//    guardabosques.setNames("NuevoNombre");
//    guardabosques.setLastNames("NuevoApellido");
//
//    
//};//En esta parte se define la expresion lambda que se ejecutara cuando el metodo accept  sea llamado
//
// //Llamar al método actualizar
//listaGuardabosques.update(guardabosques1, criteriaByCedula, updateAction);
//
//        System.out.println("");

//
//
    //Crear un objeto Area
       // Area area = new Area("Área de Observación de Aves", "Zona designada para la observación de aves.", StateArea.accessible);

        // Cambiar el estado del área
        //area.setStateArea(StateArea.inaccessible);

//         //Imprimir el nuevo estado del área
////        System.out.println("El estado del área '" + area.getName() + "' ha sido cambiado a: " + area.getStateArea());
//        NatureReserveManager manager = new NatureReserveManager();
//
//        // Crear algunos guardaparques
//        ParkRanger ranger1 = new ParkRanger("123456789", "John", "Doe", LocalDate.of(2020, 5, 15));
//        ParkRanger ranger2 = new ParkRanger("987654321", "Jane", "Smith", LocalDate.of(2018, 8, 20));
//
//        // Agregar los guardaparques al gestor de la reserva natural
//        manager.addParkRanger(ranger1);
//        manager.addParkRanger(ranger2);
//        
//         LinkedList<ParkRanger> listaParkRanger = new LinkedList<>();
//         listaParkRanger.insertElement(ranger2);
//         
//        listaParkRanger.insertElement(ranger1);
//        
//     //   System.out.println(listaParkRanger.getAllElements());
//        // Actualizar el guardaparques con la identificación "123456789"
//        ;

       // System.out.println(manager.updateParkRanger("123456789", "Actualizado", "Apellido Actualizado", LocalDate.of(2020, 5, 15)));
        // Crear una instancia de NatureReserveManager
        NatureReserveManager manager = new NatureReserveManager();
        
        // Crear instancias de ParkRanger
        ParkRanger parkRanger1 = new ParkRanger(LocalDate.of(2022, 3, 15), "123456", "John", "Doe", RangerStatus.free);
        ParkRanger parkRanger2 = new ParkRanger(LocalDate.of(2021, 8, 20), "789012", "Jane", "Smith", RangerStatus.free);
        
        manager.addParkRanger(parkRanger1);
        manager.addParkRanger(parkRanger2);
        
        Visitor visitor1 = new Visitor("123 Main St", "555-1234", VisitStatus.Active, "V1234", "John", "Doe");
        Visitor visitor2 = new Visitor("456 Elm St", "555-5678", VisitStatus.Inactive, "V5678", "Jane", "Smith");
        Visitor visitor3 = new Visitor("789 Oak St", "555-9012", VisitStatus.Active, "V9012", "Alice", "Johnson");
        
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
        Incident incident2 = new Incident("002", "Lost item", LocalDateTime.of(2021, 3, 20, 10, 0), LocalDateTime.of(2021, 3, 19, 9, 30), visitor2, null, IncidentStatus.pending, "Keys", area2);

        // Agregar incidentes al gestor
        manager.addIncident(incident1);
        manager.addIncident(incident2);
        
        // Definir el intervalo de tiempo para el cual se desean ordenar los incidentes
        LocalDateTime startTime = LocalDateTime.of(2021, 3, 15, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2025, 3, 17, 23, 59);

        System.out.println(manager.orderIncidentsByReportDate(startTime, endTime));
        
        
        /*Nota lo que se encuenta comentado se utilizo para fines de prueba*/
        
        // Creating instances of Visit
        Visit visit1 = new Visit("V001", visitor1, VisitStatus.Active, LocalDate.of(2021, 3, 19));
        visit1.setExitDate(LocalDate.of(2021, 3, 19)); // Assuming the visitor exits on the same day
        visit1.setStatus(VisitStatus.Inactive);
        Visit visit2 = new Visit("V002", visitor2, VisitStatus.Active, LocalDate.of(2021, 3, 19));
        
        manager.addVisit(visit1);
        manager.addVisit(visit2);
        
        LocalDate search = LocalDate.of(2021, 3, 19);
        
        System.out.println(manager.generateDailyVisitorReport(search));
        }
}
