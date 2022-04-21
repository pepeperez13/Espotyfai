package presentation.view;

import presentation.JImagePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class SignUpView extends JFrame {
    public static final String MALE_COMMAND = "MALE_COMMAND";
    public static final String FEMALE_COMMAND = "FEMALE_COMMAND";
    public static final String OTHER = "OTHER_COMMAND";

    public SignUpView () {
        setLocationRelativeTo(null);
        setLocation(0,10);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(1500, 900);
        setResizable(false);
        //setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(setLogo());
        add(introduceInfoPanel());
        add(setRegisterButton());
        add(setAccountAlready());

        // Añadimos fondo
        JImagePanel j = new JImagePanel("images/background.jpeg");
        j.getPreferredSize();
        add(j);
    }

    private JLabel setLogo () {
        ImageIcon logoImage = new ImageIcon("images/spotiLogo.png");
        Image image = logoImage.getImage();
        image = image.getScaledInstance(180, 180, Image.SCALE_DEFAULT);
        logoImage = new ImageIcon(image);
        JLabel logoImageLabel = new JLabel(logoImage);
        logoImageLabel.setBounds(480, 10, 500, 120);

        logoImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return logoImageLabel;
    }

    private JPanel introduceInfoPanel () {
        JPanel textIntroPanel = new JPanel();
        textIntroPanel.setBounds(500, 180, 450, 450);
        textIntroPanel.setForeground(Color.blue);
        textIntroPanel.setLayout(new BoxLayout(textIntroPanel, BoxLayout.Y_AXIS));
        textIntroPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        textIntroPanel.add(setLabelAndTextField("Username", "User"));
        textIntroPanel.add(setLabelAndTextField("E-Mail", "E-Mail"));
        textIntroPanel.add(setLabelAndTextField("Account password", "Password"));
        textIntroPanel.add(setLabelAndTextField("Confirmation password", "Password"));

        JLabel genre =  new JLabel("Introduce your genre");
        textIntroPanel.add(genre);
        genre.setAlignmentX(Component.CENTER_ALIGNMENT);
        JRadioButton jrb1 = new JRadioButton("Male");
        jrb1.setForeground(Color.MAGENTA);
        jrb1.setActionCommand(MALE_COMMAND);
        JRadioButton jrb2 = new JRadioButton("Woman");
        jrb2.setForeground(Color.MAGENTA);
        jrb2.setActionCommand(FEMALE_COMMAND);
        //JRadioButton jrb3 = new JRadioButton("Non-binary");
        //jrb3.setForeground(Color.MAGENTA);
        JRadioButton jrb4 = new JRadioButton("Other");
        jrb4.setForeground(Color.MAGENTA);
        jrb4.setActionCommand(OTHER);
        ButtonGroup group = new ButtonGroup();
        group.add(jrb1); group.add(jrb2); /*group.add(jrb3);*/ group.add(jrb4);

        JPanel auxiliar = new JPanel();
        auxiliar.add(jrb1); auxiliar.add(jrb2); /*auxiliar.add(jrb3);*/ auxiliar.add(jrb4);
        textIntroPanel.add(auxiliar);

        return textIntroPanel;
    }


    private JPanel setLabelAndTextField (String label, String tField) {
        JPanel panel = new JPanel();
        //panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setSize(new Dimension(450, 100));

        JLabel labelText = new JLabel(label);
        labelText.setPreferredSize(new Dimension(300, 20));
        labelText.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(labelText);

        if (!label.equals("Account password") && !label.equals("Confirmation password")) {
            JTextField fieldText = new JTextField(35);
            fieldText.setPreferredSize(new Dimension(300, 40));
            fieldText.setText(tField);
            fieldText.setAlignmentX(Component.CENTER_ALIGNMENT);
            fieldText.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    fieldText.setText("");
                }

                @Override
                public void focusLost(FocusEvent e) {
                    // Si lo han dejado vacío, volvemos a poner valor original
                    if (fieldText.equals("")) {
                        fieldText.setText(tField);
                    } else {
                        fieldText.setText(fieldText.getText());
                    }
                }
            });
            panel.add(fieldText);
        } else {
            JPasswordField fieldText = new JPasswordField(35);
            fieldText.setPreferredSize(new Dimension(300, 40));
            fieldText.setText(tField);
            fieldText.setAlignmentX(Component.CENTER_ALIGNMENT);
            /*
            fieldText.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    fieldText.setText("");
                }

                @Override
                public void focusLost(FocusEvent e) {
                    // Si lo han dejado vacío, volvemos a poner valor original
                    if (fieldText.equals("")) {
                        fieldText.setText(tField);
                    } else {
                        fieldText.setText(fieldText.getText());
                    }
                }
            });
            */
            panel.add(fieldText);
        }




        return panel;
    }

    public JPanel setRegisterButton () {
        JPanel panel = new JPanel();
        panel.setBounds(550, 650, 330, 70);
        panel.setBackground(Color.blue);


        JLabel labelText = new JLabel("\nRegister");
        //labelText.setHorizontalAlignment(0);
        //labelText.setVerticalAlignment((int) JPanel.TOP_ALIGNMENT);
        labelText.setFont(new Font("Serif", Font.PLAIN, 30));
        labelText.setForeground(Color.white);
        labelText.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(labelText);
        return panel;
    }

    public JPanel setAccountAlready () {
         JPanel panel = new JPanel();
         panel.setBounds(530, 750, 390, 50);
         panel.setLayout(new GridLayout(1, 2));

         JLabel accountAlready = new JLabel("Already have an account?");
         accountAlready.setHorizontalAlignment(0);
         accountAlready.setFont(new Font("Tahoma", Font.PLAIN, 16));
         JLabel login = new JLabel("Log in");
         login.setHorizontalAlignment(0);
         login.setFont(new Font("Tahoma", Font.BOLD, 16));
         panel.add(accountAlready);
         panel.add(login);

         return panel;
    }

    public void paint (Graphics g) {
        super.paintComponents(g);

        g.setColor(Color.GREEN);
        // Lineas superior (dos juntas)
        g.drawLine(59, 178, 1425, 178);
        g.drawLine(59, 179, 1425, 179);


        // Lineas inferiores izd
        g.drawLine(59, 700, 525, 700);
        g.drawLine(59, 701, 525, 701);
        // Lineas inferiores dcha
        g.drawLine(925, 700, 1425, 700);
        g.drawLine(925, 701, 1425, 701);

    }




}
