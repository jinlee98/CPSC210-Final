package ui;

import model.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AirportSchedule {
    private AirportDeparture yvr;
    private AirportDeparture yvrUrgent;
    private Scanner schedule;
    private List<String> list;

    private AirportSchedule() throws FileNotFoundException {
        yvr = new RegularFlight();
        yvrUrgent = new UrgentFlight();
        schedule = new Scanner(System.in);
        save();
    }

    private void save() throws FileNotFoundException {
        File pa = new File("pa.txt");

        Scanner input = new Scanner(pa);
        list = new ArrayList<>();

        try {
            PrintWriter output = new PrintWriter(pa);
            output.println("-------Welcome to YVR scheduling! Is this an urgent or regular booking?-------");
            output.println("--------------------------Please enter a plane name.--------------------------");
            output.println("---------------------------Enter departure time.------------------------------");
            output.println("----------------------Book another plane? Yes or no.--------------------------");
            output.println("------------------------------Departures list:--------------------------------");
            output.close();
        } catch (IOException ex) {
            System.out.printf("ERROR: %s\n", ex);
        }
        while (input.hasNextLine()) {
            list.add(input.nextLine());
        }

        urgent();
    }

    private void urgent() {
        System.out.println(list.get(0));

        String urgent;
        urgent = schedule.next();

        if (urgent.equals("urgent")) {
            urgentDeparture();
        }
        departure();
    }

    private void urgentDeparture() {
        String plane;
        int time;

        System.out.println(list.get(1));

        plane = schedule.next();
        Plane p = new Plane(plane, yvrUrgent);

        System.out.println(list.get(2));

        time = schedule.nextInt();

        yvrUrgent.makeNewDeparture(p, time);
        yvrUrgent.verifyDeparture(p, time);
        p.confirmDeparture();

        decide();
    }

    private void departure() {

        String plane;
        int time;

        System.out.println(list.get(1));

        plane = schedule.next();
        Plane p = new Plane(plane, yvr);

        System.out.println(list.get(2));

        time = schedule.nextInt();

        yvr.makeNewDeparture(p, time);
        yvr.verifyDeparture(p, time);

        decide();
    }

    private void decide() {
        String decision;
        System.out.println(list.get(3));

        decision = schedule.next();
        if (decision.equals("yes")) {
            urgent();
        } else {
            print();
        }
    }

    private void print() {
        System.out.println(list.get(4));
        yvr.printDepartures();
    }

    public static void main(String[] args) throws FileNotFoundException {

        new AirportSchedule();

    }
}