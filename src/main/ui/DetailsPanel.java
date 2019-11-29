package ui;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import model.*;

public class DetailsPanel extends JPanel {

    private EventListenerList listenerList = new EventListenerList();
    private AirportDeparture yvr = new AirportDeparture();
    private BufferedImage image;

    public DetailsPanel() {
        Dimension size = getPreferredSize();
        size.width = 400;
        setPreferredSize(size);
        setOpaque(true);
        setBackground(Color.PINK);

        setBorder(BorderFactory.createTitledBorder("Plane details"));

        try {
            image = ImageIO.read(new File("C:\\Users\\JKL95\\Desktop\\"
                    + "project_a3i2b\\src\\main\\ui\\vancouver_international_airport_logo.png"));
        } catch (IOException e) {
            System.out.println("Can't find file");
        }

        JLabel picLabel = new JLabel(new ImageIcon(image));

        JLabel intro = new JLabel("Welcome to YVR flight booking! ");
        JLabel prompt = new JLabel("Please enter information: ");
        JLabel nameLabel = new JLabel("Plane Name: ");
        JLabel urgency = new JLabel("Regular or Urgent Flight? ");
        JLabel timeLabel = new JLabel("Desired Departure Time: ");

        final JTextField nameField = new JTextField(10);
        final JTextField urgencyField = new JTextField(10);
        final JTextField timeField = new JTextField(10);

        JButton addBtn = new JButton("Schedule Plane");

        addBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String name = nameField.getText();
                String time = timeField.getText();

                Plane plane = new Plane(name, yvr);

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

        JButton weatherButton = new JButton("Click for Current Weather");

        try {
            Image img = ImageIO.read(new File("C:\\Users\\JKL95\\Desktop\\project_a3i2b\\"
                    + "src\\main\\ui\\sunicon.png"));
            weatherButton.setIcon(new ImageIcon(img));
        } catch (IOException ioe) {
            System.out.println("Image not found");
        }

        weatherButton.addActionListener(e -> {
            Desktop d = Desktop.getDesktop();
            playClick();

            try {
                d.browse(new URI("http://api.openweathermap.org/data/2.5/weather?q=Vancouver,ca&mode=xml&APPID=909101648ea24d2ff6d2ebeb77ad8fef"));
            } catch (IOException | URISyntaxException ex) {
                ex.printStackTrace();
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

        // Final Rows //
        gc.weighty = 10;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.gridx = 1;
        gc.gridy = 5;
        add(addBtn, gc);


        gc.weighty = 15;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.gridx = 1;
        gc.gridy = 6;
        add(weatherButton, gc);

        gc.weighty = 25;
        gc.anchor = GridBagConstraints.SOUTH;
        gc.gridx = 1;
        gc.gridy = 8;
        add(picLabel, gc);

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

    public void playClick() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users"
                    + "\\JKL95\\Desktop\\project_a3i2b\\src\\main\\ui\\ClickEffect.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
        }
    }
}
