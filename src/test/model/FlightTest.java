package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FlightTest {
    AirportDeparture yvr;
    Plane boeing;
    Plane delta;

    @BeforeEach
    public void setUp() {
        yvr = new AirportDeparture();
        boeing = new Plane("Boeing", yvr);
        delta = new Plane("delta", yvr);
    }

    @Test
    public void testVerifyForNoPlane() {
        Plane bad = null;
        assertFalse(yvr.verifyDeparture(bad, 12));
    }


    @Test
    public void testScheduleDepartureAtAvailableTime() {
        assertTrue(yvr.makeRegDeparture(boeing, 15));
        assertTrue(yvr.verifyDeparture(boeing, 15));
    }

    @Test
    public void scheduleMultipleDeparturesOutOfOrder() {

        Plane boeing10 = new Plane("Boeing 10", yvr);
        boolean boeing10Scheduled = yvr.makeRegDeparture(boeing10, 10);
        boolean boeing10Verified = yvr.verifyDeparture(boeing10, 10);
        Plane airbus9 = new Plane("Airbus 9", yvr);
        boolean airbus9Booked = yvr.makeRegDeparture(airbus9, 9);
        boolean airbus9Verified = yvr.verifyDeparture(airbus9, 9);
        Plane douglas15 = new Plane("Douglas 15", yvr);
        boolean douglas15Booked = yvr.makeRegDeparture(douglas15, 15);
        boolean douglas15Verified = yvr.verifyDeparture(douglas15, 15);

        assertTrue(boeing10Scheduled);
        assertTrue(airbus9Booked);
        assertTrue(douglas15Booked);

        assertTrue(boeing10Verified);
        assertTrue(airbus9Verified);
        assertTrue(douglas15Verified);
    }

    @Test
    public void scheduleMultipleDeparturesOutOfOrderRefactored() {
        Plane boeing10 = new Plane("Boeing 10", yvr);
        Plane airbus9 = new Plane("Airbus 9", yvr);
        Plane douglas15 = new Plane("Douglas 15", yvr);

        boolean boeing10Booked = yvr.makeRegDeparture(boeing10, 10);
        boolean airbus9Booked = yvr.makeRegDeparture(airbus9, 9);
        boolean douglas15Booked = yvr.makeRegDeparture(douglas15, 15);

        assertTrue(boeing10Booked);
        assertTrue(airbus9Booked);
        assertTrue(douglas15Booked);

        boolean boeing10Verified = yvr.verifyDeparture(boeing10, 10);
        boolean airbus9Verified = yvr.verifyDeparture(airbus9, 9);
        boolean douglas15Verified = yvr.verifyDeparture(douglas15, 15);

        assertTrue(boeing10Verified);
        assertTrue(airbus9Verified);
        assertTrue(douglas15Verified);
    }

    @Test public void confirmUnscheduledTimeByName() {
        assertTrue(yvr.makeRegDeparture(boeing, 15));
        assertTrue(yvr.verifyDeparture(boeing, 15));
    }

    @Test
    public void testScheduleDepartureAtTakenTime() {
        boolean madeDeparture = yvr.makeRegDeparture(boeing, 15);
        assertTrue(madeDeparture);
        assertTrue(yvr.verifyDeparture(boeing, 15));

        Plane p = new Plane("Just a Plane", yvr);
        assertTrue(yvr.makeRegDeparture(p, 12));

        assertTrue(yvr.verifyDeparture(p, 12));
        assertFalse(yvr.verifyDeparture(boeing, 12));
    }

    @Test
    public void testConfirmDeparture() {
        boeing.setDepartureTime(12);
        assertEquals(12, boeing.confirmDeparture());
    }

    @Test
    public void testEarliestDeparture() {
        assertTrue(yvr.makeRegDeparture(boeing, 5));
        assertTrue(yvr.verifyDeparture(boeing, 5));
    }

    @Test
    public void testLatestDeparture() {
        assertTrue(yvr.makeRegDeparture(boeing, 23));
        assertTrue(yvr.verifyDeparture(boeing, 23));
    }

    @Test
    public void testHash() {
        yvr.makeRegDeparture(boeing, 10);
        assertSame(boeing, yvr.getPlane(10));
        assertTrue(boeing.equals(yvr.getPlane(10)));
        assertFalse(yvr.getPlane(10).equals(null));
        Plane delta = new Plane("delta", yvr);
        delta = boeing;
        assertEquals(delta.confirmDeparture(), 10);
    }

    @Test
    public void testOneToOne() {
        yvr.makeRegDeparture(boeing, 10);
        yvr.removeDeparture(10);
        assertFalse(yvr.findFlight(boeing));
    }

    @Test
    public void testGetAirport() {
        assertSame(yvr, boeing.getAirportDeparture());
    }

    @Test
    public void testSetAirport() {
        AirportDeparture abbotsford = new AirportDeparture();
        boeing.setAirportDeparture(abbotsford);
        assertSame(abbotsford, boeing.getAirportDeparture());
    }

    @Test
    public void testPrintName() {
        assertTrue(boeing.printName());
    }

    @Test
    public void testPrinter() {
        assertTrue(yvr.printDepartures());
    }

    @Test
    public void testUnavailable() {
        assertFalse(yvr.makeRegDeparture(boeing, 1200));
    }

    @Test
    public void testAlreadyBookedException() {
        Plane delta = new Plane("Delta", yvr);
        yvr.makeRegDeparture(delta, 12);
        assertFalse(yvr.makeRegDeparture(boeing, 12));
    }

    @Test
    public void testUrgentOverRegular() {
        yvr.makeRegDeparture(delta, 15);
        assertTrue(yvr.makeUrgDeparture(boeing, 15));
        assertTrue(yvr.verifyDeparture(boeing, 15));
    }

    @Test
    public void testOutsideTime() {
        assertFalse(yvr.makeUrgDeparture(boeing, 9999));
    }
}
