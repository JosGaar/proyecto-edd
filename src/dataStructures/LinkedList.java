package dataStructures;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import services.Consumer;
import criteria.Criteria;
import criteria.TimeIntervalCriteria;
import java.util.function.Predicate;

/**
 * @param <T>
 */
public class LinkedList<T> implements Iterable<T> {

    public Node<T> firstElement;

    public LinkedList()
    {
        this.firstElement = null;
    }

    public int size()
    {
        if (isEmpty())
        {
            return 0;
        }

        int count = 0;
        Node<T> curretNode = firstElement;

        while (curretNode != null)
        {
            count++;
            curretNode = curretNode.next;
        }
        return count;
    }

    public boolean addByLast(T value)
    {
        if (value == null)
        {
            return false;
        }
        Node<T> newNode = new Node<>(value);
        if (isEmpty())
        {
            firstElement = newNode;
        } else
        {
            Node<T> lastElement = lastElement();
            lastElement.next = newNode;
        }
        return true;
    }

    private Node<T> lastElement()
    {
        if (isEmpty())
        {
            return null;
        }
        Node<T> current = firstElement;
        while (current.next != null)
        {
            current = current.next;
        }
        return current;
    }

    public boolean remove(T value)
    {
        if (value == null || isEmpty())
        {
            return false;
        }

        if (firstElement.value.equals(value))
        {
            return this.removeFirst();
        }

        Node<T> current = firstElement;
        while (current.next != null && !current.next.value.equals(value))
        {
            current = current.next;
        }

        if (current.next == null)
        {
            return false;
        }

        current.next = current.next.next;
        return true;
    }

    public T getElement(Criteria<T> criteria)
    {
        if (isEmpty() || criteria == null)
        {
            return null;
        }
        Node<T> current = firstElement;
        while (current != null)
        {
            if (criteria.match(current.value))
            {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public T getElement(Object obj)
    {
        if (isEmpty() || obj == null)
        {
            return null;
        }
        Node<T> current = firstElement;
        while (current != null)
        {
            if (current.value.equals(obj))
            {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public LinkedList<T> getElements(Criteria<T> criteria)
    {
        LinkedList<T> foundList = new LinkedList<>();

        if (isEmpty() || criteria == null)
        {
            return foundList;
        }

        Node<T> current = firstElement;
        while (current != null)
        {
            if (current.value != null && criteria.match(current.value))
            {
                foundList.addByLast(current.value);
            }
            current = current.next;
        }
        return foundList;
    }

    public boolean contains(Criteria<T> criteria)
    {
        if (isEmpty() || criteria == null)
        {
            return false;
        }
        Node<T> current = firstElement;
        while (current != null)
        {
            if (criteria.match(current.value))
            {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean contains(Object obj)
    {
        if (isEmpty() || obj == null)
        {
            return false;
        }
        Node<T> current = firstElement;
        while (current != null)
        {
            if (current.value.equals(obj))
            {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean update(T elementoActualizar, Criteria<T> criteria, Consumer<T> updateAction)
    {
        if (isEmpty() || criteria == null || updateAction == null)
        {
            return false;
        }

        Node<T> current = firstElement;
        while (current != null)
        {
            if (criteria.match(current.value))
            {
                updateAction.accept(current.value);
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean update(T elementUpdate)
    {
        if (this.isEmpty())
        {
            return false;
        }

        Node<T> current = firstElement;
        while (current != null)
        {
            if (current.value.equals(elementUpdate))
            {
                current.value = elementUpdate;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public String getElementsToString()
    {
        StringBuilder descriptionElements = new StringBuilder();
        if (isEmpty())
        {
            return null;
        }

        Node<T> current = firstElement;
        while (current != null)
        {
            descriptionElements.append("\n");
            descriptionElements.append(current.value.toString());
            current = current.next;
        }
        return descriptionElements.toString();
    }

    //Aqui se usa un arrayList para ordenar los datos en base a un comparator despues   
    public ArrayList<T> getAllElementInInterval(TimeIntervalCriteria<T> intervalCriteria, LocalDateTime startTime, LocalDateTime endTime)
    {
        if (firstElement == null || intervalCriteria == null)
        {
            return new ArrayList<>(); // Devolver un ArrayList vacío si la lista está vacía o los criterios son nulos
        }
        ArrayList<T> elementsWithinInterval = new ArrayList<>();
        Node<T> current = firstElement;
        while (current != null)
        {
            if (intervalCriteria.isWithinInterval(current.value, startTime, endTime))
            {
                elementsWithinInterval.add(current.value);
            }
            current = current.next;
        }

        return elementsWithinInterval;
    }

    public boolean removeFirst()
    {
        if (isEmpty())
        {
            return false;
        }
        this.firstElement = this.firstElement.next;
        return true;
    }

    public T getElementAt(int index)
    {
        if (isEmpty())
        {
            return null;
        }
        return this.searchAt(index).value;
    }

    public boolean isEmpty()
    {
        return (this.firstElement == null);
    }

    public void clear()
    {
        this.firstElement = null;
    }

    private Node<T> searchAt(int position)
    {
        if (isEmpty() || position < 0)
        {
            return null;
        }

        Node<T> current = firstElement;
        int count = 0;
        while (current != null && count < position)
        {
            current = current.next;
            count++;
        }

        return current;
    }

    public T getAt(int index)
    {
        Node<T> getNode = this.searchAt(index);
        return (getNode != null) ? getNode.value : null;
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
