package my.plateNumberGenerator.util;

public interface PlateDataGenerator<T> {
    T generateRandom();
    T generateNext(T currentData);
}
