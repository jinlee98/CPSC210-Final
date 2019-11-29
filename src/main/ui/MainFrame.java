package ui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainFrame extends JFrame {

    private DetailsPanel detailsPanel;

    public MainFrame(String title) {
        super(title);

        // Set layout manager
        setLayout(new BorderLayout());

        // Create Swing components
        final JScrollPane scrollPane = new JScrollPane();
        final JTextArea textArea = new JTextArea();
        JButton button = new JButton("Print Schedule");

        button.addActionListener(e -> {
            playClick();
            detailsPanel.printDepartures();
        });

        detailsPanel = new DetailsPanel();

        detailsPanel.addDetailListener(event -> {
            String text = event.getText();
            textArea.append(text);
        });

        // Add Swing components to content pane
        Container c = getContentPane();

        c.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setViewportView(textArea);
        c.add(button, BorderLayout.SOUTH);
        c.add(detailsPanel, BorderLayout.WEST);
        this.playMusic();
    }

    public void playMusic() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\JKL95\\"
                    + "Desktop\\project_a3i2b\\src\\main\\ui\\Soft Jazz Instrumental Music.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            System.out.println("Error with playing sound");
        }
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
