package test;

import exception.ExceptionMetier;
import metier.Client;
import metier.Prospect;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
public class TestProspect {
    @Test
    void testParamValides(){
        assertDoesNotThrow(()->new Prospect(1,
                "test",
                "42",
                "Rue test",
                "42042",
                "Testville",
                "4242424242",
                "test@test.com",
                "test",
                LocalDate.parse("27/02/2024",DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                "Oui"));
    }
    @Test
    void testDateProspectionNull(){
        assertThrows(ExceptionMetier.class,()->new Prospect(1,
                "test",
                "42",
                "Rue test",
                "42042",
                "Testville",
                "4242424242",
                "test@test.com",
                "test",
                null,
                "Oui"));
    }
    @Test
    void testInteressseide(){
        assertThrows(ExceptionMetier.class,()->new Prospect(1,
                "test",
                "42",
                "Rue test",
                "42042",
                "Testville",
                "4242424242",
                "test@test.com",
                "test",
                LocalDate.parse("27/02/2024",DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                ""));
    }
    @Test
    void testInterresseIncorrect(){
        assertThrows(ExceptionMetier.class,()->new Prospect(1,
                "test",
                "42",
                "Rue test",
                "42042",
                "Testville",
                "4242424242",
                "test@test.com",
                "test",
                LocalDate.parse("27/02/2024",DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                "Peut-être"));
    }
}
