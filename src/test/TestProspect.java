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
    void testGetDateProspection() throws Exception {
        Prospect prospect = new Prospect(1,
                "test",
                "42",
                "Rue test",
                "42042",
                "Testville",
                "4242424242",
                "test@test.com",
                "test",
                LocalDate.parse("27/02/2024",DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                "Oui");
        assertEquals(prospect.getDateProspection(),LocalDate.parse("27/02/2024",DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }
    @Test
    void testSetDateProspection() throws Exception {
        Prospect prospect = new Prospect(1,
                "test",
                "42",
                "Rue test",
                "42042",
                "Testville",
                "4242424242",
                "test@test.com",
                "test",
                LocalDate.parse("27/02/2024",DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                "Oui");
        prospect.setDateProspection(LocalDate.parse("28/02/2024",DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        assertEquals(prospect.getDateProspection(),LocalDate.parse("28/02/2024",DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }
    @ParameterizedTest
    @ValueSource(strings = {"Oui","Non"})
    void testGetInterresse(String interet) throws Exception {
        Prospect prospect = new Prospect(1,
                "test",
                "42",
                "Rue test",
                "42042",
                "Testville",
                "4242424242",
                "test@test.com",
                "test",
                LocalDate.parse("27/02/2024",DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                interet);
        assertEquals(interet,prospect.getInteresse());
    }
    @Test
    void testSetInteresse() throws Exception {
        Prospect prospect = new Prospect(1,
                "test",
                "42",
                "Rue test",
                "42042",
                "Testville",
                "4242424242",
                "test@test.com",
                "test",
                LocalDate.parse("27/02/2024",DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                "Oui");
        prospect.setInteresse("Non");
        assertEquals("Non",prospect.getInteresse());
    }
    @Test
    void testSetInterresseIncorrect(){
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
