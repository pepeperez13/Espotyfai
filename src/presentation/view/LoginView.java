package presentation.view;//package presentation.view;
import presentation.controller.LoginController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Arrays;

/**
 * Clase que diseña y muestra los componentes que van a permitir al usuario iniciar sesion en el programa
 * @author Alejandro Serra
 */
public class LoginView extends JPanel {
    public static final String INICIO = "INICIO";
    public static final String REGISTRO = "REGISTRO";
    private final JTextField username;

    private final JPasswordField password;
    private final LoginController loginController;

    /**
     * Metodo constructor que se encarga de asignar y crear clases que la vista pueda necesitar (asi como el controller)
     * Tambien se crean, organizan y estructuran todos los componentes que requiere la clase
     * @param initView vista incial que va a permitir, si se incia sesion, cambiar a la nueva vista (desde el controlador)
     */
    public LoginView (InitView initView) {

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

    }

    /**
     * Muestra un optionPane con los posibles errores que se hayan encontrado
     * @param userExistsError permite saber al optionPane si el usuario no esta registrado
     * @param passwordError permite saber al optionPane si la contrasena es incorrecta
     */
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

    /**
     * Permite obtener el usuario introducido
     * @return usuario introducido
     */
    public String getUserName () {
        return username.getText();
    }

    /**
     * Permite saber la contrasena introducida
     * @return contrasena introducida
     */
    public String getPassword () {
        return Arrays.toString(password.getPassword());
    }
}

