package my.plate_number_generator.service;

import my.plate_number_generator.entity.PlateNumber;
import my.plate_number_generator.repository.PlateNumberRepository;
import my.plate_number_generator.util.impl.RegistrationCodeGeneratorImpl;
import my.plate_number_generator.util.impl.SeriesGeneratorImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PlateNumberServiceTest {
    @Mock
    private PlateNumberRepository plateNumberRepository;
    @Mock
    private RegistrationCodeGeneratorImpl registrationCodeGenerator;
    @Mock
    private SeriesGeneratorImpl seriesGenerator;
    @InjectMocks
    private PlateNumberService plateNumberService;

    @Test
    void saveHappyFlow() {
        final int registrationCode = 123;
        final String seriesCode = "ААА";

        given(registrationCodeGenerator.generateRandom())
                .willReturn(registrationCode);
        given(seriesGenerator.generateRandom())
                .willReturn(seriesCode);
        PlateNumber plateNumber = PlateNumber.builder()
                .registrationCode(registrationCodeGenerator.generateRandom())
                .series(seriesGenerator.generateRandom())
                .build();

        plateNumberService.save(plateNumber);

        verify(plateNumberRepository)
                .save(plateNumber);
    }

    @Test
    void createRandomPlateNumberHappyFlow() {
        final int registrationCode = 123;
        final String seriesCode = "ААА";

        given(registrationCodeGenerator.generateRandom())
                .willReturn(registrationCode);
        given(seriesGenerator.generateRandom())
                .willReturn(seriesCode);
        PlateNumber plateNumber = PlateNumber.builder()
                .registrationCode(registrationCodeGenerator.generateRandom())
                .series(seriesGenerator.generateRandom())
                .build();

        plateNumberService.createRandomPlateNumber();

        verify(plateNumberRepository)
                .save(plateNumber);
    }
}