package ar.edu.utn.frc.tup.lciii.dtos.common;

import lombok.Getter;
import lombok.Setter;

public class CountryDTO {
    @Getter @Setter
    private String code;
    @Getter @Setter
    private String name;

    public CountryDTO () {

    }

    public CountryDTO (String code, String name) {
        this.code = code;
        this.name = name;
    }
}
