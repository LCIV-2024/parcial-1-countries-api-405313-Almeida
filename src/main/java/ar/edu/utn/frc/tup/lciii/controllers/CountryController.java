package ar.edu.utn.frc.tup.lciii.controllers;
import ar.edu.utn.frc.tup.lciii.dtos.common.CountryDTO;
import ar.edu.utn.frc.tup.lciii.model.Country;
import ar.edu.utn.frc.tup.lciii.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryService countryService;

    @GetMapping("/country")
    public ResponseEntity<List<Country>> obtenerPaises(){
       List<Country> response = countryService.getAllCountries();
       return ResponseEntity.ok(response);
    }
    @GetMapping(params = {"name", "code"})
    public ResponseEntity<List<CountryDTO>> getCountries(@RequestParam(required = false) String code,
                                         @RequestParam(required = false) String name) {
        List<CountryDTO> response = countryService.getAllCountriesDTO(code, name);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/continent/{continent}")
    public ResponseEntity<List<CountryDTO>> getCountriesRegion(@RequestParam String region){
        List<CountryDTO> response = countryService.getAllCountriesForRegion(region);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/country/{language}")
    public ResponseEntity<List<CountryDTO>> getCountriesLanguage(@RequestParam String language){
        List<CountryDTO> response = countryService.getAllCountriesForLanguage(language);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/most-borders")
    public ResponseEntity<CountryDTO> getCountryMoreBorder(){
        CountryDTO response = countryService.getCountryMoreBorders();
        return ResponseEntity.ok(response);
    }


}