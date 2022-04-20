package presentation.view;

import presentation.JImagePanel;

import javax.swing.*;
import java.awt.*;

public class SignUpView extends JFrame {

    public SignUpView () {
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);


        add(setLogo());
        JPanel infoPanel = introduceInfoPanel();
        infoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(infoPanel);

        // Añadimos fondo
        JImagePanel j = new JImagePanel("images/background.jpeg");
        j.getPreferredSize();
        add(j);
    }

    private JLabel setLogo () {
        ImageIcon logoImage = new ImageIcon("images/spotiLogo.png");
        Image image = logoImage.getImage();
        image = image.getScaledInstance(325, 180, Image.SCALE_DEFAULT);
        logoImage = new ImageIcon(image);
        JLabel logoImageLabel = new JLabel(logoImage);
        logoImageLabel.setBounds(500, 10, 500, 120);

        logoImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return logoImageLabel;
    }

    private JPanel introduceInfoPanel () {
        JPanel textIntroPanel = new JPanel();
        textIntroPanel.setBounds(500, 180, 450, 600);
        textIntroPanel.setForeground(Color.blue);
        textIntroPanel.setLayout(new BoxLayout(textIntroPanel, BoxLayout.Y_AXIS));

        textIntroPanel.add(setLabelAndTextField("Username", "User"));
        textIntroPanel.add(setLabelAndTextField("E-Mail", "E-Mail"));
        textIntroPanel.add(setLabelAndTextField("Account password", "Password"));
        textIntroPanel.add(setLabelAndTextField("Confirmation password", "Password"));

        JLabel genre =  new JLabel("Introduce your genre");
        textIntroPanel.add(genre);
        genre.setAlignmentX(Component.CENTER_ALIGNMENT);
        JRadioButton jrb1 = new JRadioButton("Male");
        jrb1.setForeground(Color.MAGENTA);
        JRadioButton jrb2 = new JRadioButton("Woman");
        jrb2.setForeground(Color.MAGENTA);
        JRadioButton jrb3 = new JRadioButton("Non-binary");
        jrb3.setForeground(Color.MAGENTA);
        JRadioButton jrb4 = new JRadioButton("Other");
        jrb4.setForeground(Color.MAGENTA);
        ButtonGroup group = new ButtonGroup();
        group.add(jrb1); group.add(jrb2); group.add(jrb3); group.add(jrb4);

        JPanel auxiliar = new JPanel();
        auxiliar.add(jrb1); auxiliar.add(jrb2); auxiliar.add(jrb3); auxiliar.add(jrb4);
        textIntroPanel.add(auxiliar);

        return textIntroPanel;
    }

    public void paint (Graphics g) {
        super.paintComponents(g);

        g.setColor(Color.GREEN);
        g.drawLine(59, 178, 1400, 178);
        g.drawLine(59, 179, 1400, 179);

        g.drawLine(59, 850, 1400, 850);
        g.drawLine(59, 851, 1400, 851);
    }


    private JPanel setLabelAndTextField (String label, String tField) {
        JPanel panel = new JPanel();
        //panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setSize(new Dimension(450, 100));

        JLabel labelText = new JLabel(label);
        labelText.setPreferredSize(new Dimension(300, 20));
        labelText.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField fieldText = new JTextField(40);
        fieldText.setPreferredSize(new Dimension(300, 40));
        fieldText.setText(tField);
        fieldText.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(labelText);
        panel.add(fieldText);

        return panel;
    }




}
