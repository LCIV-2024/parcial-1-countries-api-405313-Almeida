package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.dtos.common.CountryDTO;
import ar.edu.utn.frc.tup.lciii.model.Country;
import ar.edu.utn.frc.tup.lciii.service.CountryService;
import org.apache.coyote.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CountryControllerTest {
    @Mock
    private CountryService service;
    @InjectMocks
    private CountryController sut;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void obtenerPaises() {
        List<Country> mockCountries = new ArrayList<>();
        when(service.getAllCountries()).thenReturn(mockCountries);

        ResponseEntity<List<Country>> response = sut.obtenerPaises();

        assertEquals(200,response.getStatusCodeValue());
        assertEquals(mockCountries, response.getBody());
        verify(service, times(1)).getAllCountries();
    }

    @Test
    void getCountries() {
        List<Country> mockCountries = new ArrayList<>();
        when(service.getAllCountries()).thenReturn(mockCountries);

        ResponseEntity<List<Country>> response = sut.obtenerPaises();

        assertEquals(200,response.getStatusCodeValue());
        assertEquals(mockCountries, response.getBody());
        verify(service, times(1)).getAllCountries();
    }
    @Test
    void getCountriesRegion() {
        List<CountryDTO> mockCountries = new ArrayList<>();
        when(service.getAllCountriesForRegion(anyString())).thenReturn(mockCountries);

        ResponseEntity<List<CountryDTO>> response = sut.getCountriesRegion("Europe");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockCountries, response.getBody());
        verify(service, times(1)).getAllCountriesForRegion("Europe");
    }

    @Test
    void getCountriesLanguage() {
        List<CountryDTO> mockCountries = new ArrayList<>();
        when(service.getAllCountriesForLanguage(anyString())).thenReturn(mockCountries);

        ResponseEntity<List<CountryDTO>> response = sut.getCountriesLanguage("English");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockCountries, response.getBody());
        verify(service, times(1)).getAllCountriesForLanguage("English");
    }

    @Test
    void getCountryMoreBorder() {
        CountryDTO mockCountry = new CountryDTO();
        when(service.getCountryMoreBorders()).thenReturn(mockCountry);

        ResponseEntity<CountryDTO> response = sut.getCountryMoreBorder();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockCountry, response.getBody());
        verify(service, times(1)).getCountryMoreBorders();
    }


    @Test
    void getCountries_NameAndCode() {
        List<CountryDTO> mockCountries = new ArrayList<>();
        when(service.getAllCountriesDTO("ARG", "Argentina")).thenReturn(mockCountries);

        ResponseEntity<List<CountryDTO>> response = sut.getCountries("ARG", "Argentina");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockCountries, response.getBody());
        verify(service, times(1)).getAllCountriesDTO("ARG", "Argentina");
    }

    @Test
    void getCountries_Name() {
        List<CountryDTO> mockCountries = new ArrayList<>();
        when(service.getAllCountriesDTO(null, "Argentina")).thenReturn(mockCountries);

        ResponseEntity<List<CountryDTO>> response = sut.getCountries(null, "Argentina");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockCountries, response.getBody());
        verify(service, times(1)).getAllCountriesDTO(null, "Argentina");
    }

    @Test
    void getCountries_Code() {
        List<CountryDTO> mockCountries = new ArrayList<>();
        when(service.getAllCountriesDTO("BRA", null)).thenReturn(mockCountries);

        ResponseEntity<List<CountryDTO>> response = sut.getCountries("BRA", null);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockCountries, response.getBody());
        verify(service, times(1)).getAllCountriesDTO("BRA", null);
    }

    @Test
    void getCountries_NotNameAndCode() {
        List<CountryDTO> mockCountries = new ArrayList<>();
        when(service.getAllCountriesDTO(null, null)).thenReturn(mockCountries);

        ResponseEntity<List<CountryDTO>> response = sut.getCountries(null, null);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockCountries, response.getBody());
        verify(service, times(1)).getAllCountriesDTO(null, null);
    }
}