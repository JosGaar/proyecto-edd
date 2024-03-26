package dataPack;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedList<T> implements Iterable<T> {

    Node<T> firstElement;

    public LinkedList()
    {
        this.firstElement = null;
    }

    public boolean isEmpty()
    {
        return this.firstElement == null;
    }

    public boolean insertElement(T value)
    {

        if (value == null) {

            return false;
        }
        Node<T> newNode = new Node<>(value);
        if (firstElement == null) {
            firstElement = newNode;
        } else {
            Node<T> lastElement = lastElement();

            lastElement.next = newNode;
        }
        return true;

    }

    public Node<T> lastElement()
    {
        if (firstElement == null) {
            return null;
        }
        Node<T> current = firstElement;

        while (current.next != null) {

            current = current.next;
        }
        return current;
    }

    public boolean removeElement(T value)
    {
        if (value == null || firstElement == null) {
            return false; // No se puede eliminar si el valor es nulo o la lista está vacía
        }

        // Caso especial: el elemento a eliminar es el primer elemento de la lista
        if (firstElement.value.equals(value)) {
            firstElement = firstElement.next; // Actualizar el primer elemento para que apunte al siguiente nodo
            return true; // Indicar que se eliminó el elemento correctamente
        }

        // Buscar el nodo anterior al nodo a eliminar
        Node<T> current = firstElement;
        while (current.next != null && !current.next.value.equals(value)) {
            current = current.next;
        }

        // Si no se encontró el elemento a eliminar, devolver false
        if (current.next == null) {
            return false;
        }

        // Enlazar el nodo anterior al nodo siguiente al nodo a eliminar
        current.next = current.next.next;
        return true; // Indicar que se eliminó el elemento correctamente
    }

    //Con este metodo  se podra filtrar para el delete,add y update
    public T getElement(Criteria<T> criteria)
    {
        if (firstElement == null || criteria == null) {
            return null;
        }

        Node<T> current = firstElement;
        while (current != null) {
            if (criteria.match(current.value)) {
                return current.value;
            }
            current = current.next;
        }

        return null;
    }

    // Agregado por Josue -----------
    public List<T> getElements(Criteria<T> criteria)
    {
        List<T> matchingElements = new ArrayList<>();

        if (firstElement == null || criteria == null) {
            return matchingElements;
        }

        Node<T> current = firstElement;
        while (current != null) {
            if (criteria.match(current.value)) {
                matchingElements.add(current.value);
            }
            current = current.next;
        }

        return matchingElements;
    }

    public boolean updateElement(T elementoActualizar, Criteria<T> criteria, Consumer<T> updateAction)
    {
        if (isEmpty() || criteria == null || updateAction == null) {
            return false;
            // No se puede actualizar si la lista está vacía o si los parámetros son nulos
        }

        Node<T> current = firstElement;
        while (current != null) {
            if (criteria.match(current.value)) {
                // Si el elemento actual cumple con el criterio, aplicar la acción de actualización
                updateAction.accept(current.value);
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean update(T elementoActualizar, Criteria<T> criteria, Consumer<T> updateAction)
    {
        if (firstElement == null || criteria == null || updateAction == null) {
            return false;
        }

        Node<T> current = firstElement;
        while (current != null) {
            if (criteria.match(current.value)) {
                // Si el elemento actual cumple con el criterio, aplicar la acción de actualización
                updateAction.accept(current.value);
                return true;
            }
            current = current.next;
        }
        return false;

    }

    public String getAllElement()
    {

        StringBuilder descriptionElements = new StringBuilder();

        if (firstElement == null) {

            return "No existe registros";
        }
        Node<T> current = firstElement;
        while (current != null) {
            descriptionElements.append(current.value);
            descriptionElements.append("\n");
            current = current.next;
        }
        return descriptionElements.toString();

    }

    //Aqui se usa un arrayList para ordenar los datos en base a un comparator despues   
    public ArrayList<T> getAllElementInInterval(TimeIntervalCriteria<T> intervalCriteria, LocalDateTime startTime, LocalDateTime endTime)
    {
        if (firstElement == null || intervalCriteria == null) {
            return new ArrayList<>(); // Devolver un ArrayList vacío si la lista está vacía o los criterios son nulos
        }

        ArrayList<T> elementsWithinInterval = new ArrayList<>();
        Node<T> current = firstElement;
        while (current != null) {
            if (intervalCriteria.isWithinInterval(current.value, startTime, endTime)) {
                elementsWithinInterval.add(current.value);
            }
            current = current.next;
        }

        return elementsWithinInterval;
    }

    @Override
    public Iterator<T> iterator()
    {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {

        private Node<T> current;

        public LinkedListIterator()
        {
            this.current = firstElement;
        }

        @Override
        public boolean hasNext()
        {
            return current != null;
        }

        @Override
        public T next()
        {
            T data = current.value;
            current = current.next;
            return data;
        }
    }

}
