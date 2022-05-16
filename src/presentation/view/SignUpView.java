package presentation.view;

import business.UserManager;
import presentation.controller.SignUpViewController;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class SignUpView extends JPanel {

    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmField;
    public static final String MALE_COMMAND = "MALE_COMMAND";
    public static final String FEMALE_COMMAND = "FEMALE_COMMAND";
    public static final String OTHER = "OTHER_COMMAND";
    public static final String REGISTER_BUTTON = "REGISTER_BUTTON";

    private final SignUpViewController controller;

    public static void main (String[] args) {
        JFrame frame = new JFrame();
        SignUpView view = new SignUpView();
        view.setVisible(true);
        frame.add(view);
        frame.setVisible(true);
    }

    public SignUpView (InitView initView) {
        //setLocation(0,10);

        controller = new SignUpViewController(this, initView);

        setSize(1500, 900);
        //setLayout(new BorderLayout(0, 50));

        setLayout(new GridLayout(3, 1));

        add(setLogo());
        add(introduceInfoPanel());
        add(setEndButtons());
    }

    private JPanel setLogo () {

        ImageIcon logoImage = new ImageIcon("images/logo.png");
        Image image = logoImage.getImage();
        image = image.getScaledInstance(175, 175, Image.SCALE_DEFAULT);
        logoImage = new ImageIcon(image);
        JLabel logoImageLabel = new JLabel(logoImage);

        logoImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel panel = new JPanel();
        panel.add(logoImageLabel);
        panel.setVisible(true);
        return panel;
    }

    private JPanel introduceInfoPanel () {
        JPanel textIntroPanel = new JPanel();
        textIntroPanel.setLayout(new BoxLayout(textIntroPanel, BoxLayout.Y_AXIS));
        textIntroPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        textIntroPanel.setAlignmentY(Component.CENTER_ALIGNMENT);



        // Añadimos todos los label y textFields
        textIntroPanel.add(setLabelAndField("Username", "User"));
        textIntroPanel.add(setLabelAndField("E-Mail", "E-Mail"));
        textIntroPanel.add(setLabelAndField("Account password", "Password"));
        textIntroPanel.add(setLabelAndField("Confirmation password", "Password"));

        // Añadimos label y RadioButton que preguntan el genero
        JLabel genre =  new JLabel("Introduce your genre");
        textIntroPanel.add(genre);
        genre.setAlignmentX(Component.CENTER_ALIGNMENT);
        JRadioButton jrb1 = new JRadioButton("Male");
        jrb1.setForeground(Color.MAGENTA);
        jrb1.setActionCommand(MALE_COMMAND);
        JRadioButton jrb2 = new JRadioButton("Woman");
        jrb2.setForeground(Color.MAGENTA);
        jrb2.setActionCommand(FEMALE_COMMAND);
        JRadioButton jrb3 = new JRadioButton("Other");
        jrb3.setForeground(Color.MAGENTA);
        jrb3.setActionCommand(OTHER);

        ButtonGroup group = new ButtonGroup();
        group.add(jrb1); group.add(jrb2);  group.add(jrb3);


        JPanel auxiliar = new JPanel();
        auxiliar.add(jrb1); auxiliar.add(jrb2);  auxiliar.add(jrb3);
        textIntroPanel.add(auxiliar);


        return textIntroPanel;
    }


    private JPanel setLabelAndField(String label, String tField) {
        JPanel panel = new JPanel();

        JLabel labelText = new JLabel(label);
        labelText.setPreferredSize(new Dimension(300, 20));
        labelText.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(labelText);

        switch (label) {
            case "Username" -> {
                nameField = new JTextField(35);
                nameField.setPreferredSize(new Dimension(300, 40));
                nameField.setText(tField);
                nameField.setAlignmentX(Component.CENTER_ALIGNMENT);
                panel.add(nameField);
            }
            case "E-Mail" -> {
                emailField = new JTextField(35);
                emailField.setPreferredSize(new Dimension(300, 40));
                emailField.setText(tField);
                emailField.setAlignmentX(Component.CENTER_ALIGNMENT);
                panel.add(emailField);
            }
            case "Account password" -> {
                passwordField = new JPasswordField(35);
                passwordField.setPreferredSize(new Dimension(300, 40));
                passwordField.setText(tField);
                passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
                panel.add(passwordField);
            }
            case "Confirmation password" -> {
                confirmField = new JPasswordField(35);
                confirmField.setPreferredSize(new Dimension(300, 40));
                confirmField.setText(tField);
                confirmField.setAlignmentX(Component.CENTER_ALIGNMENT);
                panel.add(confirmField);
            }
        }
        return panel;
    }

    public JPanel setEndButtons () {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton registerButton = new JButton("         Register         ");
        registerButton.setPreferredSize(new Dimension(100, 60));
        registerButton.setFont(new Font("Tahoma", Font.BOLD, 22));
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerButton.setBackground(new Color(52, 166, 244));
        registerButton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        registerButton.setFocusable(false);
        registerButton.setActionCommand(REGISTER_BUTTON);
        registerButton.addActionListener(controller);
        panel.add(registerButton);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1, 2));
        panel2.setAlignmentY(Component.CENTER_ALIGNMENT);

        JLabel accountAlready = new JLabel("Already have an account?");
        accountAlready.setPreferredSize(new Dimension(100, 150));
        accountAlready.setHorizontalAlignment(JLabel.RIGHT);
        accountAlready.setFont(new Font("Tahoma", Font.PLAIN, 16));

        JLabel login = new JLabel("       Log in");
        login.setHorizontalAlignment(JLabel.LEFT);
        login.setFont(new Font("Tahoma", Font.BOLD, 16));

        panel2.add(accountAlready);
        panel2.add(login);

        panel.add(panel2);
        return panel;
    }

    public void showErrorMessage (boolean userExists, boolean emailExists, boolean emailFormatError, boolean passwordFormatError, boolean confirmationError) {
        String message = "";

        if (userExists) {
            message = "Username already exists.\n";
        }
        if (emailExists) {
            message = message + "Email address is already used.\n";
        }
        if (emailFormatError) {
            message = message + "Email format is not correct.\n";
        }
        if (passwordFormatError) {
            message = message + "Password format is not correct.\n";
        }
        if (confirmationError) {
            message = message + "Passwords don't match.";
        }
        JOptionPane.showMessageDialog(this, message, "Following errors were found", JOptionPane.WARNING_MESSAGE);
    }

    public void paint (Graphics g) {
        super.paintComponents(g);

        g.setColor(Color.GREEN);
        // Lineas superior (dos juntas)
        g.drawLine(59, 178, 1425, 178);
        g.drawLine(59, 179, 1425, 179);


        // Lineas inferiores izd
        g.drawLine(59, 715, 540, 715);
        g.drawLine(59, 716, 540, 716);
        // Lineas inferiores dcha
        g.drawLine(925, 715, 1425, 715);
        g.drawLine(925, 716, 1425, 716);

    }

    public String getUserName () {
        return nameField.getText();
    }

    public String getEmail () {
        return emailField.getText();
    }

    public String getPassword () {
        return Arrays.toString(passwordField.getPassword());
    }

    public String getConfirmation () {
        return Arrays.toString(confirmField.getPassword());
    }
}