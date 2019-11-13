package ui;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

class MainTest {

    @Test
    public void weatherTest() {
        try {
            AirportWeather.checkWeather();
        } catch (IOException e) {
            fail();
        }
    }

}