package ar.edu.utn.frc.tup.lciii.service;

import ar.edu.utn.frc.tup.lciii.dtos.common.CountryDTO;
import ar.edu.utn.frc.tup.lciii.model.Country;
import ar.edu.utn.frc.tup.lciii.repository.CountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CountryServiceTest {
    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private CountryService sut;
    @Mock
    private CountryRepository repository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void testGetAllCountries() {
//
//    }

//    @Test
//    void testGetAllCountriesDTO_CodeAndName() {
//
//    }
//
//
//    @Test
//    void testGetAllCountriesForRegion() {
//
//    }
//
//    @Test
//    void testGetAllCountriesForLanguage() {
//
//    }
//
//    @Test
//    void testGetCountryMoreBorders() {
//
//    }
    @Test
    void testGetAllCountries() {
        // Mock the API response
        Map<String, Object> countryData1 = new HashMap<>();
        countryData1.put("name", Map.of("common", "Country1"));
        countryData1.put("population", 1000000);
        countryData1.put("area", 50000.0);
        countryData1.put("region", "Region1");
        countryData1.put("languages", Map.of("lang1", "Language1"));
        countryData1.put("cca3", "C1");
        countryData1.put("borders", Arrays.asList("C2", "C3"));

        Map<String, Object> countryData2 = new HashMap<>();
        countryData2.put("name", Map.of("common", "Country2"));
        countryData2.put("population", 2000000);
        countryData2.put("area", 60000.0);
        countryData2.put("region", "Region2");
        countryData2.put("languages", Map.of("lang2", "Language2"));
        countryData2.put("cca3", "C2");
        countryData2.put("borders", Arrays.asList("C1"));

        List<Map<String, Object>> apiResponse = Arrays.asList(countryData1, countryData2);
        when(restTemplate.getForObject(any(String.class), any(Class.class))).thenReturn(apiResponse);

        // Mock the repository save method
        when(repository.saveAll(any())).thenReturn(Arrays.asList(new Country(), new Country()));

        // Call the service method
        List<Country> countries = sut.getAllCountries();

        // Assertions
        assertNotNull(countries);
        assertEquals(2, countries.size());
        verify(repository, times(1)).saveAll(any());
    }

    @Test
    void testGetAllCountriesDTO_WithCodeAndName() {
        Country country1 = new Country("C1", "Country1", 1000000L, 50000.0, "Region1", null, null);
        Country country2 = new Country("C2", "Country2", 2000000L, 60000.0, "Region2", null, null);
        when(repository.findByCodeOrName("C1", "Country2")).thenReturn(Arrays.asList(country1, country2));

        List<CountryDTO> countriesDTO = sut.getAllCountriesDTO("C1", "Country2");

        assertEquals(2, countriesDTO.size());
        assertEquals("Country1", countriesDTO.get(0).getName());
        assertEquals("C1", countriesDTO.get(0).getCode());
    }

    @Test
    void testGetAllCountriesDTO_WithoutCodeAndName() {
        Country country1 = new Country("C1", "Country1", 1000000L, 50000.0, "Region1", null, null);
        when(repository.findAll()).thenReturn(Arrays.asList(country1));

        List<CountryDTO> countriesDTO = sut.getAllCountriesDTO(null, null);

        assertEquals(1, countriesDTO.size());
        assertEquals("Country1", countriesDTO.get(0).getName());
        assertEquals("C1", countriesDTO.get(0).getCode());
    }

    @Test
    void testGetAllCountriesForRegion() {
        Country country1 = new Country("C1", "Country1", 1000000L, 50000.0, "Region1", null, null);
        when(repository.findByRegion("Region1")).thenReturn(Arrays.asList(country1));

        List<CountryDTO> countriesDTO = sut.getAllCountriesForRegion("Region1");

        assertEquals(1, countriesDTO.size());
        assertEquals("Country1", countriesDTO.get(0).getName());
    }

    @Test
    void testGetAllCountriesForLanguage() {
        Country country1 = new Country("C1", "Country1", 1000000L, 50000.0, "Region1", null, null);
        when(repository.findByLanguages("Language1")).thenReturn(Arrays.asList(country1));

        List<CountryDTO> countriesDTO = sut.getAllCountriesForLanguage("Language1");

        assertEquals(1, countriesDTO.size());
        assertEquals("Country1", countriesDTO.get(0).getName());
    }

    @Test
    void testGetCountryMoreBorders() {
        Country country1 = new Country("C1", "Country1", 1000000L, 50000.0, "Region1", null, Arrays.asList("C2", "C3"));
        Country country2 = new Country("C2", "Country2", 2000000L, 60000.0, "Region2", null, Arrays.asList("C1", "C3"));
        Country country3 = new Country("C3", "Country3", 3000000L, 70000.0, "Region3", null, Arrays.asList("C1", "C2", "C4", "C5", "C6", "C7", "C8", "C9", "C10", "C11", "C12", "C13", "C14", "C15", "C16"));

        when(repository.findAll()).thenReturn(Arrays.asList(country1, country2, country3));

        CountryDTO countryDTO = sut.getCountryMoreBorders();

        assertEquals("C3", countryDTO.getCode());
        assertEquals("Country3", countryDTO.getName());
    }
}