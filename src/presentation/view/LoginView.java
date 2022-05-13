package presentation.view;
import persistance.dao.sql.SQLConnector;
import presentation.controller.LoginController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JPanel implements ActionListener {

    public static final String INICIO = "INICIO";
    public static final String SIGN_UP = "SIGN_UP";
    //private LoginController loginController;
    private int numView;
    private final JLabel success_message;

    public LoginView() {
        //AJUSTES PRINCIPALES DEL FRAME
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setSize(600, 450);
        //this.setTitle("Espotifai");
        //this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.green.brighter());

        //PANELES
        JPanel username_panel = new JPanel();
        JPanel password_panel = new JPanel();
        JPanel top = new JPanel(new BorderLayout());
        JPanel center = new JPanel(new GridLayout(4,1));
        JPanel no_account = new JPanel(new GridLayout(1, 2));
        JPanel register_but = new JPanel(new BorderLayout());
        JPanel no_account_message = new JPanel(new GridLayout(2, 1));

        //COLORES DE FONDO
        //this.getContentPane().setBackground(Color.GREEN.darker());
        username_panel.setBackground(Color.green);
        password_panel.setBackground(Color.green);
        center.setBackground(Color.green);
        no_account.setBackground(Color.green);

        //COMPONENTES
        JLabel blank = new JLabel("");
        JLabel welcome_message = new JLabel("Welcome to Espotify");
        welcome_message.setSize(25, 25);
        welcome_message.setFont(new Font("Arial", Font.BOLD,25));

        JLabel label_username = new JLabel("Username");

        JLabel label_password = new JLabel("Password");

        JTextField username = new JTextField(20);

        JPasswordField password = new JPasswordField(20);

        JButton signin_button = new JButton("Sign In");
        signin_button.addActionListener(this);
        signin_button.setActionCommand(INICIO);

        success_message = new JLabel("");

        JLabel register_message = new JLabel("You don't have an account?");
        JLabel new_account = new JLabel("Create an account here!");

        JButton register_button = new JButton("Register");
        register_button.setSize(25, 25);
        register_button.setActionCommand(SIGN_UP);

        JLabel imagen = new JLabel();
        imagen.setIcon(new ImageIcon("logop.png"));

        //ADICIONES
        top.add(welcome_message, BorderLayout.CENTER);

        no_account_message.add(register_message);
        no_account_message.add(new_account);

        register_but.add(register_button, BorderLayout.CENTER);

        no_account.add(no_account_message);
        no_account.add(register_but);

        username_panel.add(label_username);
        username_panel.add(username);

        password_panel.add(label_password);
        password_panel.add(password);

        center.add("nothing", blank);
        center.add("username", username_panel);
        center.add("password", password_panel);
        center.add("register", no_account);

        this.add(welcome_message, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
        this.add(imagen, BorderLayout.WEST);
        this.add(signin_button, BorderLayout.SOUTH);

        //VISIBILIDAD
        this.setVisible(true);
    }

    public int changueView (int num) {
        this.numView = 0;
        this.numView = num;
        return num;
    }

    public int getNumView() {
        return numView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String aux_message = "Login successful!";
        success_message.setText(aux_message);

        if (e.getActionCommand().equals(LoginView.INICIO)) {
            this.changueView(3);
        } else if (e.getActionCommand().equals(LoginView.SIGN_UP)) {
            this.changueView(1);
        }
    }
}

