package ui;

import java.util.ArrayList;
import java.util.Scanner;

class AirportSchedule {
    private final ArrayList<LogEntry> operationLog;
    private final Scanner scanner;

    private AirportSchedule() {
        operationLog = new ArrayList<>();
        scanner = new Scanner(System.in);
        processOperations();
    }

    private void processOperations() {
        String operation;
        String exit = "3";

        while (true) {
            System.out.println("Please select an option " + "([1]add departure, [2]remove departure, or [3]quit):");
            operation = scanner.nextLine();
            System.out.println("you selected: " + operation);

            if (operation.equals(exit)) {
                break;
            }

            int result = processOperation(operation);
            System.out.println("The departure list is: " + operationLog);
        }

        System.out.println("Departure List: " + operationLog);
    }

    private int processOperation(String operation) {
        int result = 0;
        LogEntry logEntry = new LogEntry();

        System.out.println("Please enter the departure time.");
        int firstTime = scanner.nextInt();
        scanner.nextLine(); //// clears the line;
        // otherwise the carriage return is taken as the
        // next input and you get a blank "selected" loop

        if (operation.equals("1")) {
            result = firstTime;
        }

        if (operation.equals("2")) {
            if (operationLog.size() > 0) {
                operationLog.remove(0);
            }
        }

        logResult(logEntry, operation, result);

        return result;
    }

    private void logResult(LogEntry logEntry, String operation, int result) {
        logEntry.setOperation(operation);
        logEntry.setResult(result);
        operationLog.add(logEntry);
    }

    public static void main(String[] args) {
        new AirportSchedule();
    }
}