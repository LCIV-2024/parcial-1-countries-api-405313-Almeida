package ar.edu.utn.frc.tup.lciii.service;

import ar.edu.utn.frc.tup.lciii.dtos.common.CountryDTO;
import ar.edu.utn.frc.tup.lciii.model.Country;
import ar.edu.utn.frc.tup.lciii.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryService {

        private final CountryRepository countryRepository;

        private final RestTemplate restTemplate;

        public List<Country> getAllCountries() {
                String url = "https://restcountries.com/v3.1/all";
                List<Map<String, Object>> response = restTemplate.getForObject(url, List.class);
                List<Country> countries = response.stream().map(this::mapToCountry).collect(Collectors.toList());
                countryRepository.saveAll(countries);
                return countries;
        }

        private Country mapToCountry(Map<String, Object> countryData) {
                Map<String, Object> nameData = (Map<String, Object>) countryData.get("name");
                return Country.builder()
                        .name((String) nameData.get("common"))
                        .population(((Number) countryData.get("population")).longValue())
                        .area(((Number) countryData.get("area")).doubleValue())
                        .region((String) countryData.get("region"))
                        .languages((Map<String, String>) countryData.get("languages"))
                        .code((String) countryData.get("cca3"))
                        .borders((List<String>) countryData.get("borders"))
                        .build();
        }


        private CountryDTO mapToDTO(Country country) {
                return new CountryDTO(country.getCode(), country.getName());
        }

        public List<CountryDTO> getAllCountriesDTO(String code, String name) {
                List<Country> countries;
                if ((code != null && !code.isEmpty()) || (name != null && !name.isEmpty())) {
                        countries = countryRepository.findByCodeOrName(code, name);
                } else {
                        countries = countryRepository.findAll();
                }
                return countries.stream()
                        .map(this::mapToDTO)
                        .collect(Collectors.toList());
        }

        public List<CountryDTO> getAllCountriesForRegion(String region){
                List<Country> countries = new ArrayList<>();
                if ((region != null && !region.isEmpty())) {
                        countries = countryRepository.findByRegion(region);
                }
                return countries.stream()
                        .map(this::mapToDTO)
                        .collect(Collectors.toList());
        }

        public List<CountryDTO> getAllCountriesForLanguage(String language) {
                List<Country> countries = new ArrayList<>();
                if ((language != null && !language.isEmpty())) {
                        countries = countryRepository.findByLanguages(language);
                }
                return countries.stream()
                        .map(this::mapToDTO)
                        .collect(Collectors.toList());
        }

        public CountryDTO getCountryMoreBorders(){
                List<Country> countries = new ArrayList<>();
                Country response = new Country();
                countries = countryRepository.findAll();
                for (Country country : countries){
                        if (country.getBorders().size() > 15){
                                response = country;
                        }
                }
                CountryDTO dto = new CountryDTO(response.getCode(), response.getName());
                return dto;
        }

}