package ui;

import model.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class AirportSchedule {

    public static final int WIDTH = 850;
    public static final int HEIGHT = 600;
    private AirportDeparture yvr;
    private Scanner schedule;
    private List<String> list;

    private AirportSchedule() throws FileNotFoundException    {
        yvr = new AirportDeparture();
        schedule = new Scanner(System.in);
        System.out.println("Current weather report:");
        try {
            AirportWeather.checkWeather();
        } catch (IOException e) {
            System.out.println("not a good URL");
        }
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
        Plane p = new Plane(plane, yvr);

        System.out.println(list.get(2));

        time = schedule.nextInt();

        yvr.makeUrgDeparture(p, time);
        yvr.verifyDeparture(p, time);
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

        yvr.makeRegDeparture(p, time);
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
        System.exit(0);
    }

    public static void main(String[] args) throws FileNotFoundException {

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new MainFrame("Airport Schedule");
            frame.setSize(WIDTH, HEIGHT);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });

        new AirportSchedule();

    }
}