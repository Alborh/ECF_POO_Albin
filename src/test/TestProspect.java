package test;

import exception.ExceptionMetier;
import metier.Prospect;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
public class TestProspect {
    @Test
    void testDateProspectionNull(){
        Exception exception = assertThrows(ExceptionMetier.class,()->new Prospect(1,
                "test",
                "42",
                "Rue test",
                "42042",
                "Testville",
                "4242424242",
                "test@test.com",
                "test",
                null,
                "Peut-être"));
        assertEquals("Erreur : la valeur Date prospection ne dois pas être vide",exception.getMessage());
    }
    @Test
    void testInteressseide(){
        Exception exception = assertThrows(ExceptionMetier.class,()->new Prospect(1,
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
        assertEquals("Erreur : la valeur interesse ne dois pas être nulle",exception.getMessage());
    }
    @Test
    void testInterresseIncorrect(){
        Exception exception = assertThrows(ExceptionMetier.class,()->new Prospect(1,
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
        assertEquals("la valeur interesse doit être Oui ou Non",exception.getMessage());
    }
}
