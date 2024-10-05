package ar.edu.utn.frc.tup.lciii.service;

import ar.edu.utn.frc.tup.lciii.repository.CountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

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
    @Test
    void getAllCountries() {
        
    }

    @Test
    void getAllCountriesDTO() {
    }

    @Test
    void getAllCountriesForRegion() {
    }

    @Test
    void getAllCountriesForLanguage() {
    }

    @Test
    void getCountryMoreBorders() {
    }
}