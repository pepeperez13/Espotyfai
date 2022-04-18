package presentation;

import javax.swing.*;
import java.awt.*;

public class BarReproduccion {
    JPanel panel = new JPanel();
    JLabel ImagenMusica = new JLabel();
    JSlider slider = new JSlider();
    JLabel currentTime = new JLabel();
    JLabel finishTime = new JLabel();

    public BarReproduccion () {
        panel.setBackground(new Color(255, 255, 255));
        panel.add(ImagenMusica);
        panel.add(currentTime);
        panel.add(slider);
        panel.add(finishTime);

        //pack();
    }
}
