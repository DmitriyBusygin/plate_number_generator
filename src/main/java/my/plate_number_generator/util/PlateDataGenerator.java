package my.plate_number_generator.util;

public interface PlateDataGenerator<T> {
    T generateRandom();
    T generateNext(T currentData);
}
