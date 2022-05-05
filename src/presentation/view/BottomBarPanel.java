package presentation.view;

import javax.swing.*;
import java.awt.*;

public class BottomBarPanel extends JPanel {

    JPanel panel = new JPanel();
    JLabel ImagenMusica = new JLabel();
    JSlider slider = new JSlider();
    JLabel currentTime = new JLabel();
    JLabel finishTime = new JLabel();
    private GridBagConstraints constraint;

    public BottomBarPanel() {
        setLayout(new GridBagLayout());

        this.setBackground(new Color(100, 100, 100));

        constraint = new GridBagConstraints();
        constraint.anchor = GridBagConstraints.WEST;
        constraint.weighty = 1;

        final JButton btPause = new JButton();
        btPause.setText(" || ");

        this.add(ImagenMusica, constraint);
        this.add(currentTime, constraint);
        this.add(slider, constraint);
        this.add(finishTime, constraint);

        this.add(btPause, constraint);

    }
}
