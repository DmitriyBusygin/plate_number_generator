package my.plate_number_generator.util.impl;

import my.plate_number_generator.util.PlateDataGenerator;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RegistrationCodeGeneratorImpl implements PlateDataGenerator<Integer> {
    public static final int MIN_REGISTRATION_CODE = 1;
    public static final int MAX_REGISTRATION_CODE = 999;
    private static final Random random = new Random();

    /**
     * Генерирует случайный регистрационный код (пример 1, 231, ...)
     * Границы номеров от 1 до 999
     * Номер 000 не существует согласно правилам РФ
     *
     * @return случайный регистрационный код
     */
    @Override
    public Integer generateRandom() {
        return random.nextInt(MIN_REGISTRATION_CODE, MAX_REGISTRATION_CODE);
    }


    /**
     * Итерирует переданный регистрационный код (пример 1 -> 2; 784 -> 785; 999 -> 1)
     *
     * @param currentData регистрационный код
     * @return инкрементированый регистрационный код
     */
    @Override
    public Integer generateNext(Integer currentData) {
        return currentData == MAX_REGISTRATION_CODE ? MIN_REGISTRATION_CODE : currentData + 1;
    }
}
