package my.plate_number_generator.util.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RegistrationCodeGeneratorImplTest {
    @InjectMocks
    private RegistrationCodeGeneratorImpl registrationCodeGenerator;

    @Test
    void generateNext() {
        int expected = 2;
        int actual = registrationCodeGenerator.generateNext(1);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void generateNextLoop() {
        int expected = 1;
        int actual = registrationCodeGenerator.generateNext(999);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void generateRandomMinNumber() {
        int registrationCode = registrationCodeGenerator.generateRandom();
        assert registrationCode >= RegistrationCodeGeneratorImpl.MIN_REGISTRATION_CODE;
    }

    @Test
    void generateRandomMaxNumber() {
        int registrationCode = registrationCodeGenerator.generateRandom();
        assert registrationCode <= RegistrationCodeGeneratorImpl.MAX_REGISTRATION_CODE;
    }
}