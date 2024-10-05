package ar.edu.utn.frc.tup.lciii.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private long population;
    @Getter @Setter
    private double area;
    @Getter @Setter
    private String code;
    @Getter @Setter
    private String region;
    @Getter @Setter
    @ElementCollection
    private List<String> borders;
    @Getter @Setter
    @ElementCollection
    private Map<String, String> languages;


    public Country() {
    }

    public Country(Long id, String name, long population, double area, String code, String region, List<String> borders, Map<String, String> languages) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.area = area;
        this.code = code;
        this.region = region;
        this.borders = borders;
        this.languages = languages;
    }

    public Country(String c1, String country1, long l, double v, String region1, Object o, Object o1) {
    }
}