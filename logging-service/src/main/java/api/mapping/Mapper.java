package api.mapping;

public interface Mapper<T, R> {
    T mapTo(R source);

    R mapFrom(T source);
}
