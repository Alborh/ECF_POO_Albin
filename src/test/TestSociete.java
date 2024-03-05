package test;

import exception.ExceptionMetier;
import metier.Client;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class TestSociete {

    @Test
    void testGetIdentifiant() throws Exception {
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
        assertEquals(societe.getIdentifiant(),1);
    }
    @Test
    void testSetIdentifiant() throws Exception {
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
        societe.setIdentifiant(2);
        assertEquals(societe.getIdentifiant(),2);
    }
    @Test
    void testGetRaisonSociale()throws Exception {
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
        assertEquals(societe.getRaisonSociale(),"test");
    }

    @Test
    void testGetNumeroRue()throws Exception {
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
        assertEquals(societe.getNumeroRue(),"42");
    }
    @Test
    void testSetNumeroRue() throws Exception {
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
        societe.setNumeroRue("21");
        assertEquals(societe.getNumeroRue(),"21");
    }
    @Test
    void testGetNomRue()throws Exception {
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
        assertEquals(societe.getNomRue(),"Rue test");
    }

    @Test
    void testGetCodePostal()throws Exception {
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
        assertEquals(societe.getCodePostal(),"42042");
    }
    @Test
    void testSetCodePostal() throws Exception {
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
        societe.setCodePostal("21021");
        assertEquals(societe.getCodePostal(),"21021");
    }
    @Test
    void testGetVille()throws Exception {
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
        assertEquals(societe.getVille(),"Testville");
    }
    @Test
    void testSetVille() throws Exception {
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
        societe.setVille("Testbourg");
        assertEquals(societe.getVille(),"Testbourg");
    }
    @Test
    void testGetTelephone()throws Exception {
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
        assertEquals(societe.getTelephone(),"4242424242");
    }
    @Test
    void testSetTelephone() throws Exception {
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
        societe.setTelephone("2121212121");
        assertEquals(societe.getTelephone(),"2121212121");
    }
    @Test
    void testSetTelephoneTropPetit(){
        Exception exception = assertThrows(ExceptionMetier.class,()->new Client(1,
                "test",
                "42",
                "Rue test",
                "42042",
                "Testville",
                "42",
                "test@test.com",
                "test",
                2048,
                42));
        assertEquals("Numéro de téléphone trop court : doit avoir au moins 10 chiffres",exception.getMessage());
    }

    @Test
    void testGetMail()throws Exception {
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
        assertEquals(societe.getMail(),"test@test.com");
    }
    @Test
    void testSetMail() throws Exception {
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
        societe.setMail("adresse@mail.com");
        assertEquals(societe.getMail(),"adresse@mail.com");
    }
    @Test
    void testGetCommentaire() throws Exception {
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
        assertEquals(societe.getCommentaire(),"test");
    }
    @Test
    void testSEtCommentaire() throws Exception {
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
        societe.setCommentaire("commentaire");
        assertEquals(societe.getCommentaire(),"commentaire");
    }
}

