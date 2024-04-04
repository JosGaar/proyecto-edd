package proyecto;

@FunctionalInterface
public interface Criteria<T> {
    boolean match(T value);
}