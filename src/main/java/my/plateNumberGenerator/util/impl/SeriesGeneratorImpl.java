package my.plateNumberGenerator.util.impl;

import my.plateNumberGenerator.util.PlateDataGenerator;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class SeriesGeneratorImpl implements PlateDataGenerator<String> {
    private static final List<Character> letters = new ArrayList<>(Arrays.asList('А', 'В', 'Е', 'К', 'М', 'Н', 'О', 'Р', 'С', 'Т', 'У', 'Х'));
    private static final int NUMBER_OF_LETTERS = 3;
    private static final Random random = new Random();
    private boolean isIncrementNeeded;

    /**
     * Возвращает случайную серию (пример АБВ, КУМ, ...)
     *
     * @return случайная серия
     */
    @Override
    public String generateRandom() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < NUMBER_OF_LETTERS; i++) {
            int randomIndex = random.nextInt(letters.size());
            char randomLetter = letters.get(randomIndex);
            stringBuilder.append(randomLetter);
        }
        return stringBuilder.toString();
    }

    /**
     * Итерирует переданную серию автомобильного номера.
     * Примеры: ААА -> ААВ; АВЕ -> АВК; ЕТХ -> ЕУА; НХХ -> ОАА; ХХХ -> ААА
     *
     * @param currentData серия автомобиля
     * @return инкрементированая серия автомобиля
     */
    @Override
    public String generateNext(String currentData) {
        char first = currentData.charAt(0);
        char second = currentData.charAt(1);
        char third = currentData.charAt(2);

        isIncrementNeeded = true;
        third = incrementIfFlagEnabled(third);
        second = incrementIfFlagEnabled(second);
        first = incrementIfFlagEnabled(first);

        return String.valueOf(new char[]{first, second, third});
    }

    /**
     * Инкрементирует одну букву если включен флаг.
     * Если флаг выключен, инкрементации не произойдет.
     *
     * @param letter буква
     * @return инкрементировання буква
     */
    private char incrementIfFlagEnabled(char letter) {
        if (isIncrementNeeded) {
            if (letter == getLastCharFromLetters()) {
                letter = letters.get(0);
                isIncrementNeeded = true;
            } else {
                letter = letters.get(letters.indexOf(letter) + 1);
                isIncrementNeeded = false;
            }
        }
        return letter;
    }

    /**
     * Возвращает последнюю букву из алфавита используемых букв для серии автомобильного номера.
     *
     * @return буква
     */
    private char getLastCharFromLetters() {
        return letters.get(letters.size() - 1);
    }
}
