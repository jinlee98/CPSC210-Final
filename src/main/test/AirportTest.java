package test;

import airport.Plane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class AirportTest {
    airport.Airport vancouverInternationalAirport;
    Plane boeing;

    @BeforeEach
    public void setUp() {
        vancouverInternationalAirport = new airport.Airport();
        boeing = new Plane("Boeing");
    }


    @Test
    public void testScheduleDepartureAtAvailableTime() {
        assertTrue(vancouverInternationalAirport.makeNewDeparture(boeing, 15));
        assertTrue(vancouverInternationalAirport.verifyDeparture(boeing, 15));
    }

    @Test
    public void scheduleMultipleDeparturesOutOfOrder() {

        Plane boeing10 = new Plane("Boeing 10");
        boolean boeing10Scheduled = vancouverInternationalAirport.makeNewDeparture(boeing10, 10);
        boolean boeing10Verified = vancouverInternationalAirport.verifyDeparture(boeing10, 10);
        Plane airbus9 = new Plane("Airbus 9");
        boolean airbus9Booked = vancouverInternationalAirport.makeNewDeparture(airbus9, 9);
        boolean airbus9Verified = vancouverInternationalAirport.verifyDeparture(airbus9, 9);
        Plane douglas15 = new Plane("Douglas 15");
        boolean douglas15Booked = vancouverInternationalAirport.makeNewDeparture(douglas15, 15);
        boolean douglas15Verified = vancouverInternationalAirport.verifyDeparture(douglas15, 15);

        assertTrue(boeing10Scheduled);
        assertTrue(airbus9Booked);
        assertTrue(douglas15Booked);

        assertTrue(boeing10Verified);
        assertTrue(airbus9Verified);
        assertTrue(douglas15Verified);
    }

    @Test
    public void scheduleMultipleDeparturesOutOfOrderRefactored() {
        Plane boeing10 = new Plane("Boeing 10");
        Plane airbus9 = new Plane("Airbus 9");
        Plane douglas15 = new Plane("Douglas 15");

        boolean boeing10Booked = vancouverInternationalAirport.makeNewDeparture(boeing10, 10);
        boolean airbus9Booked = vancouverInternationalAirport.makeNewDeparture(airbus9, 9);
        boolean douglas15Booked = vancouverInternationalAirport.makeNewDeparture(douglas15, 15);

        assertTrue(boeing10Booked);
        assertTrue(airbus9Booked);
        assertTrue(douglas15Booked);

        boolean boeing10Verified = vancouverInternationalAirport.verifyDeparture(boeing10, 10);
        boolean airbus9Verified = vancouverInternationalAirport.verifyDeparture(airbus9, 9);
        boolean douglas15Verified = vancouverInternationalAirport.verifyDeparture(douglas15, 15);

        assertTrue(boeing10Verified);
        assertTrue(airbus9Verified);
        assertTrue(douglas15Verified);
    }


    @Test public void confirmUnscheduledTimeByName() {
        assertTrue(vancouverInternationalAirport.makeNewDeparture(boeing, 15));
        assertTrue(vancouverInternationalAirport.verifyDeparture(boeing, 15));
    }

    @Test
    public void testScheduleDepartureAtTakenTime() {
        boolean madeDeparture = vancouverInternationalAirport.makeNewDeparture(boeing, 15);
        assertTrue(madeDeparture);
        assertTrue(vancouverInternationalAirport.verifyDeparture(boeing, 15));

        Plane p = new Plane("Just a Plane");
        assertTrue(vancouverInternationalAirport.makeNewDeparture(p, 15));

        assertTrue(vancouverInternationalAirport.verifyDeparture(p, 15));
        assertFalse(vancouverInternationalAirport.verifyDeparture(boeing, 15));
    }

    @Test
    public void testEarliestDeparture() {
        assertTrue(vancouverInternationalAirport.makeNewDeparture(boeing, 5));
        assertTrue(vancouverInternationalAirport.verifyDeparture(boeing, 5));
    }

    @Test
    public void testLatestDeparture() {
        assertTrue(vancouverInternationalAirport.makeNewDeparture(boeing, 23));
        assertTrue(vancouverInternationalAirport.verifyDeparture(boeing, 23));
    }

    @Test
    public void saveTest() throws FileNotFoundException {
        File test = new File("test.txt");
        Scanner tester = new Scanner(test);

        assertEquals("cheese", tester.nextLine());
    }

}