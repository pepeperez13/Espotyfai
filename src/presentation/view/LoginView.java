package presentation.view;//package presentation.view;
import presentation.controller.LoginController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Arrays;

public class LoginView extends JPanel {
    public static final String INICIO = "INICIO";
    public static final String REGISTRO = "REGISTRO";
    private JTextField username;

    private JPasswordField password;
    private LoginController loginController;
    private JLabel success_message;

    public LoginView (InitView initView) {

        //AJUSTES PRINCIPALES DEL FRAME
        /*this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 450);
        this.setTitle("Espotifai");
        this.setLocationRelativeTo(null);*/
        loginController = new LoginController(this, initView);
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(191, 105, 240));

        //PANELES
        JPanel username_panel = new JPanel();
        username_panel.setBackground(new Color(191, 105, 240));
        JPanel password_panel = new JPanel();
        password_panel.setBackground(new Color(191, 105, 240));
        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(new Color(191, 105, 240));
        JPanel center = new JPanel(new GridLayout(4,1));
        center.setBackground(new Color(191, 105, 240));
        JPanel west = new JPanel(new BorderLayout());
        west.setBackground(new Color(191, 105, 240));
        JPanel south = new JPanel(new BorderLayout());
        south.setBackground(new Color(191, 105, 240));

        //COMPONENTES
        JLabel blank = new JLabel("         ");
        JLabel welcome_message = new JLabel("Welcome to Espotify");
        welcome_message.setSize(25, 25);
        welcome_message.setFont(new Font("Arial", Font.BOLD,25));

        JLabel label_username = new JLabel("Username");

        JLabel label_password = new JLabel("Password");

        username = new JTextField(20);
        username.setBackground(new Color(190, 150, 250));

        password = new JPasswordField(20);
        password.setBackground(new Color(190, 150, 250));

        JButton signin_button = new JButton("Sign In");
        signin_button.setBackground(new Color(160, 100, 230));
        signin_button.setBorder(new LineBorder(Color.BLUE));
        signin_button.setForeground(Color.BLUE);
        south.add(signin_button, BorderLayout.CENTER);
        signin_button.addActionListener(loginController);
        signin_button.setActionCommand(INICIO);

        success_message = new JLabel("");

        JPanel no_account = new JPanel(new GridBagLayout());
        GridBagConstraints sd = new GridBagConstraints();
        no_account.setBackground(new Color(191, 105, 240));

        JLabel register_message = new JLabel("You don't have an account?");
        sd.fill = GridBagConstraints.HORIZONTAL;
        sd.weightx = 0.0;
        sd.gridx = 0;
        sd.gridy = 0;
        no_account.add(register_message, sd);

        JButton register_button = new JButton("Register");
        register_button.setBackground(new Color(160, 100, 230));
        register_button.setBorder(new LineBorder(Color.BLUE));
        register_button.setForeground(Color.BLUE);
        sd.fill = GridBagConstraints.HORIZONTAL;
        sd.weightx = 0.0;
        sd.gridx = 0;
        sd.gridy = 1;
        no_account.add(register_button, sd);
        register_button.setActionCommand(REGISTRO);
        register_button.addActionListener(loginController);

        JLabel new_account = new JLabel("Create an account here!");
        sd.fill = GridBagConstraints.HORIZONTAL;
        sd.weightx = 0.0;
        sd.gridx = 0;
        sd.gridy = 1;
        no_account.add(new_account, sd);

        JLabel imagen = new JLabel();
        imagen.setIcon(new ImageIcon("logop.png"));
        west.add(imagen, BorderLayout.CENTER);

        //ADICIONES
        top.add(welcome_message, BorderLayout.CENTER);

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
        this.add(west, BorderLayout.WEST);
        this.add(south, BorderLayout.SOUTH);
        //this.setVisible(true);
    }

    public void showErrorMessage (boolean userExistsError, boolean passwordError) {
        String message = "";

        if (userExistsError) {
            message = "No account is registered for that name. Please sign up.\n";
        }
        if (passwordError) {
            message = message + "Incorrect password\n";
        }

        JOptionPane.showMessageDialog(this, message, "Following errors were found", JOptionPane.WARNING_MESSAGE);
    }

    public String getUserName () {
        return username.getText();
    }

    public String getPassword () {
        return Arrays.toString(password.getPassword());
    }
}

