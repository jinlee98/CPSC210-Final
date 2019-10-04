package ui;

import airport.Airport;
import airport.BookingService;
import airport.Plane;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AirportSchedule {
    private BookingService vancouverInternationalAirport;
    private Scanner schedule;
    private List<String> list;

    private AirportSchedule() throws FileNotFoundException {
        vancouverInternationalAirport = new Airport();
        schedule = new Scanner(System.in);
        save();
    }

    private void save() throws FileNotFoundException {
        File pa = new File("pa.txt");

        Scanner input = new Scanner(pa);
        list = new ArrayList<>();

        try {
            PrintWriter output = new PrintWriter(pa);
            output.println("-------Welcome to YVR scheduling! Please enter a plane name.--------");
            output.println("---------Enter departure time.------");
            output.println("------Book another plane? Yes or no.-------");
            output.println("--------Departures list:---------");
            output.close();
        } catch (IOException ex) {
            System.out.printf("ERROR: %s\n", ex);
        }
        while (input.hasNextLine()) {
            list.add(input.nextLine());
        }

        departure();
    }

    private void departure() {

        String plane = "";
        int time;
        String decision = "";

        System.out.println(list.get(0));

        plane = schedule.next();
        Plane p = new Plane(plane);

        System.out.println(list.get(1));

        time = schedule.nextInt();

        vancouverInternationalAirport.makeNewDeparture(p, time);
        vancouverInternationalAirport.confirmScheduledPlane(plane, time);
        p.confirmDeparture();

        System.out.println(list.get(2));

        decision = schedule.next();
        if (decision.equals("yes")) {
            departure();
        } else {
            print();
        }
    }

    private void print() {
        System.out.println(list.get(3));
        vancouverInternationalAirport.print();
    }

    public static void main(String[] args) throws FileNotFoundException {

        new AirportSchedule();

    }
}