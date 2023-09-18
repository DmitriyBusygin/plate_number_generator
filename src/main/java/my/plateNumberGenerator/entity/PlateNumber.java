package my.plateNumberGenerator.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "numbers")
public class PlateNumber {
    private static final int REGION_CODE = 116;
    private static final String COUNTY = "RUS";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "series")
    private String series;

    @Column(name = "registration_code")
    private Integer registrationCode;

    @Builder.Default()
    @Column(name = "region_code")
    private Integer regionCode = REGION_CODE;

    @Builder.Default()
    @Column(name = "country")
    private String country = COUNTY;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlateNumber that = (PlateNumber) o;
        return Objects.equals(series, that.series) && Objects.equals(registrationCode, that.registrationCode) && Objects.equals(regionCode, that.regionCode) && Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(series, registrationCode, regionCode, country);
    }

    @Override
    public String toString() {
        return "PlateNumber{" +
               "id=" + id +
               ", series='" + series + '\'' +
               ", registrationCode=" + registrationCode +
               ", regionCode=" + regionCode +
               ", country='" + country + '\'' +
               '}';
    }
}
