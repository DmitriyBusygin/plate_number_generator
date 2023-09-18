package my.plate_number_generator.dto;

import lombok.Data;

@Data
public class PlateNumberDto {
    private String series;
    private Integer registrationCode;
    private Integer regionCode;
    private String country;

    /**
     * Возвращает данные автомобильного номера в формате: "C400BA 116 RUS"
     *
     * @return отформатированные данные согласно формату Тип 1
     */
    public String formatToType1() {
        return String.format("%c%03d%c%c %d %s",
                series.charAt(0),
                registrationCode,
                series.charAt(1),
                series.charAt(2),
                regionCode,
                country);
    }
}
