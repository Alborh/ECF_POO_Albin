package test;

import exception.ExceptionMetier;
import metier.Client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestClient {
    @Test
    void testGetChiffreDAffaire() throws Exception {
        Client societe = new Client(1,
                "test",
                "42",
                "Rue test",
                "42042",
                "Testville",
                "4242424242",
                "test@test.com",
                "test",
                2048,
                42);
        assertEquals(societe.getChiffreDAffaire(),2048);
    }
    @Test
    void testSetChiffreDAffaire() throws Exception {
        Client societe = new Client(1,
                "test",
                "42",
                "Rue test",
                "42042",
                "Testville",
                "4242424242",
                "test@test.com",
                "test",
                2048,
                42);
        societe.setChiffreDAffaire(4096);
        assertEquals(societe.getChiffreDAffaire(),4096);
    }
    @Test
    void testSetChiffreDAffaireTropPetit(){
        Exception exception = assertThrows(ExceptionMetier.class,()->new Client(1,
                "test",
                "42",
                "Rue test",
                "42042",
                "Testville",
                "4242424242",
                "test@test.com",
                "test",
                199,
                42));
        assertEquals("Le chiffre d'affaire doit être supérieur à 200",exception.getMessage());
    }

    @Test
    void testGetNbEmpoye() throws Exception {
        Client societe = new Client(1,
                "test",
                "42",
                "Rue test",
                "42042",
                "Testville",
                "4242424242",
                "test@test.com",
                "test",
                2048,
                42);
        assertEquals(societe.getNbEmploye(),42);
    }
    @Test
    void testSetNbEmploye() throws Exception {
        Client societe = new Client(1,
                "test",
                "42",
                "Rue test",
                "42042",
                "Testville",
                "4242424242",
                "test@test.com",
                "test",
                2048,
                42);
        societe.setNbEmploye(84);
        assertEquals(societe.getNbEmploye(),84);
    }
    @Test
    void testSetNbEmployeTropPetit(){
        Exception exception = assertThrows(ExceptionMetier.class,()->new Client(1,
                "test",
                "42",
                "Rue test",
                "42042",
                "Testville",
                "4242424242",
                "test@test.com",
                "test",
                2048,
                0));
        assertEquals("Le nombre d'employé doit être supérieur à 0",exception.getMessage());
    }
}
