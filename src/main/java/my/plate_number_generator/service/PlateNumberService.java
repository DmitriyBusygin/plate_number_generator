package my.plate_number_generator.service;

import my.plate_number_generator.entity.PlateNumber;
import my.plate_number_generator.repository.PlateNumberRepository;
import my.plate_number_generator.util.impl.RegistrationCodeGeneratorImpl;
import my.plate_number_generator.util.impl.SeriesGeneratorImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static my.plate_number_generator.util.impl.RegistrationCodeGeneratorImpl.MAX_REGISTRATION_CODE;
import static my.plate_number_generator.util.impl.RegistrationCodeGeneratorImpl.MIN_REGISTRATION_CODE;

@Service
public class PlateNumberService {
    private final PlateNumberRepository plateNumberRepository;
    private final RegistrationCodeGeneratorImpl registrationCodeGenerator;
    private final SeriesGeneratorImpl seriesGenerator;

    public PlateNumberService(PlateNumberRepository plateNumberRepository, RegistrationCodeGeneratorImpl registrationCodeGenerator, SeriesGeneratorImpl seriesGenerator) {
        this.plateNumberRepository = plateNumberRepository;
        this.registrationCodeGenerator = registrationCodeGenerator;
        this.seriesGenerator = seriesGenerator;
    }

    /**
     * Сохраняет автомобильный номер в БД
     *
     * @param newPlateNumber автомобильный номер
     */
    public void save(PlateNumber newPlateNumber) {
        plateNumberRepository.save(newPlateNumber);
    }

    /**
     * Генерирует случайный новый автомобильный номер. Если такой номер уже существует в БД, то генерирует новый.
     *
     * @return новый автомобильный номер, сохраненный в БД
     */
    public PlateNumber createRandomPlateNumber() {
        PlateNumber newPlateNumber = generateNewPlateNumber();
        while (isExist(newPlateNumber)) {
            newPlateNumber = generateNewPlateNumber();
        }

        save(newPlateNumber);
        return newPlateNumber;
    }

    /**
     * Генерирует следующий новый автомобильный номер, получая данные последнего из БД.
     * Если новый номер уже существует в БД, то генерирует следующий по порядку.
     *
     * @param lastPlateNumber последний автомобильный номер, от которого нужно инкрементироваться
     * @return новый автомобильный номер, сохраненный в БД
     */
    public PlateNumber createNextPlateNumber(Optional<PlateNumber> lastPlateNumber) {
        if (lastPlateNumber.isEmpty()) {
            return createRandomPlateNumber();
        }

        int lastRegistrationCode = lastPlateNumber.get().getRegistrationCode();
        String lastSeries = lastPlateNumber.get().getSeries();

        int newRegistrationCode = registrationCodeGenerator.generateNext(lastRegistrationCode);
        String newSeries = lastSeries;
        if (lastRegistrationCode == MAX_REGISTRATION_CODE && newRegistrationCode == MIN_REGISTRATION_CODE) {
            newSeries = seriesGenerator.generateNext(lastSeries);
        }

        PlateNumber newPlateNumber = PlateNumber.builder()
                .registrationCode(newRegistrationCode)
                .series(newSeries)
                .build();

        while (isExist(newPlateNumber)) {
            newPlateNumber = createNextPlateNumber(Optional.of(newPlateNumber));
        }

        save(newPlateNumber);
        return newPlateNumber;
    }

    /**
     * Возвращает последний автомобильный номер из БД
     *
     * @return последний автомобильный номер
     */
    public Optional<PlateNumber> getLastPlateNumber() {
        return plateNumberRepository.findTopByOrderByIdDesc();
    }

    /**
     * Билдер автомобильного номера
     *
     * @return новый объект автомобильного номера
     */
    private PlateNumber generateNewPlateNumber() {
        return PlateNumber.builder()
                .registrationCode(registrationCodeGenerator.generateRandom())
                .series(seriesGenerator.generateRandom())
                .build();
    }

    /**
     * Проверяет наличие переданного автомобильного номера в БД
     *
     * @param checkNumber автомобильный номер, требующий проверки
     * @return true - такой автомобльный номер уже существует в БД; false - такого номера нет в БД
     */
    private boolean isExist(PlateNumber checkNumber) {
        return plateNumberRepository.existsBySeriesAndRegistrationCodeAndRegionCodeAndCountry(
                checkNumber.getSeries(),
                checkNumber.getRegistrationCode(),
                checkNumber.getRegionCode(),
                checkNumber.getCountry());
    }
}
