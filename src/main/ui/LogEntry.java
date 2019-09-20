package ui;

import java.util.ArrayList;

class LogEntry {
    private String operation;
    private final ArrayList<Integer> operands;
    private int result;

    public LogEntry() {
        operation = "";
        operands = new ArrayList<>();
        result = 0;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String toString() {
        return "Departure at: " + result;
    }
}