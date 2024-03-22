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
                             3. Gestionar areas
                             4. Gestionar incidentes
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
                    areasMenu(reserveManage);
                    break;
                case 4:
                    incidentMenu(reserveManage);
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

    // --------------------------------------------------------------------------------------------------------------------------------
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
                    // No se puede eliminar un guardabosques si esta atendiendo una incidencia
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

        Criteria<ParkRanger> criteriaForParkRangers = parkRanger -> parkRanger.getIdentification().equals(id);
        ParkRanger auxParkRanger = gestorReserva.parkRangers.getElement(criteriaForParkRangers);

        if (auxParkRanger == null) {
            contractDate = errorControl.validateDate(sc, "Ingrese la fecha de contrato (en formato yyyy-MM-dd): \n");
            firstNames = errorControl.validateTwoWords(sc, "Ingrese sus dos nombres: ", "nombres");
            lastNames = errorControl.validateTwoWords(sc, "Ingrese sus dos apellidos: ", "apellidos");
            status = parkRangerStatus();

            ParkRanger parkRanger = new ParkRanger(stringToLocalDateConverter(contractDate), id,
                    firstNames, lastNames, status);

            gestorReserva.addParkRanger(parkRanger);
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
            String message = "Seleccione un estado: ocupado (1), desocupado (2): ";
            opcion = errorControl.validateNumericInputInt(sc, message);

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

        // ...
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
        String id, newFirstNames, newLastNames, newContractDate;
        RangerStatus newStatus;

        System.out.println();
        id = errorControl.validateIDNumber(sc, "Ingrese la cedula de identidad: ");

        Criteria<ParkRanger> criteriaForParkRangers = parkRanger -> parkRanger.getIdentification().equals(id);
        ParkRanger auxParkRanger = gestorReserva.parkRangers.getElement(criteriaForParkRangers);

        if (auxParkRanger != null) {
            newFirstNames = changeRangerWords(auxParkRanger.names, "nombres");
            newLastNames = changeRangerWords(auxParkRanger.lastNames, "apellidos");
            newContractDate = changeContractDateRanger(auxParkRanger.dateOfHire.toString());
            newStatus = changeRangerStatus(auxParkRanger.status);

            gestorReserva.updateParkRanger(id, newFirstNames,
                    newLastNames, stringToLocalDateConverter(newContractDate),
                    newStatus);
        } else {
            System.err.println("No se ha encontrado al guardabosques con la cedula proporcionada.\n");
        }
    }

    public String changeRangerWords(String originalName, String categoria)
    {

        String message, newWord = null;
        int option;
        do {
            message = "¿Desea cambiar los " + categoria + "?\nSi (1) / No (2): ";
            option = errorControl.validateNumericInputInt(sc, message);

            switch (option) {
                case 1:
                    newWord = errorControl.validateTwoWords(sc, "Ingrese sus dos " + categoria + ": ", categoria);
                    break;
                case 2:
                    newWord = originalName;
                    break;
                default:
                    System.err.println("Error, opcion no encontrada.\n");
            }

        } while (option != 1 && option != 2);

        return newWord;
    }

    public String changeContractDateRanger(String originalDate)
    {
        String message, newDate = null;
        int option;
        do {
            message = "¿Desea cambiar la fecha de contrato?\nSi (1) / No (2): ";
            option = errorControl.validateNumericInputInt(sc, message);

            switch (option) {
                case 1:
                    newDate = errorControl.validateDate(sc, "Ingrese la fecha de contrato (en formato yyyy-MM-dd): \n");
                    break;
                case 2:
                    newDate = originalDate;
                    break;
                default:
                    System.err.println("Error, opcion no encontrada.\n");
            }

        } while (option != 1 && option != 2);

        return newDate;
    }

    public RangerStatus changeRangerStatus(RangerStatus originalStatus)
    {
        String message;
        RangerStatus newStatus = null;
        int option;
        do {
            message = "¿Desea cambiar el estado del guardabosque?\nSi (1) / No (2): ";
            option = errorControl.validateNumericInputInt(sc, message);

            switch (option) {
                case 1:
                    newStatus = parkRangerStatus();
                    break;
                case 2:
                    newStatus = originalStatus;
                    break;
                default:
                    System.err.println("Error: opcion no conocida.\n");
            }

        } while (option != 1 && option != 2);

        return newStatus;
    }

    public void areasMenu(NatureReserveManager gestorReserva)
    {
        int option;

        do {
            System.out.println();
            String message = """
                             === Incidentes ===
                             1. Crear area
                             2. Mostrar areas
                             3. Actualizar area
                             4. Eliminar area
                             5. Regresar
                             Seleccione una opcion: 
                             """;
            option = errorControl.validateNumericInputInt(sc, message);

            switch (option) {
                case 1:
                    createArea(gestorReserva);
                    break;
                case 2:
                    mostrarAreas(gestorReserva);
                    break;
                case 3:
                    modifyArea(gestorReserva);
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

    public void createArea(NatureReserveManager gestorReserva)
    {

        String codeArea, name, description;
        StateArea stateArea;

        System.out.println();
        codeArea = errorControl.validateStrings(sc, "Ingrese el codigo del area: ");

        Criteria<Area> criteriaByCodeArea = area -> area.getCodeArea().equals(codeArea);
        Area auxArea = gestorReserva.areas.getElement(criteriaByCodeArea);

        if (auxArea == null) {
            name = errorControl.validateStrings(sc, "Ingrese el nombre del area: ");
            description = errorControl.validateStrings(sc, "Ingrese la descripcion del area: ");
            stateArea = selectStatusArea();
            Area area = new RecreationalArea(codeArea, name, description, stateArea);
            gestorReserva.addArea(area);
        } else {
            System.err.println("El codigo del area a registrar ya se encuentra en uso.\n");
        }
    }

    public StateArea selectStatusArea()
    {
        String message;
        StateArea area = null;
        int option;
        do {
            message = "Accesibilidad del area: accesible (1), innacesible (2): ";
            option = errorControl.validateNumericInputInt(sc, message);

            switch (option) {
                case 1:
                    area = StateArea.accessible;
                    break;
                case 2:
                    area = StateArea.inaccessible;
                    break;
                default:
                    System.err.println("Error: opcion no conocida.\n");
            }

        } while (option != 1 && option != 2);

        return area;
    }

    public void mostrarAreas(NatureReserveManager gestorReserva)
    {

        Criteria<Area> criteriaForParkRangersBusy = area -> area.getStateArea() == (StateArea.accessible);
        Criteria<Area> criteriaForParkRangersFree = area -> area.getStateArea() == (StateArea.inaccessible);

        // ...
        List<Area> accesibleArea = gestorReserva.areas.getElements(criteriaForParkRangersBusy);
        List<Area> inaccessibleArea = gestorReserva.areas.getElements(criteriaForParkRangersFree);

        if (!accesibleArea.isEmpty()) {
            System.out.println("Areas accesibles: \n");
            for (Area area : accesibleArea) {
                System.out.println("Identificacion: " + area.codeArea + ", nombre: "
                        + area.name + ", descripcion: " + area.description + "\n");
            }
        } else {
            System.out.println("No hay areas accesibles.");
        }

        if (!inaccessibleArea.isEmpty()) {
            System.out.println("Areas innacesibles: \n");
            for (Area area : inaccessibleArea) {
                System.out.println("Identificacion: " + area.codeArea + ", nombre: "
                        + area.name + ", descripcion: " + area.description + "\n");
            }
        } else {
            System.out.println("\nNo hay areas inaccesible.");
        }

    }

    public void modifyArea(NatureReserveManager gestorReserva)
    {
        String codeArea, newName, newDescription;
        StateArea newState;

        System.out.println();
        codeArea = errorControl.validateStrings(sc, "Ingrese el codigo del area: ");

        Criteria<Area> criteriaForParkRangers = area -> area.getCodeArea().equals(codeArea);
        Area auxArea = gestorReserva.areas.getElement(criteriaForParkRangers);

        if (auxArea != null) {
            newName = newNameArea(auxArea.name);
            newDescription = newDescriptionArea(auxArea.description);
            newState = newStateArea(auxArea.stateArea);
            gestorReserva.updateArea(codeArea, newName, newDescription, newState);
        } else {
            System.err.println("No se ha encontrado el area con el codigo proporcionada.\n");
        }
    }

    public String newNameArea(String originalName)
    {
        String message, newName = null;
        int option;
        do {
            message = "¿Desea cambiar el nombre del area?\nSi (1) / No (2): ";
            option = errorControl.validateNumericInputInt(sc, message);

            switch (option) {
                case 1:
                    newName = errorControl.validateStrings(sc, "Ingrese el nombre del area: ");
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

    public String newDescriptionArea(String originalDescription)
    {
        String message, newDescription = null;
        int option;
        do {
            message = "¿Desea cambiar la descripcion del area?\nSi (1) / No (2): ";
            option = errorControl.validateNumericInputInt(sc, message);

            switch (option) {
                case 1:
                    newDescription = errorControl.validateStrings(sc, "Ingrese la descripcion del area: ");
                    break;
                case 2:
                    newDescription = originalDescription;
                    break;
                default:
                    System.err.println("Error, opcion no encontrada.\n");
            }

        } while (option != 1 && option != 2);

        return newDescription;
    }

    public StateArea newStateArea(StateArea originalState)
    {
        String message;
        StateArea newState = null;
        int option;
        do {
            message = "¿Desea cambiar el estado del area?\nSi (1) / No (2): ";
            option = errorControl.validateNumericInputInt(sc, message);

            switch (option) {
                case 1:
                    newState = selectStatusArea();
                    break;
                case 2:
                    newState = originalState;
                    break;
                default:
                    System.err.println("Error: opcion no conocida.\n");
            }

        } while (option != 1 && option != 2);

        return newState;
    }

    // --------------------------------------------------------------------------------------------------------------------------------
    // Informacion sobre los incidentes
    public void incidentMenu(NatureReserveManager gestorReserva)
    {
        int option;

        do {
            System.out.println();
            String message = """
                             === Incidentes ===
                             1. Crear incidente
                             2. Mostrar incidentes
                             3. Actualizar incidente
                             4. Eliminar incidente
                             5. Regresar
                             Seleccione una opcion: 
                             """;
            option = errorControl.validateNumericInputInt(sc, message);

            switch (option) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
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

}
