package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegularFlightTest extends FlightTest {

    @BeforeEach
    public void setUp() {
        yvr = new model.RegularFlight();
        boeing = new Plane("Boeing", yvr);
    }

    @Test
    public void testUnavailable() {
        assertFalse(yvr.makeNewDeparture(boeing, 1200));
    }

    @Test
    public void testAlreadyBookedException() {
        Plane delta = new Plane("Delta", yvr);
        yvr.makeNewDeparture(delta, 12);
        assertFalse(yvr.makeNewDeparture(boeing, 12));
    }

}