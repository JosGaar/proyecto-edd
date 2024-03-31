package proyecto;

@FunctionalInterface
interface Criteria<T> {
    boolean match(T value);
}