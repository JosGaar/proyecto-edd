package dataPack;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
                    areaMenu(reserveManage);
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
        int option;

        do {
            System.out.println();
            String message = """
                             === Guardaparques ===
                             1. Crear visitante
                             2. Mostrar visitantes
                             3. Actualizar visitante
                             4. Eliminar visitante
                             5. Regresar
                             Seleccione una opcion: 
                             """;
            option = errorControl.validateNumericInputInt(sc, message);

            switch (option) {
                case 1:
                    createVisitor(gestorReserva);
                    break;
                case 2:
                    showVisitor(gestorReserva);
                    break;
                case 3:
                    updateVisitor(gestorReserva);
                    break;
                case 4:
                    deleteVisitor(gestorReserva);
                    break;
                case 5:
                    System.out.println("Regresando...\n");
                    break;
                default:
                    System.err.println("Error, opcion no disponible.\n");
            }

        } while (option != 5);
    }

    public void createVisitor(NatureReserveManager gestorReserva)
    {
        String identification, address, phoneNumber, names, lastNames;
        VisitStatus status;

        System.out.println();
        identification = errorControl.validateIDNumber(sc, "Ingrese la cedula de identidad: ");

        Criteria<Visitor> criteriaForVisitor = visitor -> visitor.getIdentification().equals(identification);
        Visitor auxVisitor = gestorReserva.visitors.getElement(criteriaForVisitor);

        if (auxVisitor == null) {
            names = errorControl.validateTwoWords(sc, "Ingrese los dos nombres: ", "nombres");
            lastNames = errorControl.validateTwoWords(sc, "Ingrese dos apellidos: ", "apellidos");
            address = errorControl.validateStrings(sc, "Ingrese la direccion: ");
            phoneNumber = errorControl.validatePhoneNumber(sc, "Ingrese el número de telefono: ");
            status = statusVisitor();

            Visitor visitor = new Visitor(address, phoneNumber, status, identification, names, lastNames);
            if (gestorReserva.addVisitor(visitor)) {
                System.out.println("Visitante agregado exitosamente.");
            }
        } else {
            System.err.println("No se ha podido agregar el visitante: cedula ya existente.");
        }
    }

    public VisitStatus statusVisitor()
    {
        VisitStatus estado = null;
        int opcion;

        do {
            String message = "Seleccione un estado: activo (1), inactivo (2): ";
            opcion = errorControl.validateNumericInputInt(sc, message);

            switch (opcion) {
                case 1:
                    estado = VisitStatus.Active;
                    break;
                case 2:
                    estado = VisitStatus.Inactive;
                    break;
                default:
                    System.err.println("Seleccione un estado valido.\n");
            }

        } while (opcion != 1 && opcion != 2);

        return estado;
    }

    public void showVisitor(NatureReserveManager gestorReserva)
    {
        System.out.println();
        Criteria<Visitor> criteriaForVisitorActive = visitor -> visitor.getStatus() == (VisitStatus.Active);
        Criteria<Visitor> criteriaForVisitorInactive = visitor -> visitor.getStatus() == (VisitStatus.Inactive);

        List<Visitor> visitorsActive = gestorReserva.visitors.getElements(criteriaForVisitorActive);
        List<Visitor> visitorInactive = gestorReserva.visitors.getElements(criteriaForVisitorInactive);

        if (!visitorsActive.isEmpty()) {
            System.out.println("Visitantes activos: \n");
            for (Visitor visitor : visitorsActive) {
                System.out.println(visitor);
            }
        } else {
            System.out.println("No hay visitantes activos.");
        }

        if (!visitorInactive.isEmpty()) {
            System.out.println("\nVisitantes inactivos: \n");
            for (Visitor visitor : visitorInactive) {
                System.out.println(visitor);
            }
        } else {
            System.out.println("\nNo hay visitantes inactivos.");
        }

    }

    public void updateVisitor(NatureReserveManager gestorReserva)
    {
        String identification, newAddress, newPhoneNumber, newNames, newLastNames;
        VisitStatus newStatus;

        System.out.println();
        identification = errorControl.validateIDNumber(sc, "Ingrese la cedula de identidad: ");

        Criteria<Visitor> criteriaForVisitor = visitor -> visitor.getIdentification().equals(identification);
        Visitor auxVisitor = gestorReserva.visitors.getElement(criteriaForVisitor);

        if (auxVisitor != null) {
            newNames = changeVisitorWords(auxVisitor.names, "nombres");
            newLastNames = changeVisitorWords(auxVisitor.lastNames, "apellidos");
            newPhoneNumber = changeVisitorPhoneNumber(auxVisitor.getPhoneNumber());
            newAddress = changeVisitorAddress(auxVisitor.getAddress());
            newStatus = changeVisitorStatus(auxVisitor.getStatus());

            if (gestorReserva.updateVisitor(newAddress, newPhoneNumber, newStatus, identification,
                    newNames, newLastNames)) {
                System.out.println("Se ha actualizado correctamente el visitante.");
            }
        } else {
            System.err.println("Visitante no encontrado.");
        }
    }

    public String changeVisitorWords(String originalName, String categoria)
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

    public String changeVisitorPhoneNumber(String originalPhoneNumber)
    {
        String message, newWord = null;
        int option;
        do {
            message = "¿Desea cambiar el numero de telefono? Si (1) / No (2): ";
            option = errorControl.validateNumericInputInt(sc, message);

            switch (option) {
                case 1:
                    newWord = errorControl.validatePhoneNumber(sc, "Ingrese el número de telefono: ");
                    break;
                case 2:
                    newWord = originalPhoneNumber;
                    break;
                default:
                    System.err.println("Error, opcion no encontrada.\n");
            }

        } while (option != 1 && option != 2);

        return newWord;
    }

    public String changeVisitorAddress(String originalAddress)
    {
        String message, newWord = null;
        int option;
        do {
            message = "¿Desea cambiar la direccion? Si (1) / No (2): ";
            option = errorControl.validateNumericInputInt(sc, message);

            switch (option) {
                case 1:
                    newWord = errorControl.validateStrings(sc, "Ingrese la direccion: ");
                    break;
                case 2:
                    newWord = originalAddress;
                    break;
                default:
                    System.err.println("Error, opcion no encontrada.\n");
            }

        } while (option != 1 && option != 2);

        return newWord;
    }

    public VisitStatus changeVisitorStatus(VisitStatus originalStatus)
    {
        String message;
        VisitStatus newStatus = null;
        int option;
        do {
            message = "¿Desea cambiar el estado de visita del visitante? Si (1) / No (2): ";
            option = errorControl.validateNumericInputInt(sc, message);

            switch (option) {
                case 1:
                    newStatus = statusVisitor();
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

    // Se lo puede eliminar si no tiene visitas.
    public void deleteVisitor(NatureReserveManager gestorReserva)
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
                    showParkRangers(gestorReserva);
                    break;
                case 3:
                    updateRanger(gestorReserva);
                    break;
                case 4:
                    deleteRanger(gestorReserva);
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
        String id, firstNames, lastNames;
        LocalDate contractDate;
        RangerStatus status;

        System.out.println();
        id = errorControl.validateIDNumber(sc, "Ingrese la cedula de identidad: ");

        Criteria<ParkRanger> criteriaForParkRangers = parkRanger -> parkRanger.getIdentification().equals(id);
        ParkRanger auxParkRanger = gestorReserva.parkRangers.getElement(criteriaForParkRangers);

        if (auxParkRanger == null) {
            contractDate = errorControl.validateLocalDate(sc, "Ingrese la fecha de contrato (en formato yyyy-MM-dd): ");
            firstNames = errorControl.validateTwoWords(sc, "Ingrese sus dos nombres: ", "nombres");
            lastNames = errorControl.validateTwoWords(sc, "Ingrese sus dos apellidos: ", "apellidos");
            status = parkRangerStatus();

            ParkRanger parkRanger = new ParkRanger(contractDate, id,
                    firstNames, lastNames, status);

            if (gestorReserva.addParkRanger(parkRanger)) {
                System.out.println("Guardaparques añadio exitosamente.");
            }
        } else {
            System.err.println("No se ha podido agregar al guardaparques: cedula ya existente.");
        }
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
        System.out.println();
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
            System.out.println("Guardabosques desocupados: \n");
            for (ParkRanger parkRanger : freeRangers) {
                System.out.println("Identificacion: " + parkRanger.identification + parkRanger.fullname() + "\n");
            }
        } else {
            System.out.println("No hay guardabosques desocupados.");
        }
    }

    // Pedir cedula de identidad
    // Preguntar si desea cambiar el nombre
    // Preguntar si desea cambiar los apelldos
    // Preguntar si desea cambiar la fecha de contrato
    // Preguntar si desea cambiar el estado
    public void updateRanger(NatureReserveManager gestorReserva)
    {
        String id, newFirstNames, newLastNames;
        LocalDate newContractDate;
        RangerStatus newStatus;

        System.out.println();
        id = errorControl.validateIDNumber(sc, "Ingrese la cedula de identidad: ");

        Criteria<ParkRanger> criteriaForParkRangers = parkRanger -> parkRanger.getIdentification().equals(id);
        ParkRanger auxParkRanger = gestorReserva.parkRangers.getElement(criteriaForParkRangers);

        if (auxParkRanger != null) {
            newFirstNames = changeRangerWords(auxParkRanger.names, "nombres");
            newLastNames = changeRangerWords(auxParkRanger.lastNames, "apellidos");
            newContractDate = changeContractDateRanger(auxParkRanger.dateOfHire);
            newStatus = changeRangerStatus(auxParkRanger.status);

            if (gestorReserva.updateParkRanger(id, newFirstNames,
                    newLastNames, newContractDate,
                    auxParkRanger.status)) {
                System.out.println("Se ha actualizado la informacion del guardabosques correctamente.");
            }
        } else {
            System.err.println("No se ha encontrado al guardabosques con la cedula proporcionada");
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

    public LocalDate changeContractDateRanger(LocalDate originalDate)
    {
        String message;
        LocalDate newDate = null;
        int option;
        do {
            message = "¿Desea cambiar la fecha de contrato?\nSi (1) / No (2): ";
            option = errorControl.validateNumericInputInt(sc, message);

            switch (option) {
                case 1:
                    newDate = errorControl.validateLocalDate(sc, "Ingrese la fecha de contrato (en formato yyyy-MM-dd): ");
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

    public void deleteRanger(NatureReserveManager gestorReserva)
    {
        String id;
        id = errorControl.validateStrings(sc, "\nIngrese la cedula de identidad del guardabosques a eliminar: ");

        Criteria<ParkRanger> criteriaForParkRangers = parkRanger -> parkRanger.getIdentification().equals(id);
        ParkRanger auxParkRanger = gestorReserva.parkRangers.getElement(criteriaForParkRangers);

        if (auxParkRanger != null) {
            if (auxParkRanger.status == RangerStatus.free) { // Desocupado --> eliminar
                if (gestorReserva.removeParkRanger(id)) {
                    System.out.println("Guardaparques eliminado exitosamente.");
                }
            } else {
                System.err.println("No se puede eliminar el guardabosques debido a que se encuentra ocupado.");
            }
        } else {
            System.err.println("No se ha encontrado el guardabosques con el id proporcionado.");
        }

    }

    // --- Areas
    public void areaMenu(NatureReserveManager gestorReserva)
    {
        int option;

        do {
            System.out.println();
            String message = """
                             === Areas ===
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
                    showAreas(gestorReserva);
                    break;
                case 3:
                    updateArea(gestorReserva);
                    break;
                case 4:
                    deleteArea(gestorReserva);
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
            stateArea = selectStatusArea(); // No lo utilizo en ningun lado
            Area area = new RecreationalArea(codeArea, name, description, stateArea);
            if (gestorReserva.addArea(area)) {
                System.out.println("El area se ha añadido exitosamente.");
            }
        } else {
            System.err.println("El codigo del area a registrar ya se encuentra en uso.");
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

    public void showAreas(NatureReserveManager gestorReserva)
    {

        System.out.println();
        Criteria<Area> criteriaForParkRangersBusy = area -> area.getStateArea() == (StateArea.accessible);
        Criteria<Area> criteriaForParkRangersFree = area -> area.getStateArea() == (StateArea.inaccessible);

        List<Area> accesibleArea = gestorReserva.areas.getElements(criteriaForParkRangersBusy);
        List<Area> inaccessibleArea = gestorReserva.areas.getElements(criteriaForParkRangersFree);

        if (!accesibleArea.isEmpty()) {
            System.out.println("Areas accesibles: \n");
            for (Area area : accesibleArea) {
                System.out.println(area);
            }
        } else {
            System.out.println("No hay areas accesibles.");
        }

        if (!inaccessibleArea.isEmpty()) {
            System.out.println("\nAreas innacesibles: \n");
            for (Area area : inaccessibleArea) {
                System.out.print(area + "\n");
            }
        } else {
            System.out.println("No hay areas inaccesible.");
        }

    }

    public void updateArea(NatureReserveManager gestorReserva)
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
            if (gestorReserva.updateArea(codeArea, newName, newDescription, newState)) {
                System.out.println("Se ha actualizado la informacion del area correctamente.");
            }
        } else {
            System.err.println("No se ha encontrado el area con el codigo proporcionada.");
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

    // Dos formas
    // 1. Utilizar la referencia a incidents de la clase Area
    // 2. Buscar entre todas los incidentes cual tiene el Area con id dado por el usuario
    public void deleteArea(NatureReserveManager gestorReserva)
    {
        String codeArea;
        codeArea = errorControl.validateStrings(sc, "\nIngrese el codigo del area a eliminar: ");

        Criteria<Area> criteriaForParkRangers = area -> area.getCodeArea().equals(codeArea);
        Area auxArea = gestorReserva.areas.getElement(criteriaForParkRangers);

        if (auxArea != null) {
            if (auxArea.incidents.isEmpty()) {
                if (gestorReserva.removeArea(codeArea)) {
                    System.out.println("Area eliminiada exitosamente.");
                }
            } else {
                System.err.println("No se ha podido eliminar el area, puesto que esta relacionado con alguna incidencia.");
            }
        } else {
            System.err.println("No se ha encontrado el area a eliminar.");
        }

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
                    createIndicent(gestorReserva);
                    break;
                case 2:
                    showIncidents(gestorReserva);
                    break;
                case 3:
                    updateIncidents(gestorReserva);
                    break;
                case 4:
                    deleteIncident(gestorReserva);
                    break;
                case 5:
                    System.out.println("Regresando...\n");
                    break;
                default:
                    System.err.println("Error, opcion no disponible.\n");
            }

        } while (option != 5);

    }

    public void createIndicent(NatureReserveManager gestorReserva)
    {
        String incidentCode, description, annotations;
        LocalDateTime dateTimeReport, dateTimeAttention;
        IncidentStatus incidentStatus;
        Area selectArea;

        Criteria<Area> criteriaByStatusAreaAccesible = area -> area.getStateArea() == StateArea.accessible;
        Criteria<Area> criteriaByStatusAreaInaccessible = area -> area.getStateArea() == StateArea.inaccessible;
        Criteria<ParkRanger> criteria = parkRanger -> parkRanger.status == RangerStatus.free;

        List<Area> areasAccesible = gestorReserva.areas.getElements(criteriaByStatusAreaAccesible);
        List<Area> areasInaccesible = gestorReserva.areas.getElements(criteriaByStatusAreaInaccessible);
        List<ParkRanger> parkRangers = gestorReserva.parkRangers.getElements(criteria);

        if ((!areasAccesible.isEmpty() || !areasInaccesible.isEmpty()) && !parkRangers.isEmpty()) {

            System.out.println();
            incidentCode = errorControl.validateStrings(sc, "Ingrese el codigo del incidente: ");

            Criteria<Incident> criteriaByCodeArea = incident -> incident.getIncidentCode().equals(incidentCode);
            Incident auxIncidenet = gestorReserva.incidents.getElement(criteriaByCodeArea);
            ParkRanger agentSupport;

            if (auxIncidenet == null) {
                description = errorControl.validateStrings(sc, "Ingrese la descripcion del incidente: ");
                annotations = errorControl.validateStrings(sc, "Ingrese las anotaciones del incidente: ");
                agentSupport = selectAgentSupport(gestorReserva);
                dateTimeReport = errorControl.validateLocalDateTime(sc, "Ingrese la fecha de reporte: ");
                dateTimeAttention = errorControl.validateLocalDateTime(sc, "Ingrese la fecha de atención: ");
                incidentStatus = selectIndicentStatus();
                selectArea = selectAreaIncident(gestorReserva);
                agentSupport.status = RangerStatus.busy; // El agente ya no esta desocupado, puesto que se la sido asignado una incidencia.

                Incident incidente = new Incident(incidentCode, description, dateTimeReport, dateTimeAttention,
                        agentSupport, incidentStatus, annotations, selectArea);

                selectArea.incidents.insertElement(incidente);

                if (gestorReserva.addIncident(incidente)) {
                    System.out.println("Se ha añadido el incidente correctamente.\n");
                } else {
                    System.err.println("No se ha podido añadir el incidente correctamente.\n");
                }

            } else {
                System.err.println("El codigo del area a registrar ya se encuentra en uso.\n");
            }
        } else {
            System.err.println("Por favor, registre las areas o guardabosques para poder crear un incidente.");
        }

    }

    public IncidentStatus selectIndicentStatus()
    {
        String message;
        IncidentStatus status = null;
        int option;
        do {
            message = "Estado del incidente: en proceso (1), pendiente (2), resuelta (3): ";
            option = errorControl.validateNumericInputInt(sc, message);

            switch (option) {
                case 1:
                    status = IncidentStatus.inProcess;
                    break;
                case 2:
                    status = IncidentStatus.pending;
                    break;
                case 3:
                    status = IncidentStatus.resolved;
                    break;
                default:
                    System.err.println("Error: opcion no conocida.\n");
            }

        } while (option != 1 && option != 2 && option != 3);

        return status;
    }

    public ParkRanger selectAgentSupport(NatureReserveManager gestorReserva)
    {

        Criteria<ParkRanger> criteria = parkRanger -> parkRanger.status == RangerStatus.free;
        List<ParkRanger> parkRangers = gestorReserva.parkRangers.getElements(criteria);

        System.out.println("\nGuardabosques disponibles:");
        for (int i = 0; i < parkRangers.size(); i++) {
            System.out.println((i + 1) + ". " + parkRangers.get(i).getNames() + ", " + parkRangers.get(i).lastNames + "\n");
        }
        int selection;
        do {
            selection = errorControl.validateNumericInputInt(sc, "Seleccione un número para elegir al guardabosques: ");
            if (selection < 1 || selection > parkRangers.size()) {
                System.err.println("Ingrese un número valido.\n");
            }
        } while (selection < 1 || selection > parkRangers.size());

        return parkRangers.get(selection - 1);
    }

    public Area selectAreaIncident(NatureReserveManager gestorReserva)
    {
        Criteria<Area> criteriaByStatusAreaAccesible = area -> area.getStateArea() == StateArea.accessible;
        Criteria<Area> criteriaByStatusAreaInaccessible = area -> area.getStateArea() == StateArea.inaccessible;

        List<Area> areasAccesible = gestorReserva.areas.getElements(criteriaByStatusAreaAccesible);
        List<Area> areasInaccesible = gestorReserva.areas.getElements(criteriaByStatusAreaInaccessible);

        if (!areasAccesible.isEmpty()) {
            System.out.println("\nÁreas accesibles disponibles:");
            for (int i = 0; i < areasAccesible.size(); i++) {
                System.out.println((i + 1) + ". " + areasAccesible.get(i).getName());
            }
        } else {
            System.out.println("\nNo hay áreas accesibles disponibles.");
        }

        if (!areasInaccesible.isEmpty()) {
            System.out.println("\nÁreas inaccesibles disponibles:");
            for (int i = 0; i < areasInaccesible.size(); i++) {
                System.out.println((i + 1) + ". " + areasInaccesible.get(i).getName());
            }
        } else {
            System.out.println("\nNo hay areas innacesibles disponibles");
        }

        int selection;
        boolean isAccessible;

        do {
            selection = errorControl.validateNumericInputInt(sc, "Seleccione el número de área: ");
            System.out.println("¿Es accesible (s/n)?");
            String response = sc.nextLine();
            isAccessible = response.equalsIgnoreCase("s");
        } while ((isAccessible && (selection < 1 || selection > areasAccesible.size()))
                || (!isAccessible && (selection < 1 || selection > areasInaccesible.size())));

        if (isAccessible) {
            return areasAccesible.get(selection - 1);
        } else {
            return areasInaccesible.get(selection - 1);
        }
    }

    public void showIncidents(NatureReserveManager gestorReserva)
    {
        Criteria<Incident> criteria1 = incident -> incident.incidentStatus == (IncidentStatus.inProcess);
        Criteria<Incident> criteria2 = incident -> incident.incidentStatus == (IncidentStatus.pending);
        Criteria<Incident> criteria3 = incident -> incident.incidentStatus == (IncidentStatus.resolved);

        List<Incident> listCriteria1 = gestorReserva.incidents.getElements(criteria1);
        List<Incident> listCriteria2 = gestorReserva.incidents.getElements(criteria2);
        List<Incident> listCriteria3 = gestorReserva.incidents.getElements(criteria3);

        if (!listCriteria1.isEmpty()) {
            System.out.println("'\nIncidentes en proceso: ");
            for (Incident incident : listCriteria1) {
                System.out.println(incident);
            }
        } else {
            System.out.println("\nNo hay incidentes en proceso.");
        }

        if (!listCriteria2.isEmpty()) {
            System.out.println("\nIncidentes pendientes: ");
            for (Incident incident : listCriteria2) {
                System.out.println(incident);
            }
        } else {
            System.out.println("\nNo hay incidentes pendientes.");
        }

        if (!listCriteria3.isEmpty()) {
            System.out.println("Incidentes resueltos: ");
            for (Incident incident : listCriteria3) {
                System.out.println(incident);
            }
        } else {
            System.out.println("\nNo hay incidentes resueltos.");
        }
    }

    public void updateIncidents(NatureReserveManager gestorReserva)
    {
        String incidentCode, newDescription, newAnnotations;
        ParkRanger newAgentSupport;
        Area newArea;
        IncidentStatus newIncidentStatus;
        LocalDateTime newDateTimeReport, newDateTimeAttention;

        incidentCode = errorControl.validateStrings(sc, "\nIngrese el código del incide a actualizar: ");
        Criteria<Incident> criteria1 = incident -> incident.incidentCode.equals(incidentCode);
        Incident auxIncident = gestorReserva.incidents.getElement(criteria1);

        if (auxIncident != null) {
            newDescription = changeDescriptionIncident(auxIncident.description);
            newAnnotations = changeAnnotations(auxIncident.annotations);
            newAgentSupport = changeAgentSupport(auxIncident.agentSupport, gestorReserva);
            newDateTimeReport = changeDateTimeReport(auxIncident.dateTimeReport);
            newDateTimeAttention = changeDateTimeAttetion(auxIncident.dateTimeAttention);
            newIncidentStatus = changeIncidentStatus(auxIncident.incidentStatus);
            newArea = changeArea(auxIncident.area, gestorReserva);

            if (gestorReserva.updateIncident(incidentCode, newDescription, newDateTimeReport, newDateTimeAttention,
                    newAgentSupport, newIncidentStatus, newAnnotations, newArea)) {
                System.out.println("Se ha actualizado correctamente el incidente");
            } else {
                System.err.println("No se ha podido actualizar el incidente");
            }
        }

    }

    public String changeDescriptionIncident(String originalDescription)
    {
        String message, newDescription = null;
        int option;
        do {
            message = "¿Desea cambiar la descripcion del incidente?\nSi (1) / No (2): ";
            option = errorControl.validateNumericInputInt(sc, message);

            switch (option) {
                case 1:
                    newDescription = errorControl.validateStrings(sc, "Ingrese la nueva descripcion del incidente\n");
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

    public LocalDateTime changeDateTimeReport(LocalDateTime originalDate)
    {
        String message;
        LocalDateTime newTime = null;
        int option;
        do {
            message = "¿Desea cambiar el tiempo de reporte del incidente?\nSi (1) / No (2): ";
            option = errorControl.validateNumericInputInt(sc, message);

            switch (option) {
                case 1:
                    newTime = errorControl.validateLocalDateTime(sc, "Ingrese el nuevo tiempo de reporte del incidente\n");
                    break;
                case 2:
                    newTime = originalDate;
                    break;
                default:
                    System.err.println("Error, opcion no encontrada.\n");
            }

        } while (option != 1 && option != 2);

        return newTime;
    }

    public LocalDateTime changeDateTimeAttetion(LocalDateTime originalDate)
    {
        String message;
        LocalDateTime newTime = null;
        int option;
        do {
            message = "¿Desea cambiar el tiempo de atencion del incidente?\nSi (1) / No (2): ";
            option = errorControl.validateNumericInputInt(sc, message);

            switch (option) {
                case 1:
                    newTime = errorControl.validateLocalDateTime(sc, "Ingrese el nuevo tiempo de atención del incidente\n");
                    break;
                case 2:
                    newTime = originalDate;
                    break;
                default:
                    System.err.println("Error, opcion no encontrada.\n");
            }

        } while (option != 1 && option != 2);

        return newTime;
    }

    public IncidentStatus changeIncidentStatus(IncidentStatus originalStatus)
    {
        String message;
        IncidentStatus statusIncident = null;
        int option;
        do {
            message = "¿Desea cambiar el estado del incidente?\nSi (1) / No (2): ";
            option = errorControl.validateNumericInputInt(sc, message);

            switch (option) {
                case 1:
                    statusIncident = selectIndicentStatus();
                    break;
                case 2:
                    statusIncident = originalStatus;
                    break;
                default:
                    System.err.println("Error, opcion no encontrada.\n");
            }

        } while (option != 1 && option != 2);

        return statusIncident;
    }

    public String changeAnnotations(String originalAnnotation)
    {
        String message;
        String newAnottations = null;
        int option;
        do {
            message = "¿Desea cambiar las anotaciones del incidente?\nSi (1) / No (2): ";
            option = errorControl.validateNumericInputInt(sc, message);

            switch (option) {
                case 1:
                    newAnottations = errorControl.validateStrings(sc, "Ingrese las nuevas anotaciones del incidente:\n");
                    break;
                case 2:
                    newAnottations = originalAnnotation;
                    break;
                default:
                    System.err.println("Error, opcion no encontrada.\n");
            }

        } while (option != 1 && option != 2);

        return newAnottations;
    }

    public ParkRanger changeAgentSupport(ParkRanger originalParkRanger, NatureReserveManager gestorReserva)
    {
        String message;
        ParkRanger newParkRanger = null;
        int option;
        do {
            message = "¿Desea cambiar el agente de soporte del incidente?\nSi (1) / No (2): ";
            option = errorControl.validateNumericInputInt(sc, message);

            switch (option) {
                case 1:
                    newParkRanger = selectAgentSupport(gestorReserva);
                    break;
                case 2:
                    newParkRanger = originalParkRanger;
                    break;
                default:
                    System.err.println("Error, opcion no encontrada.\n");
            }

        } while (option != 1 && option != 2);

        return newParkRanger;
    }

    public Area changeArea(Area originalArea, NatureReserveManager gestorReserva)
    {
        String message;
        Area newArea = null;
        int option;
        do {
            message = "¿Desea cambiar el area del incidente?\nSi (1) / No (2): ";
            option = errorControl.validateNumericInputInt(sc, message);

            switch (option) {
                case 1:
                    newArea = selectAreaIncident(gestorReserva);
                    break;
                case 2:
                    newArea = originalArea;
                    break;
                default:
                    System.err.println("Error, opcion no encontrada.\n");
            }

        } while (option != 1 && option != 2);

        return newArea;
    }

    // El incidete se puede borrar, no importa si tiene un area o un guardaparques
    public void deleteIncident(NatureReserveManager gestorReserva)
    {
        String incidentCode;
        incidentCode = errorControl.validateStrings(sc, "Ingrese el código del incide a eliminar: ");

        Criteria<Incident> criteria1 = incident -> incident.incidentCode.equals(incidentCode);
        Incident auxIncident = gestorReserva.incidents.getElement(criteria1);

        if (auxIncident != null && auxIncident.incidentStatus != IncidentStatus.inProcess && auxIncident.incidentStatus != IncidentStatus.pending) {
            auxIncident.agentSupport.status = RangerStatus.free; // El guardabosques ahora esta desocupado
            gestorReserva.removeIncident(incidentCode);
            auxIncident.area.incidents.removeElement(auxIncident); // El area de "esta" incidencia, debe eliminar a la incidencia que no existe.
            System.out.println("Incidente eliminado ocorrectamente.");
        } else {
            System.err.println("No se ha podido eliminar el incidente: incidente no encontrado o estado no concluido.");
        }
    }
}
