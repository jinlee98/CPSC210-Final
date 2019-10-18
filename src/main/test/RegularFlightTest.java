package test;

import model.Plane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegularFlightTest extends FlightTest {

    @BeforeEach
    public void setUp() {
        yvr = new model.RegularFlight();
        boeing = new Plane("Boeing");
    }

    @Test
    public void testUnavailable() {
        assertTrue(yvr.makeNewDeparture(boeing, 1200));

    }

}