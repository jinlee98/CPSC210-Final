package ui;

import airport.Airport;
import airport.Plane;

import java.util.ArrayList;
import java.util.Scanner;

public class AirportSchedule {

    public static void main(String[] args)  {

        String plane = "";
        int time;
        String operation = "";
        Scanner schedule = new Scanner(System.in);

        Airport vancouverInternationalAirport = new Airport();

        //schedule a departure from the airport
        System.out.println("-------Welcome to YVR scheduling! Please enter a plane name.--------");
        Plane p = new Plane(plane);
        plane = schedule.next();

        System.out.println("---------Enter departure time.------");
        time = schedule.nextInt();

        vancouverInternationalAirport.makeNewDeparture(p, time);
        vancouverInternationalAirport.verifyDeparture(p, time);
        vancouverInternationalAirport.confirmScheduledPlane(plane, time);
        p.confirmDeparture();

//        vancouverInternationalAirport.changeDeparture(boeing, 13);
//        boeing.confirmDeparture();
//        vancouverInternationalAirport.verifyDeparture(boeing, 13);

//        //find plane departures by name?
//        System.out.println("---------Which departure are you checking?-------------");
//
//        System.out.println("Find Boeing by name? "
//                + vancouverInternationalAirport.confirmScheduledPlane("Boeing", 13));

        //print out the departure list
        System.out.println("Departures list:");
        System.out.println("---------------------------------------------");
        vancouverInternationalAirport.printDeparturesList();
        System.out.println("---------------------------------------------");
    }
}