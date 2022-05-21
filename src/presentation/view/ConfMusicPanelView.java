package presentation.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ConfMusicPanelView extends JPanel {
    public static final String GO_ADD_MUSIC = "GO_ADD_MUSIC";
    public static final String GO_SHOW_MUSIC = "GO_SHOW_MUSIC";
    public static final String GO_DELETE_MUSIC = "GO_DELETE_MUSIC";
    private JButton jbAdd;
    private JButton jbShow;
    private JButton jbDel;
    private final GridBagConstraints gc = new GridBagConstraints();
    public ConfMusicPanelView() {
        setBackground(new Color(255, 255, 255));
        setLayout(new GridBagLayout());

        gc.fill = GridBagConstraints.NONE;

        JSeparator s1 = new JSeparator();
        s1.setOrientation(SwingConstants.HORIZONTAL);
        JSeparator s2 = new JSeparator();
        s2.setOrientation(SwingConstants.HORIZONTAL);

        jbAdd = new JButton();
        jbAdd.setText("Añadir canción");
        jbAdd.setFont(new Font("Arial", Font.BOLD, 18));
        jbAdd.setBackground(new Color(152, 245, 214));
        jbAdd.setBorderPainted(false);
        jbAdd.setActionCommand(GO_ADD_MUSIC);

        jbShow = new JButton();
        jbShow.setText("Ver canción");
        jbShow.setFont(new Font("Arial", Font.BOLD, 18));
        jbShow.setBackground(new Color(152, 245, 214));
        jbShow.setBorderPainted(false);
        jbShow.setActionCommand(GO_SHOW_MUSIC);

        jbDel = new JButton();
        jbDel.setText("Eliminar canción");
        jbDel.setFont(new Font("Arial", Font.BOLD, 18));
        jbDel.setBackground(new Color(230,101, 101));
        jbDel.setBorderPainted(false);
        jbDel.setActionCommand(GO_DELETE_MUSIC);

        gc.gridx = 0;
        gc.gridy = 0;
        add(jbAdd, gc);
        add(s1);
        gc.gridx = 0;
        gc.gridy = 1;
        add(jbShow, gc);
        add(s2);
        gc.gridx = 0;
        gc.gridy = 3;
        add(jbDel, gc);
    }

    public void registerController (ActionListener listener) {
        jbAdd.addActionListener(listener);
        jbShow.addActionListener(listener);
        jbDel.addActionListener(listener);
    }


}
