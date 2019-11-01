package model;

import model.Plane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public abstract class FlightTest {
    model.Airport yvr;
    Plane boeing;

    @Test
    public void testVerifyForNoPlane() {
        Plane bad = null;
        assertFalse(yvr.verifyDeparture(bad, 12));
    }

    @Test
    public void testConfirm() {
        yvr.makeNewDeparture(boeing, 12);
        assertTrue(yvr.confirmScheduledPlane(boeing.getName(), 12));
        assertFalse(yvr.confirmScheduledPlane("abc", 14));
    }

    @Test
    public void testConfirmDeparture() {
        boeing.setDepartureTime(12);
        assertEquals(12, boeing.confirmDeparture());
    }

    @Test
    public void testScheduleDepartureAtAvailableTime() {
        assertTrue(yvr.makeNewDeparture(boeing, 15));
        assertTrue(yvr.verifyDeparture(boeing, 15));
    }

    @Test
    public void scheduleMultipleDeparturesOutOfOrder() {

        Plane boeing10 = new Plane("Boeing 10");
        boolean boeing10Scheduled = yvr.makeNewDeparture(boeing10, 10);
        boolean boeing10Verified = yvr.verifyDeparture(boeing10, 10);
        Plane airbus9 = new Plane("Airbus 9");
        boolean airbus9Booked = yvr.makeNewDeparture(airbus9, 9);
        boolean airbus9Verified = yvr.verifyDeparture(airbus9, 9);
        Plane douglas15 = new Plane("Douglas 15");
        boolean douglas15Booked = yvr.makeNewDeparture(douglas15, 15);
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
        Plane boeing10 = new Plane("Boeing 10");
        Plane airbus9 = new Plane("Airbus 9");
        Plane douglas15 = new Plane("Douglas 15");

        boolean boeing10Booked = yvr.makeNewDeparture(boeing10, 10);
        boolean airbus9Booked = yvr.makeNewDeparture(airbus9, 9);
        boolean douglas15Booked = yvr.makeNewDeparture(douglas15, 15);

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
        assertTrue(yvr.makeNewDeparture(boeing, 15));
        assertTrue(yvr.verifyDeparture(boeing, 15));
    }

    @Test
    public void testScheduleDepartureAtTakenTime() {
        boolean madeDeparture = yvr.makeNewDeparture(boeing, 15);
        assertTrue(madeDeparture);
        assertTrue(yvr.verifyDeparture(boeing, 15));

        Plane p = new Plane("Just a Plane");
        assertTrue(yvr.makeNewDeparture(p, 12));

        assertTrue(yvr.verifyDeparture(p, 12));
        assertFalse(yvr.verifyDeparture(boeing, 12));
    }

    @Test
    public void testEarliestDeparture() {
        assertTrue(yvr.makeNewDeparture(boeing, 5));
        assertTrue(yvr.verifyDeparture(boeing, 5));
    }

    @Test
    public void testLatestDeparture() {
        assertTrue(yvr.makeNewDeparture(boeing, 23));
        assertTrue(yvr.verifyDeparture(boeing, 23));
    }

    @Test
    public void testHash() {
        yvr.makeNewDeparture(boeing, 10);
        assertSame(boeing, yvr.getPlane(10));
        assertTrue(boeing.equals(yvr.getPlane(10)));
        assertFalse(yvr.getPlane(10).equals(null));
    }
}
