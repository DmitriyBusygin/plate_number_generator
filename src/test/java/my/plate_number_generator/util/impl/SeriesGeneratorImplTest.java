package my.plate_number_generator.util.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SeriesGeneratorImplTest {
    @InjectMocks
    private SeriesGeneratorImpl seriesGenerator;

    @Test
    void generateNextThirdLetter() {
        String currentSeries = "АВЕ";
        String expected = "АВК";
        String actual = seriesGenerator.generateNext(currentSeries);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void generateNextSecondAndThirdLetter() {
        String currentSeries = "ВТХ";
        String expected = "ВУА";
        String actual = seriesGenerator.generateNext(currentSeries);

        Assertions.assertEquals(expected, actual);
    }


    @Test
    void generateNextAllLetters() {
        String currentSeries = "ВХХ";
        String expected = "ЕАА";
        String actual = seriesGenerator.generateNext(currentSeries);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void generateNextLoopAllLetters() {
        String currentSeries = "ХХХ";
        String expected = "ААА";
        String actual = seriesGenerator.generateNext(currentSeries);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void generateRandomHappyFlow() {
        int expectedLength = 3;
        String actual = seriesGenerator.generateRandom();
        Assertions.assertEquals(expectedLength, actual.length());
    }
}