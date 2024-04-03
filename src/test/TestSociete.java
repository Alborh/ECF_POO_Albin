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
    void testParamValides(){
        assertDoesNotThrow(()->new Client(1,
                "test",
                "42",
                "Rue test",
                "42042",
                "Testville",
                "4242424242",
                "test@test.com",
                "test",
                2048,
                42));
    }
    @Test
    void testRaisonSocialeVide() {
        assertThrows(ExceptionMetier.class,()->new Client(1,
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
    }
    @Test
    void testRaisonSocialeTropGrand(){
        assertThrows(ExceptionMetier.class,()->new Client(1,
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
    }
    @Test
    void testNumeroRueVide(){
        assertThrows(ExceptionMetier.class,()->new Client(1,
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
    }
    @Test
    void testNumeroRueTropGrand(){
        assertThrows(ExceptionMetier.class,()->new Client(1,
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
    }
    @Test
    void testNomRueVide(){
        assertThrows(ExceptionMetier.class,()->new Client(1,
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
    }
    @Test
    void testNomRueTropLong(){
        assertThrows(ExceptionMetier.class,()->new Client(1,
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
    }
    @Test
    void testCodePostalVide(){
        assertThrows(ExceptionMetier.class,()->new Client(1,
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
    }
    @Test
    void testCodePostalTropGrand(){
        assertThrows(ExceptionMetier.class,()->new Client(1,
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
    }
    @Test
    void testVilleVide(){
        assertThrows(ExceptionMetier.class,()->new Client(1,
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
    }
    @Test
    void testVilleTropGrand(){
        assertThrows(ExceptionMetier.class,()->new Client(1,
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
    }
    @Test
    void testTelephoneVide(){
        assertThrows(ExceptionMetier.class,()->new Client(1,
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
    }
    @Test
    void testTelephoneTropGrand(){
        assertThrows(ExceptionMetier.class,()->new Client(1,
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
    }
    @Test
    void testTelephoneTropPetit(){
        assertThrows(ExceptionMetier.class,()->new Client(1,
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
    }
    @Test
    void testMailVide(){
        assertThrows(ExceptionMetier.class,()->new Client(1,
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
    }
    @Test
    void testMailTropGrand(){
        assertThrows(ExceptionMetier.class,()->new Client(1,
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
    }
    @ParameterizedTest
    @ValueSource(strings = {"test","test.test","test@test"})
    void testMailMauvaisFormat(String mail){
        assertThrows(ExceptionMetier.class,()->new Client(1,
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
    }
    @Test
    void testCommentaireTropLong(){
        assertThrows(ExceptionMetier.class,()->new Client(1,
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
    }
}

