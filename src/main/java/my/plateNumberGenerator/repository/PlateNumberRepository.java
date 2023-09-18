package my.plateNumberGenerator.repository;

import my.plateNumberGenerator.entity.PlateNumber;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PlateNumberRepository extends CrudRepository<PlateNumber, Integer> {
    /**
     * Проверяет наличие автомобильного номера в БД.
     *
     * @param series           Серия номера (пример АБВ, КУМ, ...)
     * @param registrationCode Регистрационный номер (пример 23, 442, ...)
     * @param regionCode       Код региона регистрации (78, 116, 2, ...)
     * @param country          Страна регистрации (RUS)
     * @return true автомобильный номер уже существует в БД; false автомобильного номера нет в БД
     */
    boolean existsBySeriesAndRegistrationCodeAndRegionCodeAndCountry(String series, Integer registrationCode, Integer regionCode, String country);

    /**
     * Возвращает последний добавленный автомобильный номер в БД.
     *
     * @return Объект последнего добавленного автомобильного номера
     */
    Optional<PlateNumber> findTopByOrderByIdDesc();
}
