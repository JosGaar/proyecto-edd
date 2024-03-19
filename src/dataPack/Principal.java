/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataPack;

import java.time.LocalDate;

/**
 *
 * @author ASUS
 */
public class Principal {
    public static void main(String[] args) {
//           // Crear una lista enlazada para almacenar guardabosques
//        LinkedList<parkRanger> listaParkRanger = new LinkedList<>();
//
//        // Añadir guardabosques a la lista
//        parkRanger guardabosques1 = new parkRanger("1234567890", "Juan", "Perez", LocalDate.parse("2023-01-15"));
//        parkRanger guardabosques2 = new parkRanger("0987654321", "Maria", "Gomez", LocalDate.parse("2023-03-20"));
// 
//                parkRanger guardabosques3 = new parkRanger("0987654321", "Pepe", "Gomez", LocalDate.parse("2023-03-20"));
//                
//              parkRanger guardabosques4 = null;
//        listaParkRanger.insertElement(guardabosques1);
//        listaParkRanger.insertElement(guardabosques2);
//            listaParkRanger.insertElement(guardabosques4);
//          listaParkRanger.insertElement(guardabosques3);
//listaParkRanger.removeElement(guardabosques4);
//        // Imprimir la lista de guardabosques
//     // Definir el criterio de búsqueda por cédula
//     
//                                          //pametros        //expresion
//Criteria< parkRanger> criteriaByCedula = guardabosques -> guardabosques.lastNames.equals("Juan");
//
//parkRanger guardabosquesEncontrado = listaParkRanger.getElement(criteriaByCedula);
//
//if (guardabosquesEncontrado!=null){
//    System.out.println("Encontrado");  
//    }

// Crear una lista enlazada para almacenar guardabosques
LinkedList<parkRanger> listaGuardabosques = new LinkedList<>();

// Añadir guardabosques a la lista
parkRanger guardabosques1 = new parkRanger("1234567890", "Juan", "Perez", LocalDate.of(2023, 1, 15));
parkRanger guardabosques2 = new parkRanger("0987654321", "Maria", "Gomez", LocalDate.of(2023, 3, 20));

listaGuardabosques.insertElement(guardabosques1);
listaGuardabosques.insertElement(guardabosques2);

// Definir el criterio de búsqueda por cédula
                                         //Parametro        //Funcion a evaluar
Criteria<parkRanger> criteriaByCedula = guardabosques -> guardabosques.getIdentification().equals("1234567890");

// Definir la acción de actualización
Consumer<parkRanger> updateAction = guardabosques -> {
    guardabosques.setNames("NuevoNombre");
    guardabosques.setLastNames("NuevoApellido");

    
};//En esta parte se define la expresion lambda que se ejecutara cuando el metodo accept  sea llamado

// Llamar al método actualizar
listaGuardabosques.update(guardabosques1, criteriaByCedula, updateAction);

        System.out.println("");




    }
}
