package dataPack;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
                             2. Gestionar visitas
                             3. Gestionar guardaparques
                             4. Gestionar areas
                             5. Gestionar incidentes
                             6. Gestionar atenciones de incidentes
                             7. Salir
                             Seleccione una opcion: 
                             """;
            option = errorControl.validateNumericInputInt(sc, message);

            switch (option) {
                case 1:
                    visitorMenu(reserveManage);
                    break;
                case 2:
                    visitMenu(reserveManage);
                    break;
                case 3:
                    parkRangersMenu(reserveManage);
                    break;
                case 4:
                    areaMenu(reserveManage);
                    break;
                case 5:
                    incidentMenu(reserveManage);
                    break;
                case 6:
                    manageIncidentMenu(reserveManage);
                    break;
                case 7:
                    System.out.println("\nSaliendo...\n");
                    break;
                default:
                    System.err.println("Error, opcion no disponible.\n");
            }

        } while (option != 7);
    }

    public void visitorMenu(NatureReserveManager gestorReserva)
    {
        int option;

        do {
            System.out.println();
            String message = """
                             === Visitantes ===
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

        System.out.println();
        identification = errorControl.validateIDNumber(sc, "Ingrese la cedula de identidad: ");

        Criteria<Visitor> criteriaForVisitor = visitor -> visitor.getIdentification().equals(identification);
        Visitor auxVisitor = gestorReserva.visitors.getElement(criteriaForVisitor);

        if (auxVisitor == null) {
            names = errorControl.validateTwoWords(sc, "Ingrese los dos nombres: ", "nombres");
            lastNames = errorControl.validateTwoWords(sc, "Ingrese dos apellidos: ", "apellidos");
            address = errorControl.validateStrings(sc, "Ingrese la direccion: ");
            phoneNumber = errorControl.validatePhoneNumber(sc, "Ingrese el número de telefono: ");

            Visitor visitor = new Visitor(address, phoneNumber, identification, names, lastNames);
            if (gestorReserva.addVisitor(visitor)) {
                System.out.println("Visitante agregado exitosamente.");
            }
        } else {
            System.err.println("No se ha podido agregar el visitante: cedula ya existente.");
        }
    }

    public void showVisitor(NatureReserveManager gestorReserva)
    {
        List<Visitor> visitors = gestorReserva.visitors.getAllElementList();

        if (!visitors.isEmpty()) {
            for (Visitor v : visitors) {
                System.out.println(v);
            }
        } else {
            System.out.println("No se han registrado visitantes por el momento.");
        }
    }

    public void updateVisitor(NatureReserveManager gestorReserva)
    {
        String identification, newAddress, newPhoneNumber, newNames, newLastNames;

        System.out.println();
        identification = errorControl.validateIDNumber(sc, "Ingrese la cedula de identidad: ");

        Criteria<Visitor> criteriaForVisitor = visitor -> visitor.getIdentification().equals(identification);
        Visitor auxVisitor = gestorReserva.visitors.getElement(criteriaForVisitor);

        if (auxVisitor != null) {
            newNames = changeVisitorWords(auxVisitor.names, "nombres");
            newLastNames = changeVisitorWords(auxVisitor.lastNames, "apellidos");
            newPhoneNumber = changeVisitorPhoneNumber(auxVisitor.getPhoneNumber());
            newAddress = changeVisitorAddress(auxVisitor.getAddress());

            if (gestorReserva.updateVisitor(newAddress, newPhoneNumber, identification,
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

    // Se lo puede eliminar si no tiene visitas.
    public void deleteVisitor(NatureReserveManager gestorReserva)
    {
        String identification;
        identification = errorControl.validateStrings(sc, "Ingrese la identificación del visitante a eliminar: ");

        Criteria<Visitor> criteriaForVisitor = visitor -> visitor.getIdentification().equals(identification);
        Visitor auxVisitor = gestorReserva.visitors.getElement(criteriaForVisitor);

        if (auxVisitor != null) {
            if (gestorReserva.removeVisitor(identification)) {
                System.out.println("Visitante eliminado exitosamente.");
            } else {
                System.out.println("No se ha podido eliminar al visitante.");
            }
        } else {
            System.out.println("No se puede borrar un visitante con una visita activa.");

        }

    }

    // --------------------------------------------------------------------------------------------------------------------------------
    // Información sobre las visitas
    public void visitMenu(NatureReserveManager gestorReserva)
    {
        int option;

        do {
            System.out.println();
            String message = """
                             === Visitas ===
                             1. Crear visita
                             2. Mostrar visitas
                             3. Actualizar visita
                             4. Eliminar visita
                             5. Registrar salida visita
                             6. Regresar
                             Seleccione una opcion: 
                             """;
            option = errorControl.validateNumericInputInt(sc, message);

            switch (option) {
                case 1:
                    createVisit(gestorReserva);
                    break;
                case 2:
                    showVisit(gestorReserva);
                    break;
                case 3:
                    updateVisit(gestorReserva);
                    break;
                case 4:
                    deleteVisit(gestorReserva);
                    break;
                case 5:
                    recordVisitsExit(gestorReserva);
                    break;
                case 6:
                    System.out.println("Regresando...\n");
                    break;
                default:
                    System.err.println("Error, opcion no disponible.\n");
            }

        } while (option != 6);
    }

    public void createVisit(NatureReserveManager gestorReserva)
    {
        if (!gestorReserva.visitors.isEmpty()) {
            String codeVisit;
            Person person;
            LocalDateTime dateEntry;
            int numberVisitor;
            List<Visitor> visitors;

            codeVisit = errorControl.validateStrings(sc, "Ingrese el código de la visita: ");
            Criteria<Visit> criteriaforVisit = visit -> visit.getCodeVisit().equals(codeVisit);
            Visit auxVisit = gestorReserva.visits.getElement(criteriaforVisit);

            if (auxVisit == null) {
                // Traer a todos los visitantes y que el usuario seleccione
                do {
                    System.out.println("Seleccione algun visitante de los que se le muestra a continuación:");
                    visitors = gestorReserva.visitors.getAllElementList();
                    for (int i = 0; i < visitors.size(); i++) {
                        System.out.println((i + 1) + " nombres: " + visitors.get(i).names + ", apellidos: " + visitors.get(i).lastNames);
                    }

                    numberVisitor = errorControl.validateNumericInputInt(sc, "Su opción es: ");

                    if (numberVisitor <= 0 || numberVisitor > visitors.size()) {
                        System.err.println("\nIngrese un número en el intervalo mostrado.");
                    }

                } while (numberVisitor <= 0 || numberVisitor > visitors.size());

                person = gestorReserva.visitors.getElementAtIndex(numberVisitor - 1);
                dateEntry = errorControl.validateLocalDateTime(sc, "Ingrese la fecha de entrada (yyyy-MM-dd HH:mm:ss): ");

                Visit visit = new Visit(codeVisit, person, VisitStatus.Active, dateEntry, Visibility.visible);

                if (gestorReserva.addVisit(visit)) {
                    ((Visitor) person).getVisits().insertElement(visit);
                    System.out.println("Se ha añadido exitosamente la visita.");
                } else {
                    System.out.println("Ya existe una visita con el visitante que selecciono.");
                }
            } else {
                System.out.println("La visita con el código dado, ya existe.");
            }

        } else {
            System.out.println("No puede generar una visita si no tiene visitantes.");
        }
    }

    public VisitStatus visitStatus()
    {
        VisitStatus estado = null;
        int opcion;

        do {
            String message = "Seleccione un estado: activa (1), inactiva (2): ";
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

    public void showVisit(NatureReserveManager gestorReserva)
    {
        Criteria<Visit> criteriaForVisit = visit -> visit.getVisibility() == Visibility.visible;
        List<Visit> auxVisits = gestorReserva.visits.getElements(criteriaForVisit);

        if (!auxVisits.isEmpty()) {
            for (Visit v : auxVisits) {
                System.out.println(v);
            }
        } else {
            System.out.println("No se han registrado visitas por el momento.");
        }
    }

    public void updateVisit(NatureReserveManager gestorReserva)
    {
        LocalDateTime newDateEntry;
        String codeVisit;

        codeVisit = errorControl.validateStrings(sc, "Ingrese el código de la visita: ");

        Criteria<Visit> criteriaforVisit = visit -> visit.getCodeVisit().equals(codeVisit);
        Visit auxVisit = gestorReserva.visits.getElement(criteriaforVisit);

        if (auxVisit != null) {
            newDateEntry = updateDateEntry(auxVisit.getEntryDate());

            if (gestorReserva.updateVisit(codeVisit, auxVisit.getVisitor(), auxVisit.getStatus(), newDateEntry, null)) {
                System.out.println("Se ha actualizado la visita correctamente.");
            } else {
                System.out.println("No se ha podido actualizar la visita.");
            }
        }
    }

    public VisitStatus updateStatusVisit(VisitStatus originalStatus)
    {
        String message;
        VisitStatus newStatus = null;
        int option;
        do {
            message = "¿Desea cambiar el estado de la visita?\nSi (1) / No (2): ";
            option = errorControl.validateNumericInputInt(sc, message);

            switch (option) {
                case 1:
                    newStatus = visitStatus();
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

    public LocalDateTime updateDateEntry(LocalDateTime originalDate)
    {
        String message;
        LocalDateTime newDate = null;
        int option;
        do {
            message = "¿Desea cambiar la fecha de la visita?\nSi (1) / No (2): ";
            option = errorControl.validateNumericInputInt(sc, message);

            switch (option) {
                case 1:
                    newDate = errorControl.validateLocalDateTime(sc, "Ingrese la fecha (yyyy-MM-dd HH:mm:ss): ");
                    break;
                case 2:
                    newDate = originalDate;
                    break;
                default:
                    System.err.println("Error: opcion no conocida.\n");
            }

        } while (option != 1 && option != 2);

        return newDate;
    }

    public void deleteVisit(NatureReserveManager gestorReserva)
    {
        String codeVisit;
        codeVisit = errorControl.validateStrings(sc, "Ingrese el código de la visita: ");

        Criteria<Visit> criteriaforVisit = visit -> visit.getCodeVisit().equals(codeVisit);
        Visit auxVisit = gestorReserva.visits.getElement(criteriaforVisit);

        // Eliminar visita solo si no esta activa.
        if (auxVisit != null && auxVisit.getStatus() == VisitStatus.Inactive) {
            auxVisit.setVisibility(Visibility.hidden);
        }

    }

    // Si elimino la visita, es porque el visitante se fue de esta.
    // Para este caso, se cambiara el estado de la visita a inactivo.
    public void recordVisitsExit(NatureReserveManager gestorReserva)
    {
        String codeVisit;
        LocalDateTime exitDate;
        codeVisit = errorControl.validateStrings(sc, "Ingrese el código de la visita: ");

        Criteria<Visit> criteriaforVisit = visit -> visit.getCodeVisit().equals(codeVisit);
        Visit auxVisit = gestorReserva.visits.getElement(criteriaforVisit);

        if (auxVisit != null) {
            exitDate = errorControl.validateLocalDateTime(sc, "Ingrese la fecha de salida (yyyy-MM-dd HH:mm:ss): ");
            auxVisit.setExitDate(exitDate);
            auxVisit.setStatus(VisitStatus.Inactive);
            System.out.println("La visita ha sido registrada para su salida.");
        } else {
            System.out.println("No se ha encontrado la visita con el codigo proporcionado");
        }
    }

    // --------------------------------------------------------------------------------------------------------------------------------
    // Información sobre guardaparques
    public void parkRangersMenu(NatureReserveManager gestorReserva)
    {
        int option;

        do {
            System.out.println();
            String message = """
                             === Guardabosques ===
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
            // status = parkRangerStatus();

            ParkRanger parkRanger = new ParkRanger(contractDate, id,
                    firstNames, lastNames, RangerStatus.free, Visibility.visible);

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
        Criteria<ParkRanger> criteriaForParkRangers = parkRanger -> parkRanger.getVisibility() == Visibility.visible;
        List<ParkRanger> auxParkRangers = gestorReserva.parkRangers.getElements(criteriaForParkRangers);

        if (!auxParkRangers.isEmpty()) {
            for (ParkRanger pk : auxParkRangers) {
                System.out.println(pk);
            }
        } else {
            System.out.println("No se han encontrado guardabosques.");
        }
    }

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
            // newStatus = changeRangerStatus(auxParkRanger.status);

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
            // stateArea = selectStatusArea(); // No lo utilizo en ningun lado
            Area area = new Area(codeArea, name, description);
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

        List<Area> areas = gestorReserva.areas.getAllElementList();

        if (!areas.isEmpty()) {
            for (Area a : areas) {
                System.out.println(a);
            }
        } else {
            System.out.println("No se han encontrado areas registradas.");
        }
    }

    public void updateArea(NatureReserveManager gestorReserva)
    {
        String codeArea, newName, newDescription;

        System.out.println();
        codeArea = errorControl.validateStrings(sc, "Ingrese el codigo del area: ");

        Criteria<Area> criteriaForParkRangers = area -> area.getCodeArea().equals(codeArea);
        Area auxArea = gestorReserva.areas.getElement(criteriaForParkRangers);

        if (auxArea != null) {
            newName = newNameArea(auxArea.name);
            newDescription = newDescriptionArea(auxArea.description);
            if (gestorReserva.updateArea(codeArea, newName, newDescription)) {
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

    public void deleteArea(NatureReserveManager gestorReserva)
    {
        String codeArea;
        codeArea = errorControl.validateStrings(sc, "\nIngrese el codigo del area a eliminar: ");

        Criteria<Area> criteriaForParkRangers = area -> area.getCodeArea().equals(codeArea);
        Area auxArea = gestorReserva.areas.getElement(criteriaForParkRangers);

        if (auxArea != null) {

            // Manejar la forma de que si el area tiene al menos un incidente como VISIBLE, no se podra borrarlo.
            boolean delete = true;
            List<Incident> incidentsArea = auxArea.incidents.getAllElementList();
            for (Incident in : incidentsArea) {
                if (in.getVisibility() == Visibility.visible) {
                    delete = false;
                    break;
                }
            }

            if (delete) {
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
                             1. Reportar incidente
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

        List<Area> areas = gestorReserva.areas.getAllElementList();

        if (!areas.isEmpty()) {

            incidentCode = errorControl.validateStrings(sc, "Ingrese el codigo del incidente: ");

            Criteria<Incident> criteriaByCodeArea = incident -> incident.getIncidentCode().equals(incidentCode);
            Incident auxIncidenet = gestorReserva.incidents.getElement(criteriaByCodeArea);

            if (auxIncidenet == null) {
                description = errorControl.validateStrings(sc, "Ingrese la descripcion del incidente: ");
                annotations = errorControl.validateStrings(sc, "Ingrese las anotaciones del incidente: ");
                dateTimeReport = errorControl.validateLocalDateTime(sc, "Ingrese la fecha de reporte: ");
                selectArea = selectAreaIncident(gestorReserva);

                Incident incidente = new Incident(incidentCode, description, dateTimeReport, null,
                        null, IncidentStatus.pending, annotations, selectArea, Visibility.visible);

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

        Area selectedArea;
        Area areaFound;
        int areaNumber;

        List<Area> areas = gestorReserva.areas.getAllElementList();

        do {
            if (!areas.isEmpty()) {
                System.out.println("Las areas disponibles son: ");
                for (int i = 0; i < areas.size(); i++) {
                    Area area = areas.get(i);
                    System.out.println((i + 1) + " nombre: " + area.name + ", descripcion: " + area.description);
                }
            } else {
                System.out.println("No se han encontrado áreas para mostrar.");
            }
            areaNumber = errorControl.validateNumericInputInt(sc, "Ingrese la opcion deseada: ");

            if (areaNumber <= 0 || areaNumber > areas.size()) {
                System.out.println("Ingrese un valor en el rango mostrado.");
            }

        } while (areaNumber <= 0 || areaNumber > areas.size());

        areaFound = gestorReserva.areas.getElementAtIndex(areaNumber - 1);

        return areaFound;
    }

    public void showIncidents(NatureReserveManager gestorReserva)
    {
        Criteria<Incident> criteriaForIncidents = incident -> incident.getVisibility() == Visibility.visible;
        List<Incident> incidentsVisible = gestorReserva.incidents.getElements(criteriaForIncidents);
        
        if (!incidentsVisible.isEmpty()) {
            for (int i = 0; i < incidentsVisible.size(); i++) {
                Incident incident = incidentsVisible.get(i);
                System.out.println(incident);
            }
        } else {
            System.out.println("No se han registrado incidentes por el momento.");
        }
    }

    public void updateIncidents(NatureReserveManager gestorReserva)
    {
        String incidentCode, newDescription, newAnnotations;
        Area newArea;
        LocalDateTime newDateTimeReport;

        incidentCode = errorControl.validateStrings(sc, "\nIngrese el código del incide a actualizar: ");
        Criteria<Incident> criteria1 = incident -> incident.incidentCode.equals(incidentCode);
        Incident auxIncident = gestorReserva.incidents.getElement(criteria1);

        if (auxIncident != null) {
            newDescription = changeDescriptionIncident(auxIncident.description);
            newAnnotations = changeAnnotations(auxIncident.annotations);
            // newAgentSupport = changeAgentSupport(auxIncident.agentSupport, gestorReserva);
            newDateTimeReport = changeDateTimeReport(auxIncident.dateTimeReport);
            // newDateTimeAttention = changeDateTimeAttetion(auxIncident.dateTimeAttention);
            // newIncidentStatus = changeIncidentStatus(auxIncident.incidentStatus);
            newArea = changeArea(auxIncident.area, gestorReserva);

            if (gestorReserva.updateIncident(incidentCode, newDescription, newDateTimeReport, auxIncident.getDateTimeAttention(),
                    auxIncident.getAgentSupport(), auxIncident.getIncidentStatus(), newAnnotations, newArea)) {
                System.out.println("Se ha actualizado correctamente el incidente");
            } else {
                System.err.println("No se ha podido actualizar el incidente");
            }
        } else {
            System.out.println("No se ha encontrado el incidente con el codigo proporcionado.");
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

    public void deleteIncident(NatureReserveManager gestorReserva)
    {
        String incidentCode;
        incidentCode = errorControl.validateStrings(sc, "Ingrese el código del incide a eliminar: ");

        Criteria<Incident> criteria1 = incident -> incident.incidentCode.equals(incidentCode);
        Incident auxIncident = gestorReserva.incidents.getElement(criteria1);

        // Si el incidente esta resuelto, entonces se lo "elimina (oculta)"
        if (auxIncident != null && auxIncident.getIncidentStatus() == IncidentStatus.resolved) {
            auxIncident.setVisibility(Visibility.hidden);
            System.out.println("Se ha eliminado el incidente.");
        }

    }

    // Gestionar incidentes 
    public void manageIncidentMenu(NatureReserveManager gestorReserva)
    {
        int option;

        do {
            System.out.println();
            String message = """
                             === Gestion de atenciones de incidentes ===
                             1. Atender un incidente
                             2. Terminar atencion de un incidente
                             3. Regresar
                             Seleccione una opcion: 
                             """;
            option = errorControl.validateNumericInputInt(sc, message);

            switch (option) {
                case 1:
                    atenderIncident(gestorReserva);
                    break;
                case 2:
                    terminationOfAnIncident(gestorReserva);
                    break;
                case 3:
                    System.out.println("Regresando...\n");
                    break;
                default:
                    System.err.println("Error, opcion no disponible.\n");
            }

        } while (option != 3);
    }

    public void atenderIncident(NatureReserveManager gestorReserva)
    {

        // Mostrar solo aquellos incidentes que esten pendientes. Aquellos que esten en "proceso" estaran en la opcion de terminar incidente.
        Criteria<Incident> criteriaForIncident = incident -> incident.getIncidentStatus() == IncidentStatus.pending;
        List<Incident> auxIncidents = gestorReserva.incidents.getElements(criteriaForIncident);

        Criteria<ParkRanger> criteriaForParkRanger = parkRanger -> parkRanger.getStatus() == RangerStatus.free;
        List<ParkRanger> auxParkRangers = gestorReserva.parkRangers.getElements(criteriaForParkRanger);

        int numberIncident, numberParkRanger;
        Incident incidentObtained;
        ParkRanger parkRangerObtained;
        LocalDateTime dateAttention;

        if (!auxIncidents.isEmpty()) {

            do {
                System.out.println("Seleccion alguno de los incidentes pendientes: ");
                for (int i = 0; i < auxIncidents.size(); i++) {
                    Incident inc = auxIncidents.get(i);
                    System.out.println((i + 1) + " descripcion: " + inc.description + ", nombre area: " + inc.area.name);
                }

                numberIncident = errorControl.validateNumericInputInt(sc, "Su opcion es: ");

                if (numberIncident <= 0 || numberIncident > auxIncidents.size()) {
                    System.err.println("Ingrese un numero en el rango valido.");
                }

            } while (numberIncident <= 0 || numberIncident > auxIncidents.size());

            incidentObtained = auxIncidents.get(numberIncident - 1);

            if (incidentObtained != null) {

                if (auxParkRangers.isEmpty()) {
                    System.out.println("No hay guardabosques disponibles para atender el incidente en este momento.");
                    return;
                }

                do {
                    System.out.println("Seleccion alguno de los guardabosques libres: ");
                    for (int i = 0; i < auxParkRangers.size(); i++) {
                        ParkRanger pk = auxParkRangers.get(i);
                        System.out.println((i + 1) + " nombres: " + pk.names + ", apellidos" + pk.lastNames);
                    }

                    numberParkRanger = errorControl.validateNumericInputInt(sc, "Su opcion es: ");

                    if (numberParkRanger <= 0 || numberParkRanger > auxIncidents.size()) {
                        System.err.println("Ingrese un numero en el rango valido.");
                    }

                } while (numberParkRanger <= 0 || numberParkRanger > auxIncidents.size());

                parkRangerObtained = auxParkRangers.get(numberParkRanger - 1);

                if (parkRangerObtained != null) {

                    dateAttention = errorControl.validateLocalDateTime(sc, "Ingrese la fecha de atencion (yyyy-MM-dd HH:mm:ss)");
                    incidentObtained.setDateTimeAttention(dateAttention);
                    incidentObtained.setAgentSupport(parkRangerObtained);
                    incidentObtained.setIncidentStatus(IncidentStatus.inProcess);
                    parkRangerObtained.setStatus(RangerStatus.busy);
                    parkRangerObtained.incidents.insertElement(incidentObtained);

                    System.out.println("Se esta atendiendo el incidente.");
                }
            }

        } else {
            System.out.println("No se han encontrado incidentes pendientes.");
        }
    }

    // Mostrar solo aquellos incidentes en proceso
    public void terminationOfAnIncident(NatureReserveManager gestorReserva)
    {
        Criteria<Incident> criteriaForIncident = incident -> incident.getIncidentStatus() == IncidentStatus.inProcess;
        List<Incident> auxIncidents = gestorReserva.incidents.getElements(criteriaForIncident);

        int numberIncident;
        Incident incidentObtained;

        if (!auxIncidents.isEmpty()) {

            do {
                System.out.println("Los incidentes en proceso para poder ser concluidos son: ");
                for (int i = 0; i < auxIncidents.size(); i++) {
                    Incident aux = auxIncidents.get(i);
                    System.out.println((i + 1) + " descripcion: " + aux.description + " nombre area: " + aux.area.name);
                }
                numberIncident = errorControl.validateNumericInputInt(sc, "Su opcion es: ");

            } while (numberIncident <= 0 || numberIncident > auxIncidents.size());

            incidentObtained = auxIncidents.get(numberIncident - 1);

            if (incidentObtained != null) {
                incidentObtained.setIncidentStatus(IncidentStatus.resolved);
                incidentObtained.getAgentSupport().setStatus(RangerStatus.free);
                System.out.println("Incidente terminado.");
            }

        } else {
            System.out.println("No hay incidentes en proceso para concluirlos.");
        }

    }

}
