package test;

import exception.ExceptionMetier;
import metier.Client;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class TestSociete {

    @Test
    void testGetIdentifiant() throws ExceptionMetier {
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
    void testGetRaisonSociale()throws ExceptionMetier {
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
    void testGetNumeroRue()throws ExceptionMetier {
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
    void testGetNomRue()throws ExceptionMetier {
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
    void testGetCodePostal()throws ExceptionMetier {
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
    void testGetVille()throws ExceptionMetier {
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
    void testGetTelephone()throws ExceptionMetier {
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
    void testGetMail()throws ExceptionMetier {
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

    @ParameterizedTest
    @ValueSource(strings = {"test","test@test","test.com"})
    void testSetMailErreur(String mail){
        Exception exception = assertThrows(ExceptionMetier.class,()->new Client(1,
                "test",
                "42",
                "Rue test",
                "42042",
                "Testville",
                "4242424242",
                mail,
                "test",
                2048,
                42));
        assertEquals("Email invalide : doit être au format [adresse]@[mail].[domaine]",exception.getMessage());
    }

    @Test
    void testGetCommentaire() throws ExceptionMetier {
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
}

