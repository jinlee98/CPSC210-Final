package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LogoPanel extends JPanel {

    public LogoPanel() {
        Dimension size = getPreferredSize();
        size.width = 450;
        size.height = 200;
        setPreferredSize(size);

        setBorder(BorderFactory.createTitledBorder("YVR Airport"));

        try {
            BufferedImage image = ImageIO.read(new File("C:\\Users\\JKL95\\Desktop\\"
                    + "project_a3i2b\\src\\main\\ui\\vancouver_international_airport_logo.png"));
        } catch (IOException e) {
            System.out.println("Can't find file");
        }


    }
}
