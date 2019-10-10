package test;

import model.Plane;
import org.junit.jupiter.api.BeforeEach;

public class RegularFlightTest extends FlightTest {

    @BeforeEach
    public void setUp() {
        yvr = new model.RegularFlight();
        boeing = new Plane("Boeing");
    }

}