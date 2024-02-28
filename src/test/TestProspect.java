package test;

import exception.ExceptionMetier;
import metier.Prospect;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;
public class TestProspect {
    @Test
    void testGetDateProspection() throws ExceptionMetier, ParseException {
        Prospect prospect = new Prospect(1,
                "test",
                "42",
                "Rue test",
                "42042",
                "Testville",
                "4242424242",
                "test@test.com",
                "test",
                "27/02/2024",
                "Oui");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        assertEquals(prospect.getDateProspection(),dateFormat.parse("27/02/2024"));
    }
    @ParameterizedTest
    @ValueSource(strings = {"27-02-2024","02/27/2024","42/42/42","2024/02/27"})
    void testSetDateProspectionMauvaisFormat(String date){
        Exception exception = assertThrows(ExceptionMetier.class,()->new Prospect(1,
                "test","42",
                "Rue test",
                "42042",
                "Testville",
                "4242424242",
                "test@test.com",
                "test",
                date,
                "Oui"));
        assertEquals("Mauvais format de date (doit être jj/MM/yyyy)",exception.getMessage());
    }
    @Test
    void testGetDateFormatSQL() throws ExceptionMetier {
        Prospect prospect = new Prospect(1,
                "test",
                "42",
                "Rue test",
                "42042",
                "Testville",
                "4242424242",
                "test@test.com",
                "test",
                "27/02/2024",
                "Oui");
        assertEquals("2024-02-27",prospect.getDateFormatSQL());
    }
    @ParameterizedTest
    @ValueSource(strings = {"Oui","Non"})
    void testGetInterresse(String interet) throws ExceptionMetier {
        Prospect prospect = new Prospect(1,
                "test",
                "42",
                "Rue test",
                "42042",
                "Testville",
                "4242424242",
                "test@test.com",
                "test",
                "27/02/2024",
                interet);
        assertEquals(interet,prospect.getInteresse());
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
                "27/02/2024",
                "Peut-être"));
        assertEquals("la valeur interesse doit être Oui ou Non",exception.getMessage());
    }
}
