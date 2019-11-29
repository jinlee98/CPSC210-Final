package ui;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

import model.*;

public class DetailsPanel extends JPanel {

    private EventListenerList listenerList = new EventListenerList();
    private AirportDeparture yvr = new AirportDeparture();
    private Plane plane;

    public DetailsPanel() {
        Dimension size = getPreferredSize();
        size.width = 400;
        setPreferredSize(size);

        setBorder(BorderFactory.createTitledBorder("Plane details"));

        JLabel intro = new JLabel("Welcome to YVR flight booking! ");
        JLabel prompt = new JLabel("Please enter information: ");
        JLabel nameLabel = new JLabel("Plane Name: ");
        JLabel urgency = new JLabel("Regular or Urgent Flight? ");
        JLabel timeLabel = new JLabel("Desired Departure Time: ");

        final JTextField nameField = new JTextField(10);
        final JTextField urgencyField = new JTextField(10);
        final JTextField timeField = new JTextField(10);

        JButton addBtn = new JButton("Schedule Plane");

        plane = new Plane("", yvr);

        addBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String name = nameField.getText();
                String time = timeField.getText();

                plane.setName(name);
                if (urgencyField.getText().equals("regular")) {
                    if (yvr.makeRegDeparture(plane, makeInt(time))) {
                        yvr.makeRegDeparture(plane, makeInt(time));
                        fireDetailEvent(new DetailEvent(this, "Regular flight booked at " + time + "\n"));
                    } else {
                        fireDetailEvent(new DetailEvent(this, "This time is not available \n"));
                    }
                } else {
                    if (yvr.makeUrgDeparture(plane, makeInt(time))) {
                        yvr.makeUrgDeparture(plane, makeInt(time));
                        fireDetailEvent(new DetailEvent(this, "Urgent flight booked at " + time + "\n"));
                    } else {
                        fireDetailEvent(new DetailEvent(this, "This time is not available \n"));
                    }
                }
            }
        });

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        //// First Column ////

        gc.anchor = GridBagConstraints.LINE_END;
        gc.weightx = 0.3333;
        gc.weighty = 0.3333;

        gc.gridx = 0;
        gc.gridy = 0;
        add(intro, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        add(prompt, gc);

        gc.gridx = 0;
        gc.gridy = 2;
        add(nameLabel, gc);

        gc.gridx = 0;
        gc.gridy = 3;
        add(urgency, gc);

        gc.gridx = 0;
        gc.gridy = 4;
        add(timeLabel, gc);

        // Second Column //

        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 1;
        gc.gridy = 2;
        add(nameField, gc);

        gc.gridx = 1;
        gc.gridy = 3;
        add(urgencyField, gc);

        gc.gridx = 1;
        gc.gridy = 4;
        add(timeField, gc);

        // Final Row //
        gc.weighty = 10;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.gridx = 1;
        gc.gridy = 5;
        add(addBtn, gc);

    }

    public int makeInt(String s) {
        return Integer.parseInt(s);
    }

    public void fireDetailEvent(DetailEvent event) {
        Object[] listeners = listenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == DetailListener.class) {
                ((DetailListener)listeners[i + 1]).detailEventOccurred(event);
            }
        }
    }

    public void addDetailListener(DetailListener listener) {
        listenerList.add(DetailListener.class, listener);
    }

    public void removeDetailListener(DetailListener listener) {
        listenerList.remove(DetailListener.class, listener);
    }

    public void printDepartures() {
        StringBuilder str = new StringBuilder();

        for (int i = 5; i < yvr.departures.size(); i++) {
            Plane c = yvr.departures.get(i);
            if (c != null) {
                str.append("\n").append(i).append(" hrs: " + c.getName());
            } else {
                str.append("\n").append(i).append("hrs: available ");
            }
        }

        fireDetailEvent(new DetailEvent(this, str.toString()));
    }
}
