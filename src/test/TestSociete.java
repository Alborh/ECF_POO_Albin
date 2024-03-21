package test;

import exception.ExceptionMetier;
import log.LoggerPoo;
import metier.Client;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.*;

public class TestSociete {
    @Test
    void testRaisonSocialeVide() {
        Exception exception = assertThrows(ExceptionMetier.class,()->new Client(1,
                "",
                "42",
                "Rue test",
                "42042",
                "Testville",
                "4242424242",
                "test@test.com",
                "test",
                2048,
                42));
        assertEquals("Erreur : raison sociale ne dois pas être vide",exception.getMessage());
    }
    @Test
    void testRaisonSocialeTropGrand(){
        Exception exception = assertThrows(ExceptionMetier.class,()->new Client(1,
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                "42",
                "Rue test",
                "42042",
                "Testville",
                "4242424242",
                "test@test.com",
                "test",
                2048,
                42));
        assertEquals("Erreur : raison sociale ne dois pas faire plus de 50 caractères",exception.getMessage());
    }
    @Test
    void testNumeroRueVide(){
        Exception exception = assertThrows(ExceptionMetier.class,()->new Client(1,
                "test",
                "",
                "Rue test",
                "42042",
                "Testville",
                "4242424242",
                "test@test.com",
                "test",
                2048,
                42));
        assertEquals("Erreur : numéro rue ne dois pas être vide",exception.getMessage());
    }
    @Test
    void testNumeroRueTropGrand(){
        Exception exception = assertThrows(ExceptionMetier.class,()->new Client(1,
                "test",
                "10000000000000",
                "Rue test",
                "42042",
                "Testville",
                "4242424242",
                "test@test.com",
                "test",
                2048,
                42));
        assertEquals("Erreur : numéro rue ne dois pas faire plus de 10 cacatères",exception.getMessage());
    }
    @Test
    void testNomRueVide(){
        Exception exception = assertThrows(ExceptionMetier.class,()->new Client(1,
                "test",
                "42",
                "",
                "42042",
                "Testville",
                "4242424242",
                "test@test.com",
                "test",
                2048,
                42));
        assertEquals("Erreur : nom rue ne dois pas être vide",exception.getMessage());
    }
    @Test
    void testNomRueTropLong(){
        Exception exception = assertThrows(ExceptionMetier.class,()->new Client(1,
                "test",
                "42",
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                "42042",
                "Testville",
                "4242424242",
                "test@test.com",
                "test",
                2048,
                42));
        assertEquals("Erreur : nom rue ne dois pas faire plus de 30 caractères",exception.getMessage());
    }
    @Test
    void testCodePostalVide(){
        Exception exception = assertThrows(ExceptionMetier.class,()->new Client(1,
                "test",
                "42",
                "Rue test",
                "",
                "Testville",
                "4242424242",
                "test@test.com",
                "test",
                2048,
                42));
        assertEquals("Erreur : code postal ne dois pas être vide",exception.getMessage());
    }
    @Test
    void testCodePostalTropGrand(){
        Exception exception = assertThrows(ExceptionMetier.class,()->new Client(1,
                "test",
                "42",
                "Rue test",
                "4242424242242424242424242424242424242424",
                "Testville",
                "4242424242",
                "test@test.com",
                "test",
                2048,
                42));
        assertEquals("Erreur : code postal ne dois pas faire plus de 5 caractères",exception.getMessage());
    }
    @Test
    void testVilleVide(){
        Exception exception = assertThrows(ExceptionMetier.class,()->new Client(1,
                "test",
                "42",
                "Rue test",
                "42042",
                "",
                "4242424242",
                "test@test.com",
                "test",
                2048,
                42));
        assertEquals("Erreur : ville ne dois pas être vide",exception.getMessage());
    }
    @Test
    void testVilleTropGrand(){
        Exception exception = assertThrows(ExceptionMetier.class,()->new Client(1,
                "test",
                "42",
                "Rue test",
                "42042",
                "VIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIILLLLLLLLLLLLLLLLLLLEEEEEEEEEEEEEE",
                "4242424242",
                "test@test.com",
                "test",
                2048,
                42));
        assertEquals("Erreur : ville ne dois pas faire plus de 20 caractères",exception.getMessage());
    }
    @Test
    void testTelephoneVide(){
        Exception exception = assertThrows(ExceptionMetier.class,()->new Client(1,
                "test",
                "42",
                "Rue test",
                "42042",
                "Testville",
                "",
                "test@test.com",
                "test",
                2048,
                42));
        assertEquals("Erreur : telephone ne dois pas être vide",exception.getMessage());
    }
    @Test
    void testTelephoneTropGrand(){
        Exception exception = assertThrows(ExceptionMetier.class,()->new Client(1,
                "test",
                "42",
                "Rue test",
                "42042",
                "Testville",
                "01234567890123456789",
                "test@test.com",
                "test",
                2048,
                42));
        assertEquals("Erreur : telephone ne dois pas faire plus de 15 caractères",exception.getMessage());
    }
    @Test
    void testTelephoneTropPetit(){
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
    void testMailVide(){
        Exception exception = assertThrows(ExceptionMetier.class,()->new Client(1,
                "test",
                "42",
                "Rue test",
                "42042",
                "Testville",
                "4242424242",
                "",
                "test",
                2048,
                42));
        assertEquals("Erreur : mail ne dois pas être vide",exception.getMessage());
    }
    @Test
    void testMailTropGrand(){
        Exception exception = assertThrows(ExceptionMetier.class,()->new Client(1,
                "test",
                "42",
                "Rue test",
                "42042",
                "Testville",
                "4242424242",
                "GGGGGGGGGGGGGGUUUUUUUUUUUUUUAAAAAAA@JJJJJJJJJJJJIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIUUUUUUUUUUU.AAAAAAAAAAAAAAAAAAA",
                "test",
                2048,
                42));
        assertEquals("Erreur : mail ne dois pas plus de 50 caractères",exception.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings = {"test","test.test","test@test"})
    void testMailMauvaisFormat(String mail){
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
    void testCommentaireTropLong(){
        Exception exception = assertThrows(ExceptionMetier.class,()->new Client(1,
                "test",
                "42",
                "Rue test",
                "42042",
                "Testville",
                "4242424242",
                "test@test.com",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                2048,
                42));
        assertEquals("Erreur : commentaire ne dois pas faire plus de 100 caractères",exception.getMessage());
    }
}

