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
//     //   System.out.println(listaParkRanger.getAllElement());
//        // Actualizar el guardaparques con la identificación "123456789"
//        ;

       // System.out.println(manager.updateParkRanger("123456789", "Actualizado", "Apellido Actualizado", LocalDate.of(2020, 5, 15)));
        // Crear una instancia de NatureReserveManager
        NatureReserveManager manager = new NatureReserveManager();
        LinkedList<Incident> incidentes = new LinkedList<>();
        // Crear instancias de ParkRanger
        ParkRanger parkRanger1 = new ParkRanger(LocalDate.of(2022, 3, 15), "123456", "John", "Doe", RangerStatus.free);
        ParkRanger parkRanger2 = new ParkRanger(LocalDate.of(2021, 8, 20), "789012", "Jane", "Smith", RangerStatus.free);
        // Crea instancias de Area
        Area area1 = new Area("001", "Observation Area", "An area designated for observing wildlife.", StateArea.accessible);
        Area area2 = new Area("002", "Recreational Area", "An area for recreational activities such as picnicking and hiking.", StateArea.accessible);
        Area area3 = new Area("003", "Research Area", "An area reserved for scientific research and study.", StateArea.accessible);

        // Crea instancias de Incident
        Incident incident1 = new Incident("001", "Animal avistado", LocalDateTime.of(2021, 3, 19, 15, 30), LocalDateTime.of(2022, 3, 15, 11, 0), parkRanger1, IncidentStatus.resolved, "Ninguna", area1);
        Incident incident2 = new Incident("002", "Basura encontrada", LocalDateTime.of(2022, 3, 16, 14, 45), LocalDateTime.of(2022, 3, 16, 15, 15), parkRanger2, IncidentStatus.pending, "Se requiere limpieza", area2);
        Incident incident3 = new Incident("003", "Sendero bloqueado", LocalDateTime.of(2021, 3, 17, 11, 0), LocalDateTime.of(2022, 3, 17, 11, 30), parkRanger1, IncidentStatus.inProcess, "Se está trabajando en despejar el sendero", area3);
        Incident incident4 = new Incident("005", "Sendero bloqueado", LocalDateTime.of(2025, 3, 17, 11, 0), LocalDateTime.of(2022, 3, 17, 11, 30), parkRanger1, IncidentStatus.inProcess, "Se está trabajando en despejar el sendero", area3);

        // Agregar incidentes al gestor     
        manager.addIncident(incident4);

        manager.addIncident(incident1);

        manager.addIncident(incident2);

        manager.addIncident(incident3);

        // Definir el intervalo de tiempo para el cual se desean ordenar los incidentes
        LocalDateTime startTime = LocalDateTime.of(2021, 3, 15, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2025, 3, 17, 23, 59);

        System.out.println(manager.ordenarIncidentesPorFechaReporte(startTime, endTime));












                                        /*Nota lo que se encuenta comentado se utilizo para fines de prueba*/
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        

   }
}
