package dataPack;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Program {

    Scanner sc = new Scanner(System.in);
    ErrorControl errorControl = new ErrorControl();

    public void run()
    {
        showMainMenu();
    }

    public void showMainMenu()
    {
        int option;
        NatureReserveManager reserveManage = new NatureReserveManager();

        do {
            System.out.println("=== Gestion de la reserva natural ===");
            String message = """
                             1. Gestionar visitantes
                             2. Gestionar guardaparques
                             3. Gestionar incidentes
                             4. Gestionar areas
                             5. Salir
                             Seleccione una opcion: 
                             """;
            option = errorControl.validateNumericInputInt(sc, message);

            switch (option) {
                case 1:
                    visitorMenu(reserveManage);
                    break;
                case 2:
                    parkRangersMenu(reserveManage);
                    break;
                case 3:
                    menuIncidentes(reserveManage);
                    break;
                case 4:
                    menuAreas(reserveManage);
                    break;
                case 5:
                    System.out.println("\nSaliendo...\n");
                    break;
                default:
                    System.err.println("Error, opcion no disponible.\n");
            }

        } while (option != 5);
    }

    public void visitorMenu(NatureReserveManager gestorReserva)
    {

    }

    // Información sobre guardaparques
    public void parkRangersMenu(NatureReserveManager gestorReserva)
    {
        int option;

        do {
            System.out.println();
            String message = """
                             === Guardaparques ===
                             1. Crear guardaparque
                             2. Mostrar guardaparques
                             3. Actualizar guardaparque
                             4. Eliminar guardaparque
                             5. Regresar
                             Seleccione una opcion: 
                             """;
            option = errorControl.validateNumericInputInt(sc, message);

            switch (option) {
                case 1:
                    createParkRanger(gestorReserva);
                    break;
                case 2:
                    System.out.println();
                    showParkRangers(gestorReserva);
                    break;
                case 3:
                    modifyRanger(gestorReserva);
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println("Regresando...\n");
                    break;
                default:
                    System.err.println("Error, opcion no disponible.\n");
            }

        } while (option != 5);
    }

    // Opciones de los guardaparques
    public void createParkRanger(NatureReserveManager gestorReserva)
    {
        String id, firstNames, lastNames, contractDate;
        RangerStatus status;

        System.out.println();
        id = errorControl.validateIDNumber(sc, "Ingrese la cedula de identidad: ");
        contractDate = errorControl.validateDate(sc, "Ingrese la fecha de contrato (en formato yyyy-MM-dd): \n");
        firstNames = errorControl.validateTwoWords(sc, "Ingrese sus dos nombres: ", "nombres");
        lastNames = errorControl.validateTwoWords(sc, "Ingrese sus dos apellidos: ", "apellidos");
        status = parkRangerStatus();

        ParkRanger parkRanger = new ParkRanger(stringToLocalDateConverter(contractDate), id,
                firstNames, lastNames, status);

        if (gestorReserva.addParkRanger(parkRanger)) {
            System.out.println("¡Se ha generado correctamente el guardaparques!");
        } else {
            System.err.println("No se ha podido agregar al guardaparques: cedula ya existente.");
        }
    }

    public LocalDate stringToLocalDateConverter(String dateString)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaLocalDate = LocalDate.parse(dateString, formatter);
        return fechaLocalDate;
    }

    public RangerStatus parkRangerStatus()
    {
        RangerStatus estado = null;
        int opcion;

        do {

            String mensaje = """
                             Seleccione un estado: ocupado (1), desocupado (2): 
                             """;
            opcion = errorControl.validateNumericInputInt(sc, mensaje);

            switch (opcion) {
                case 1:
                    estado = RangerStatus.busy;
                    break;
                case 2:
                    estado = RangerStatus.free;
                    break;
                default:
                    System.err.println("Seleccione un estado valido.\n");
            }

        } while (opcion != 1 && opcion != 2);

        return estado;
    }

    public void showParkRangers(NatureReserveManager gestorReserva)
    {
        Criteria<ParkRanger> criteriaForParkRangersBusy = parkRanger -> parkRanger.getStatus() == (RangerStatus.busy);
        Criteria<ParkRanger> criteriaForParkRangersFree = parkRanger -> parkRanger.getStatus() == (RangerStatus.free);

        List<ParkRanger> busyRangers = gestorReserva.parkRangers.getElements(criteriaForParkRangersBusy);
        List<ParkRanger> freeRangers = gestorReserva.parkRangers.getElements(criteriaForParkRangersFree);

        if (!busyRangers.isEmpty()) {
            System.out.println("Guardabosques ocupados: \n");
            for (ParkRanger parkRanger : busyRangers) {
                System.out.println("Identificacion: " + parkRanger.identification + parkRanger.fullname() + "\n");
            }
        } else {
            System.out.println("No hay guardabosques ocupados.");
        }

        if (!freeRangers.isEmpty()) {
            System.out.println("\nGuardabosques desocupados: \n");
            for (ParkRanger parkRanger : freeRangers) {
                System.out.println("Identificacion: " + parkRanger.identification + parkRanger.fullname() + "\n");
            }
        } else {
            System.out.println("\nNo hay guardabosques desocupados.");
        }
    }

    // Pedir cedula de identidad
    // Preguntar si desea cambiar el nombre
    // Preguntar si desea cambiar los apelldos
    // Preguntar si desea cambiar la fecha de contrato
    // Preguntar si desea cambiar el estado
    public void modifyRanger(NatureReserveManager gestorReserva)
    {
        String id, firstNames, lastNames, contractDate;
        RangerStatus status;

        System.out.println();
        id = errorControl.validateIDNumber(sc, "Ingrese la cedula de identidad: ");

        Criteria<ParkRanger> criteriaForParkRangers = parkRanger -> parkRanger.getIdentification().equals(id);
        ParkRanger auxParkRanger = gestorReserva.parkRangers.getElement(criteriaForParkRangers);

        if (auxParkRanger != null) {
            firstNames = changeRangerNames(auxParkRanger.names);
            contractDate = errorControl.validateDate(sc, "Ingrese la fecha de contrato (en formato yyyy-MM-dd): \n");
            lastNames = errorControl.validateTwoWords(sc, "Ingrese sus dos apellidos: ", "apellidos");
            status = parkRangerStatus();
        } else {
            System.err.println("No se ha encontrado al guardabosques con la cedula proporcionada.\n");
        }
    }

    public String changeRangerNames(String originalName)
    {

        String message, newName = null;
        int option;
        do {

            message = """
                      ¿Desea cambiar el nombre?
                      Si (1) / No (2)
                      """;
            option = errorControl.validateNumericInputInt(sc, message);

            switch (option) {
                case 1:
                    newName = errorControl.validateTwoWords(sc, "Ingrese sus dos nombres: ", "nombres");
                    break;
                case 2:
                    newName = originalName;
                    break;
                default:
                    System.err.println("Error, opcion no encontrada.\n");
            }

        } while (option != 1 && option != 2);

        return newName;
    }

    public void menuIncidentes(NatureReserveManager gestorReserva)
    {

    }

    public void menuAreas(NatureReserveManager gestorReserva)
    {

    }

}
