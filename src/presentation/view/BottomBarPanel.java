package presentation.view;

import javax.swing.*;
import java.awt.*;

public class BottomBarPanel extends JPanel {

    private GridBagConstraints constraint;

    public BottomBarPanel() {
        setLayout(new GridBagLayout());

        this.setBackground(new Color(100, 100, 100));

        constraint = new GridBagConstraints();
        constraint.anchor = GridBagConstraints.WEST;
        constraint.weighty = 1;

        final JButton btPause = new JButton();
        btPause.setText(" || ");

        this.add(btPause, constraint);

    }
}
