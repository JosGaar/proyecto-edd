package proyecto;

import dataStructures.LinkedList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Program {

    private Scanner sc = new Scanner(System.in);
    private ErrorControl errorControl = new ErrorControl();
    private NatureReserveManager reserveManager;

    public void run()
    {
        showMainMenu();
    }

    public void showMainMenu()
    {
        int option;
        this.reserveManager= new NatureReserveManager();
        
        do {
            System.out.println("=== Gestion de la reserva natural ===");
            String message = "1. Gestionar visitantes\n" 
                    + "2. Gestionar visitas\n" 
                    + "3. Gestionar guardaparques\n" 
                    + "4. Gestionar areas\n" + "5. Gestionar incidentes\n" 
                    + "6. Gestionar atenciones de incidentes\n" 
                    + "7. Reportes\n" 
                    + "8. Salir\n";
            System.out.print(message);
            option = this.errorControl.validateNumericInputInt(this.sc, "Seleccione una opcion: ");
            
            switch (option) {
                case 1:
                    visitorMenu();
                    break;
                case 2:
                    visitMenu();
                    break;
                case 3:
                    parkRangersMenu();
                    break;
                case 4:
                    areaMenu();
                    break;
                case 5:
                    incidentMenu();
                    break;
                case 6:
                    manageIncidentMenu();
                    break;
                case 7:
                    reportMenu();
                case 8:
                    System.out.println("\nSaliendo...\n");
                    break;
                default:
                    System.err.println("Error, opcion no disponible.\n");
            }
        } while (option != 8);
    }

    public void visitorMenu()
    {
        int option;
        
        do {
            System.out.println();
            String message = "=== Visitantes ===\n"
               + "1. Crear visitante\n"
               + "2. Mostrar visitantes\n"
               + "3. Actualizar visitante\n"
               + "4. Eliminar visitante\n"
               + "5. Regresar\n";
            
            System.out.print(message);
            option = this.errorControl.validateNumericInputInt(this.sc, "Seleccione una opcion: ");

            switch (option) {
                case 1:
                    createVisitor();
                    break;
                case 2:
                    showVisitors();
                    break;
                case 3:
                    updateVisitor();
                    break;
                case 4:
                    deleteVisitor();
                    break;
                case 5:
                    System.out.println("Regresando...\n");
                    break;
                default:
                    System.err.println("Error, opcion no disponible.\n");
            }
        } while (option != 5);
    }

    public void createVisitor()
    {
        String identification, address, phoneNumber, names, lastNames;

        identification = errorControl.validateIDNumber(sc, "\nIngrese la cedula de identidad: ");
        names = errorControl.validateTwoWords(sc, "Ingrese los dos nombres: ", "nombres");
        lastNames = errorControl.validateTwoWords(sc, "Ingrese dos apellidos: ", "apellidos");
        address = errorControl.validateStrings(sc, "Ingrese la direccion: ");
        phoneNumber = errorControl.validatePhoneNumber(sc, "Ingrese el número de teléfono: ");

        Visitor visitor = new Visitor(address, phoneNumber, VisitStatus.Inactive, identification, names, lastNames);
        if (this.reserveManager.addVisitor(visitor)) {
            System.out.println("Visitante agregado exitosamente.");
        }else{
            System.err.println("No se ha podido agregar el visitante: cédula ya existente.");
        }
    }

    public void showVisitors()
    {
        if(reserveManager.visitors.isEmpty()) {
            System.err.println("Error: No se puede mostra visitantes. No se han registrado visitantes por el momento.");
            return;
        }
        
        System.out.println("\nLos visitantes registrados son: ");
        String message = reserveManager.visitors.getElementsToString();
        System.out.println(((message!=null)?message:"No se han registrado visitantes por el momento."));
    }

    public void updateVisitor()
    {
        String identification, newAddress, newPhoneNumber, newNames, newLastNames;
        VisitStatus visitStatus;
        
        if (reserveManager.visitors.isEmpty()) {
            System.err.println("Error: No se puede actualizar un Visitante. No se han registrado visitantes por el momento.");
            return;
        }

        System.out.println();
        identification = errorControl.validateIDNumber(sc, "Ingrese la cedula de identidad para actualizar: ");

        Criteria<Visitor> criteriaForVisitor = visitor -> visitor.getIdentification().equals(identification);
        Visitor auxVisitor = reserveManager.visitors.getElement(criteriaForVisitor);
        
        if(auxVisitor == null){
            System.err.println("Error: Visitante no encontrado.");
            return;
        }
        
        newNames = changeVisitorWords(auxVisitor.names, "nombres");
        newLastNames = changeVisitorWords(auxVisitor.lastNames, "apellidos");
        newPhoneNumber = changeVisitorPhoneNumber(auxVisitor.getPhoneNumber());
        newAddress = changeVisitorAddress(auxVisitor.getAddress());
        visitStatus = VisitStatus.Active;

        if (reserveManager.updateVisitor(newAddress, newPhoneNumber, visitStatus, identification, newNames, newLastNames)) {
            System.out.println("Proceso realizado correctamente.");
        } else {
            System.err.println("Error al actualizar el visitante.");
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

    public void deleteVisitor()
    {   
        if (reserveManager.visitors.isEmpty()) {
            System.err.println("Error: No se puede borrar un Visitante. No se han registrado visitantes por el momento.");
            return;
        }
        String identification;
        identification = errorControl.validateStrings(sc, "\nIngrese la identificación del visitante a eliminar: ");
        
        if (reserveManager.removeVisitor(identification)) {
            System.out.println("Visitante eliminado exitosamente.");
        } else {
            System.err.println("Error: no se ha podido eliminar al visitante.");
        }
    }

    public void visitMenu()
    {
        int option; 
        do {
            System.out.println();
            String message = "=== Visitas ===\n"
                   + "1. Crear visita\n"
                   + "2. Mostrar visitas\n"
                   + "3. Actualizar visita\n"
                   + "4. Eliminar visita\n"
                   + "5. Terminar visita\n"
                   + "6. Regresar\n";
            System.out.print(message);
            option = errorControl.validateNumericInputInt(sc, "Seleccione una opcion: ");

            switch (option) {
                case 1:
                    createVisit();
                    break;
                case 2:
                    showVisit();
                    break;
                case 3:
                    updateVisit();
                    break;
                case 4:
                    deleteVisit();
                    break;
                case 5:
                    recordVisitsExit();
                    break;
                case 6:
                    System.out.println("Regresando...\n");
                    break;
                default:
                    System.err.println("Error, opcion no disponible.\n");
            }

        } while (option != 6);
    }

    public void createVisit() {
        if (reserveManager.visitors.isEmpty()) {
            System.out.println("No puede generar una visita si no tiene visitantes.");
            return;
        }
        
        String codeVisit = errorControl.validateStrings(sc, "\nIngrese el código de la visita: ");
        if (reserveManager.visits.getElement(codeVisit) != null) {
            System.err.println("Error: La visita con el código dado ya existe.");
            return;
        }
        this.showVisitorsToSelect();
        Visitor selectedVisitor = selectVisitor();
        if (selectedVisitor == null) {
            System.err.println("Error: No se ha seleccionado ningún visitante.");
            return;
        }
        
        LocalDateTime entryDate = errorControl.validateLocalDateTime(sc, "Ingrese la fecha de entrada (yyyy-MM-dd HH:mm:ss): ");
        Visit visit = new Visit(codeVisit, selectedVisitor, Visibility.visible,  entryDate);
        
        if (reserveManager.addVisit(visit)) {
            System.out.println("Se ha añadido exitosamente la visita.");
        } else {
            System.err.println("Error: Ya existe una visita con el visitante seleccionado.");
        }
    }
    
    private void showVisitorsToSelect() {
        System.out.println("\nSeleccione algun visitante de los que se le muestra a continuación:");
        for (int i = 0; i < reserveManager.visitors.size(); i++) {
            System.out.println("Numero: " + (i + 1)
                    + reserveManager.visitors.getAt(i).toString());
        }
    }
    
    private Visitor selectVisitor() {
        if (reserveManager.visitors.isEmpty()) {
            return null;
        }
        int numberVisitor;
        do {
            numberVisitor = errorControl.validateNumericInputInt(sc, "Seleccione un número: ");
            if (numberVisitor <= 0 || numberVisitor > reserveManager.visitors.size()) {
                System.err.println("Ingrese un número en el intervalo mostrado.");
            }
        } while (numberVisitor <= 0 || numberVisitor > reserveManager.visitors.size());

        return reserveManager.visitors.getAt(numberVisitor - 1);
    }

    public void showVisit()
    {
        if (!reserveManager.visits.isEmpty()) {
            System.out.println("\nLas visitas disponibles son: ");
            String message = reserveManager.visits.getElementsToString();
            System.out.println(((message!=null)?message:"No se han registrado visitas por el momento."));
        } else {
            System.out.println("No se han registrado visitas por el momento.");
        }
    }

    public void updateVisit()
    {
        LocalDateTime newDateEntry;
        String codeVisit;

        codeVisit = errorControl.validateStrings(sc, "\nIngrese el código de la visita: ");

        Criteria<Visit> criteriaforVisit = visit -> visit.getCodeVisit().equals(codeVisit);
        Visit auxVisit = this.reserveManager.visits.getElement(criteriaforVisit);

        if (auxVisit == null) {
            System.out.println("Error: no se ha encontrado la visita.");
            return;
        }
        
        newDateEntry = updateDateEntry(auxVisit.getEntryDate());
        if (reserveManager.updateVisit(codeVisit, auxVisit.getVisitor(), newDateEntry, null)) {
            System.out.println("Proceso realizado correctamente.");
        } else {
            System.err.println("Error: no se ha podido actualizar la visita.");
        }
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

    public void deleteVisit()
    {
        String codeVisit = errorControl.validateStrings(sc, "\nIngrese el código de la visita: ");
        Visit auxVisit = reserveManager.visits.getElement(codeVisit);

        if (auxVisit == null) {
            System.err.println("Error: no se ha encontrado la visita.");
            return;
        }
        if (auxVisit.getVisitor().getStatus() == VisitStatus.Inactive) {
            auxVisit.setVisibility(Visibility.hidden);
            System.out.println("La visita ha sido eliminada correctamente.");
        } else {
            System.err.println("Error: la visita sigue activa.");
        }

    }

    public void recordVisitsExit()
    {
        String codeVisit;
        LocalDateTime exitDate;
        codeVisit = errorControl.validateStrings(sc, "\nIngrese el código de la visita: ");

        Criteria<Visit> criteriaforVisit = visit -> visit.getCodeVisit().equals(codeVisit);
        Visit auxVisit = this.reserveManager.visits.getElement(criteriaforVisit);

        if (auxVisit == null) {
            System.err.println("Error: no se ha encontrado la visita con el codigo proporcionado");
            return;
            
        }
        do {
            exitDate = errorControl.validateLocalDateTime(sc, "Ingrese la fecha de salida (yyyy-MM-dd HH:mm:ss): ");
            if (errorControl.validateDateTimeRange(exitDate, auxVisit.getEntryDate())) {
                System.err.println("Error: la fecha de salida no puede ser anterior a la de registro.\n");
            } else {
                break;
            }
        } while (true);
        
        if(this.reserveManager.endVisit(codeVisit, exitDate)){
            System.out.println("La visita ha sido registrada para su salida.");
        }else{
            System.err.println("La visita no se pudo regristrar su salida");
        }
    }

    public void parkRangersMenu()
    {
        int option;

        do {
            System.out.println();
            String message = "=== Guardabosques ===\n"
                   + "1. Crear guardaparque\n"
                   + "2. Mostrar guardaparques\n"
                   + "3. Actualizar guardaparque\n"
                   + "4. Eliminar guardaparque\n"
                   + "5. Regresar\n";
            System.out.print(message);
            option = errorControl.validateNumericInputInt(sc, "Seleccione una opcion: ");

            switch (option) {
                case 1:
                    createParkRanger();
                    break;
                case 2:
                    showParkRangers();
                    break;
                case 3:
                    updateRanger();
                    break;
                case 4:
                    deleteRanger();
                    break;
                case 5:
                    System.out.println("Regresando...\n");
                    break;
                default:
                    System.err.println("Error, opcion no disponible.\n");
            }

        } while (option != 5);
    }

    public void createParkRanger()
    {
        String id, firstNames, lastNames;
        LocalDate contractDate;
        
        System.out.println();
        id = errorControl.validateIDNumber(sc, "Ingrese la cedula de identidad: ");
        contractDate = errorControl.validateLocalDate(sc, "Ingrese la fecha de contrato (en formato yyyy-MM-dd): ");
        firstNames = errorControl.validateTwoWords(sc, "Ingrese sus dos nombres: ", "nombres");
        lastNames = errorControl.validateTwoWords(sc, "Ingrese sus dos apellidos: ", "apellidos");
        
        ParkRanger parkRanger = new ParkRanger(contractDate, id, firstNames, lastNames, RangerStatus.free, Visibility.visible);

        if (reserveManager.addParkRanger(parkRanger)) {
            System.out.println("Guardaparques añadio exitosamente.");
        }else{
            System.err.println("Error: No se pudo añadir al guardabosques");
        }
    }     

    public void showParkRangers()
    {
        if (!this.reserveManager.parkRangers.isEmpty()) {
            System.out.println("\nLos guardabosques disponibles son: ");
            String message = reserveManager.parkRangers.getElementsToString();
            System.out.println(((message!=null)?message:"No se han encontrado guardabsoques."));
        } else {
            System.out.println("No se han encontrado guardabosques.");
        }
    }

    public void updateRanger()
    {
        String id, newFirstNames, newLastNames;
        LocalDate newContractDate;
        RangerStatus newStatus;

        System.out.println();
        id = errorControl.validateIDNumber(sc, "Ingrese la cedula de identidad: ");

        Criteria<ParkRanger> criteriaForParkRangers = parkRanger -> parkRanger.getIdentification().equals(id);
        ParkRanger auxParkRanger = reserveManager.parkRangers.getElement(criteriaForParkRangers);

        if (auxParkRanger == null) {
            System.err.println("Error: no se ha encontrado al guardabosques con la cedula proporcionada");
            return;
        }
        newFirstNames = changeRangerWords(auxParkRanger.names, "nombres");
        newLastNames = changeRangerWords(auxParkRanger.lastNames, "apellidos");
        newContractDate = changeContractDateRanger(auxParkRanger.dateOfHire);
        // newStatus = changeRangerStatus(auxParkRanger.status);

        if (reserveManager.updateParkRanger(id, newFirstNames, newLastNames, newContractDate, auxParkRanger.status)) {
            System.out.println("Guardabosques actualizado correctamente.");
        }else{
            System.out.println("No se pudo actualizar el guardabosques.");
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

    public void deleteRanger()
    {
        String id;
        id = this.errorControl.validateStrings(this.sc, "\nIngrese la cedula de identidad del guardabosques a eliminar: ");

        Criteria<ParkRanger> criteriaForParkRangers = parkRanger -> parkRanger.getIdentification().equals(id);
        ParkRanger auxParkRanger = this.reserveManager.parkRangers.getElement(criteriaForParkRangers);

        if (auxParkRanger == null) {
            System.err.println("No se ha encontrado el guardabosques con el id proporcionado.");
            return;
        }
        
         if (auxParkRanger.status == RangerStatus.free) { // Desocupado --> eliminar
             System.err.println("No se puede eliminar el guardabosques debido a que se encuentra ocupado.");
             return;
         }
       
         if (this.reserveManager.removeParkRanger(id)) {
            System.out.println("Guardaparques eliminado exitosamente.");
        }else{
            System.out.println("No se pudo eliminar al guardabosques.");
        }   
    }


    public void areaMenu()
    {
        int option;

        do {
            System.out.println();
            String message = "=== Areas ===\n"
               + "1. Crear area\n"
               + "2. Mostrar areas\n"
               + "3. Actualizar area\n"
               + "4. Eliminar area\n"
               + "5. Regresar\n";
            System.out.print(message);
            option = this.errorControl.validateNumericInputInt(this.sc, "Seleccione una opción: ");

            switch (option) {
                case 1:
                    createArea();
                    break;
                case 2:
                    showAreas();
                    break;
                case 3:
                    updateArea();
                    break;
                case 4:
                    deleteArea();
                    break;
                case 5:
                    System.out.println("Regresando...\n");
                    break;
                default:
                    System.err.println("Error, opcion no disponible.\n");
            }

        } while (option != 5);
    }

    public void createArea()
    {
        String codeArea, name, description;

        System.out.println();
        codeArea = errorControl.validateStrings(sc, "Ingrese el codigo del area: ");
        name = errorControl.validateStrings(sc, "Ingrese el nombre del area: ");
        description = errorControl.validateStrings(sc, "Ingrese la descripcion del area: ");
        Area area = new Area(codeArea, name, description);
        
        if (this.reserveManager.addArea(area)) {
            System.out.println("El area se ha añadido exitosamente.");
        }else{
            System.out.println("No se pudo registrar la nueva Área");
        }
    }

    public void showAreas()
    {
        if (!this.reserveManager.areas.isEmpty()) {
            System.out.println("\nAreas disponibles: ");
            String message = reserveManager.visits.getElementsToString();
            System.out.println(((message!=null)?message:"No se han encontrado aareas por el momento."));
        } else {
            System.out.println("No se han encontrado areas registradas.");
        }
    }

    public void updateArea()
    {
        String codeArea, newName, newDescription;
        codeArea = errorControl.validateStrings(sc, "\nIngrese el codigo del area: ");

        Criteria<Area> criteriaForParkRangers = area -> area.getCodeArea().equals(codeArea);
        Area auxArea = this.reserveManager.areas.getElement(criteriaForParkRangers);

        if (auxArea == null) {
            System.err.println("No se ha encontrado el area con el codigo proporcionada.");
            return;
        }
        
        newName = newNameArea(auxArea.name);
        newDescription = newDescriptionArea(auxArea.description);
        if (this.reserveManager.updateArea(codeArea, newName, newDescription)) {
            System.out.println("Se actualizo el área correctamente.");
        }else{
            System.out.println("No se pudo actualizar el área.");
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

    public void deleteArea()
    {
        String codeArea;
        codeArea = this.errorControl.validateStrings(this.sc, "\nIngrese el codigo del area a eliminar: ");
        
        Criteria<Area> criteriaForParkRangers = area -> area.getCodeArea().equals(codeArea);
        Area auxArea = this.reserveManager.areas.getElement(criteriaForParkRangers);
        
        if (auxArea == null) {
            System.err.println("Error: no se ha encontrado el area a eliminar.");
            return;
        }
        // Manejar la forma de que si el area tiene al menos un incidente como VISIBLE, no se podra borrarlo.
        Criteria<Incident> criteriaByVisinility = incident -> incident.getVisibility().equals(Visibility.visible);
        if (auxArea.incidents.contains(criteriaByVisinility)) {
            System.err.println("Error: no se ha podido eliminar el area, puesto que esta relacionado con alguna incidencia.");
            return;
        }
        
        if (this.reserveManager.removeArea(codeArea)) {
            System.out.println("Area eliminiada exitosamente.");
        }else{
            System.out.println("No se pudo eliminar el Área.");
        }
    }

    public void incidentMenu()
    {
        int option;

        do {
            System.out.println();
            String message = "=== Incidentes ===\n"
                           + "1. Reportar incidente\n"
                           + "2. Mostrar incidentes\n"
                           + "3. Actualizar incidente\n"
                           + "4. Eliminar incidente\n"
                           + "5. Regresar\n";
            System.out.print(message);
            option = errorControl.validateNumericInputInt(sc, "Seleccione una opcion: ");
            
            switch (option) {
                case 1:
                    createIndicent();
                    break;
                case 2:
                    showIncidents();
                    break;
                case 3:
                    updateIncidents();
                    break;
                case 4:
                    deleteIncident();
                    break;
                case 5:
                    System.out.println("Regresando...\n");
                    break;
                default:
                    System.err.println("Error, opcion no disponible.\n");
            }
        } while (option != 5);
    }

    public void createIndicent()
    {
        String incidentCode, description, annotations;
        LocalDateTime dateTimeReport;
        Area selectArea;
        
        if (this.reserveManager.areas.isEmpty()) {
             System.err.println("Por favor, registre las areas o guardabosques para poder crear un incidente.");
             return;
        }
        
        incidentCode = errorControl.validateStrings(sc, "\nIngrese el codigo del incidente: ");
        description = errorControl.validateStrings(sc, "Ingrese la descripcion del incidente: ");
        annotations = errorControl.validateStrings(sc, "Ingrese las anotaciones del incidente: ");
        dateTimeReport = errorControl.validateLocalDateTime(sc, "Ingrese la fecha de reporte (yyyy-MM-dd HH:mm:ss): ");
        selectArea = selectAreaIncident();

        Incident incidente = new Incident(incidentCode, description, dateTimeReport, null, null, IncidentStatus.pending, annotations, selectArea, Visibility.visible);

        if (reserveManager.addIncident(incidente)) {
            System.out.println("Se ha añadido el incidente correctamente.");
        } else {
            System.err.println("No se ha podido añadir el incidente correctamente.");
        }
    }

    public Area selectAreaIncident()
    {
        Area areaFound;
        int areaNumber;
        
        do {
            if (!this.reserveManager.areas.isEmpty()) {
                System.out.println("\nLas areas disponibles son: ");
                for (int i = 0; i < this.reserveManager.areas.size(); i++) {
                    Area area = this.reserveManager.areas.getAt(i);
                    System.out.println(("Numero: " + i + 1) + ". Nombre: " + area.name + ", descripcion: " + area.description);
                }
            } else {
                System.out.println("No se han encontrado áreas para mostrar.");
            }
            areaNumber = errorControl.validateNumericInputInt(sc, "Ingrese la opcion deseada: ");

            if (areaNumber <= 0 || areaNumber > this.reserveManager.areas.size()) {
                System.err.println("Error: ingrese un valor en el rango mostrado.");
            }

        } while (areaNumber <= 0 || areaNumber > this.reserveManager.areas.size());

        areaFound = this.reserveManager.areas.getElementAt(areaNumber - 1);

        return areaFound;
    }

    public void showIncidents()
    {
        this.reserveManager.showIncidentes();
    }

    public void updateIncidents()
    {
        String incidentCode, newDescription, newAnnotations;
        Area newArea;
        LocalDateTime newDateTimeReport;

        incidentCode = errorControl.validateStrings(sc, "\nIngrese el código del incide a actualizar: ");
        Criteria<Incident> criteria1 = incident -> incident.incidentCode.equals(incidentCode);
        Incident auxIncident = this.reserveManager.incidents.getElement(criteria1);

        if (auxIncident == null) {
             System.out.println("No se ha encontrado el incidente con el codigo proporcionado.");
             return;
        }
        
        newDescription = changeDescriptionIncident(auxIncident.description);
        newAnnotations = changeAnnotations(auxIncident.annotations);
        newDateTimeReport = changeDateTimeReport(auxIncident.dateTimeReport);
        newArea = changeArea(auxIncident.area);

        if (this.reserveManager.updateIncident(incidentCode, newDescription, newDateTimeReport, auxIncident.getDateTimeAttention(),
                auxIncident.getAgentSupport(), auxIncident.getIncidentStatus(), newAnnotations, newArea)) {
            System.out.println("Incidente actualizado correctamente.");
        } else {
            System.err.println("No se ha podido actualizar el incidente.");
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

    public Area changeArea(Area originalArea)
    {
        String message;
        Area newArea = null;
        int option;
        do {
            message = "¿Desea cambiar el area del incidente?\nSi (1) / No (2): ";
            option = errorControl.validateNumericInputInt(sc, message);

            switch (option) {
                case 1:
                    newArea = selectAreaIncident();
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

    public void deleteIncident()
    {
        String incidentCode;
        incidentCode = errorControl.validateStrings(sc, "\nIngrese el código del incide a eliminar: ");

        Criteria<Incident> criteriaByCode = incident -> incident.incidentCode.equals(incidentCode);
        Incident auxIncident = this.reserveManager.incidents.getElement(criteriaByCode);
        
        // Si el incidente esta resuelto, entonces se lo "elimina (oculta)"
        if (auxIncident == null) {
            System.out.println("El incide no ha sido encontrado");
            return;
        }
        
        if (auxIncident.getIncidentStatus() == IncidentStatus.resolved) {
            auxIncident.setVisibility(Visibility.hidden);
            System.out.println("Se ha eliminado el incidente.");
        } else {
            System.err.println("Error: el incidente no ha sido resuelto.");
        }
    }
    
    public void manageIncidentMenu()
    {
        int option;

        do {
            System.out.println();
            String message = "=== Gestion de atenciones de incidentes ===\n"
                               + "1. Atender un incidente\n"
                               + "2. Terminar atencion de un incidente\n"
                               + "3. Regresar\n";
            System.out.print(message);
            option = errorControl.validateNumericInputInt(sc, "Seleccione una opcion: ");
            
            switch (option) {
                case 1:
                    handleIncident();
                    break;
                case 2:
                    terminateIncident();
                    break;
                case 3:
                    System.out.println("Regresando...\n");
                    break;
                default:
                    System.err.println("Error, opcion no disponible.\n");
            }
        } while (option != 3);
    }
    
    public void handleIncident() {
        LinkedList<Incident> pendingIncidents = reserveManager.incidents.getElements(incident -> incident.getIncidentStatus() == IncidentStatus.pending);
        LinkedList<ParkRanger> freeParkRangers = reserveManager.parkRangers.getElements(parkRanger -> parkRanger.getStatus() == RangerStatus.free);

        if (pendingIncidents.isEmpty()) {
            System.out.println("No se encontarron incientes pendientes.");
            return;
        }
        showPendingIncidents(pendingIncidents);
        Incident selectedIncident = selectIncident(pendingIncidents);
        if (selectedIncident == null) {
            return;
        }
        if (freeParkRangers.isEmpty()) {
            System.err.println("No hay guardabosques disponibles actualmente para atender el Incidente.");
            return;
        }
        showFreeParkRangers(freeParkRangers);
        ParkRanger selectedParkRanger = selectParkRanger(freeParkRangers);
        if (selectedParkRanger == null) {
            return;
        }
        LocalDateTime attentionDate = enterAttentionDate(selectedIncident.getDateTimeReport());
        
        if(reserveManager.atenderIncident(selectedIncident, selectedParkRanger, attentionDate)){
            System.out.println("");
        }
    }

    private void showPendingIncidents(LinkedList<Incident> incidents) {
        System.out.println("\nPending Incidents:");
        for (int i = 0; i < incidents.size(); i++) {
            Incident incident = incidents.getAt(i);
            System.out.println("Number: " + (i + 1) + ". Area Name: " + incident.getArea().getName() + ", Description: " + incident.getArea().getDescription());
        }
    }

    private Incident selectIncident(LinkedList<Incident> incidents) {
        if (incidents.isEmpty()) {
            return null;
        }
        int incidentNumber;
        do {
            incidentNumber = errorControl.validateNumericInputInt(this.sc, "Select an incident: ");
            if (incidentNumber <= 0 || incidentNumber > incidents.size()) {
                System.err.println("Error: Please enter a valid number.");
            }
        } while (incidentNumber <= 0 || incidentNumber > incidents.size());

        return incidents.getAt(incidentNumber - 1);
    }
    
    private void showFreeParkRangers(LinkedList<ParkRanger> parkRangers) {
        System.out.println("\nFree Park Rangers:");
        for (int i = 0; i < parkRangers.size(); i++) {
            ParkRanger ranger = parkRangers.getAt(i);
            System.out.println("Number: " + (i + 1) + ". Names: " + ranger.getNames() + ", Last Names: " + ranger.getLastNames());
        }
    }
    
    private ParkRanger selectParkRanger(LinkedList<ParkRanger> parkRangers) {
        if (parkRangers.isEmpty()) {
            return null;
        }
        int parkRangerNumber;
        do {
            parkRangerNumber = errorControl.validateNumericInputInt(this.sc, "Select a park ranger: ");
            if (parkRangerNumber <= 0 || parkRangerNumber > parkRangers.size()) {
                System.err.println("Error: Please enter a valid number.");
            }
        } while (parkRangerNumber <= 0 || parkRangerNumber > parkRangers.size());

        return parkRangers.getAt(parkRangerNumber - 1);
    }
    
    private LocalDateTime enterAttentionDate(LocalDateTime reportDate) {
        LocalDateTime attentionDate;
        do {
            attentionDate = errorControl.validateLocalDateTime(sc, "\nEnter the attention date (yyyy-MM-dd HH:mm:ss): ");
            if (errorControl.validateDateTimeRange(attentionDate, reportDate)) {
                System.err.println("Error: The date must be after the report date.");
            } else {
                break;
            }
        } while (true);

        return attentionDate;
    }
    
    public void terminateIncident() {
        LinkedList<Incident> inProcessIncidents = reserveManager.incidents.getElements(incident -> incident.getIncidentStatus() == IncidentStatus.inProcess);

        if (inProcessIncidents.isEmpty()) {
            System.out.println("No hay incidentes en proceso para ser concluidos.");
            return;
        }
        
        showInProcessIncidents(inProcessIncidents);
        Incident selectedIncident = selectIncidentToTerminate(inProcessIncidents);
        
        if (reserveManager.terminateIncident(selectedIncident)) {
            System.out.println("Se ha concluido la incidencia correctamente.");
        }else{
            System.err.println("No se pudo concluir la incidencia.");
        }
    }

    private void showInProcessIncidents(LinkedList<Incident> incidents) {
        System.out.println("\nIncidents in Process:");
        for (int i = 0; i < incidents.size(); i++) {
            Incident incident = incidents.getAt(i);
            System.out.println("Number: " + (i + 1) 
                    + ". Area Name: " + incident.getArea().getName() 
                    + ", Description: " + incident.getArea().getDescription() 
                    + ", Annotations: " + incident.getAnnotations());
        }
    }

    private Incident selectIncidentToTerminate(LinkedList<Incident> incidents) {
        int incidentNumber;
        do {
            incidentNumber = errorControl.validateNumericInputInt(sc, "Select an incident to terminate: ");
            if (incidentNumber <= 0 || incidentNumber > incidents.size()) {
                System.err.println("Error: Please enter a valid number.");
            }
        } while (incidentNumber <= 0 || incidentNumber > incidents.size());

        return incidents.getAt(incidentNumber - 1);
    }
    
    // Personas que han visitado la reserva, dado una fecha, señalando aquellas que no han salido.
    // Incidencias atentidas en un periodo de tiempo.
    // Incidentecias pendientes.
    public void reportMenu()
    {
        int option;
        do {
            System.out.println();
            String message = "=== Reportes ===\n"
                   + "1. Reporte de personas que han visitado la reserva\n"
                   + "2. Reporte de las incidencias atentidas y en proceso\n"
                   + "3. Regresar\n";
            System.out.print(message);
            option = errorControl.validateNumericInputInt(sc, "Seleccione una opcion: ");

            switch (option) {
                case 1:
                    visitsReport();
                    break;
                case 2:
                    incidentsReportings();
                    break;
                case 3:
                    System.out.println("Regresando...\n");
                    break;
                default:
                    System.err.println("Error, opcion no disponible.\n");
            }

        } while (option != 3);
    }

    public void visitsReport()
    {
        LocalDate date;
        date = errorControl.validateLocalDate(sc, "Ingrese la fecha de inicio (YYYY-MM-DD): ");
        System.out.println(this.reserveManager.generateDailyVisitorReport(date));
    }

    public void incidentsReportings()
    {
        LocalDateTime startTime;
        LocalDateTime endTime;

        startTime = errorControl.validateLocalDateTime(sc, "Ingrese la fecha de inicio: ");
        endTime = errorControl.validateLocalDateTime(sc, "Ingrese la fecha de fin: ");

        System.out.println(this.reserveManager.orderIncidentsByReportDate(startTime, endTime));
    }
}
