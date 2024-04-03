package test;

import exception.ExceptionMetier;
import metier.Client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestClient {
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
    void testSetChiffreDAffaireTropPetit(){
        assertThrows(ExceptionMetier.class,()->new Client(1,
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
    }
    @Test
    void testSetNbEmployeTropPetit(){
        assertThrows(ExceptionMetier.class,()->new Client(1,
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
    }
}
