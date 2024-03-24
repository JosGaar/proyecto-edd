/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataPack;

import java.time.LocalDateTime;
import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class ErrorControl {

    public ErrorControl() {
    }
    
    public int validateNumericInputInt(Scanner input, String message) {
    int number;
    while (true) {
        try {
            System.out.println(message); 
            number = Integer.parseInt(input.nextLine());
            if (number < 0) {
                throw new IllegalArgumentException("Error: El número no puede ser negativo.");

            }
            return number;
        } catch (NumberFormatException e) {
         System.out.println("Error: Por favor ingrese un número válido.");
        } catch (IllegalArgumentException e) {
         System.out.println("Error: Por favor ingrese un número válido.");
        }
    }
}
    public  String validateIDNumber(Scanner input, String message) {
    String idNumber;
    while (true) {
        System.out.println(message); 
        idNumber = input.nextLine();
        if (idNumber.matches("\\d{10}")) { 
            return idNumber;
        } else {
               System.out.println("Error: La cédula debe tener 10 números.");
        }
    }
}
    //Este metodo se puede utilizar para ingresar años/dias/meses/horas/minutos y segundos
    
    /*
    Ejemplo de uso
    
          añoNacimiento = validarFecha(lectura, "año de nacimiento", 4, 2024);
                            mesNacimiento = validarFecha(lectura, "mes de nacimiento", 2, 12);
                            diaNacimiento = validarFecha(lectura, "día de nacimiento", 2, 31);
    
    */
    
    public  int validateDate(Scanner input, String message, int length, int maximum) {
        int number;
        while (true) {
            try {
                System.out.println("Ingrese el " + message + ":");
                String userInput = input.nextLine();

                if (userInput.length() != length) {                               
                    throw new IllegalArgumentException("Error: La longitud de " + message + " debe ser de " + length + " dígitos."); 
                }

                number = Integer.parseInt(userInput);

                if (number <= 0 || number > maximum) {
                          throw new IllegalArgumentException("Error: " + message+ " debe ser un número mayor que cero y menor o igual que " + maximum + ".");  
                }
                return number;
            } catch (NumberFormatException e) {
              System.out.println("Error: Por favor ingrese un número válido para " + message + "."); 
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public boolean validateDateTimeRange(LocalDateTime startDate, LocalDateTime endDate) {
        // Verificar si la fecha de inicio es antes o igual que la fecha de fin
       return !endDate.isBefore(startDate) || startDate.isEqual(endDate);
   }
}
